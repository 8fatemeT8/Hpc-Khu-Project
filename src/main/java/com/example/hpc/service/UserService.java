package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtResponse;
import com.example.hpc.config.jwt.JwtTokenUtil;
import com.example.hpc.config.jwt.UserAuthentication;
import com.example.hpc.model.domain.UserDomain;
import com.example.hpc.model.dto.UserDto;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.UserRepository;
import com.example.hpc.utils.EmailService;
import com.example.hpc.utils.ThreadUtils;
import com.example.hpc.utils.exceptions.ErrorCodes;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.mapper.UserMapper;
import com.example.hpc.utils.validation.Validations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceBase<User, UserDto, UserDomain, UserRepository, UserMapper> implements Validations<User> {

	private UserRepository userRepository;
	private UserMapper userMapper;
	private PasswordEncoder encoder;
	private EmailService emailService;
	private JwtTokenUtil jwtTokenUtil;
	private UserAuthentication userAuthentication;

	public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder,
					   EmailService emailService, JwtTokenUtil jwtTokenUtil, UserAuthentication userAuthentication) {
		super(userRepository, userMapper);
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.encoder = encoder;
		this.emailService = emailService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userAuthentication = userAuthentication;
	}

	@Override
	public void validate(User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent())
			throw new ExceptionHandler("this username taken before", ErrorCodes.ERROR_CODE_USER_NAME_NOT_UNIQUE);

		if (user.getEmail() != null) {
			ThreadUtils.createThreadAndStart(() -> {
				String token = jwtTokenUtil.generateToken(user);
				try {
//					emailService.sendEmailWithLink(user.getEmail(),
//							"verify email", "hi " + user.getUsername() + " please click on the blew link and verify email to do all you want"
//							, "https://localhost:8081/api/account/verify?token:" + token);
				} catch (/*Messaging*/Exception e) {
				}
			});
		}
		if (user.getEmail() == null) {
			throw new ExceptionHandler("please enter email", ErrorCodes.ERROR_CODE_USER_DETAIL_IS_NOT_COMPLETE);
		}

	}

	@Override
	public UserDomain createAndUpdate(UserDto userDto) {
		User entity = userMapper.toEntity(userDto);

		((Validations<User>) this).validate(entity);
		entity.setPassword(encoder.encode(userDto.getPassword()));

		return userMapper.toDomain(userRepository.save(entity));
	}

	public JwtResponse create(UserDto dto) {
		createAndUpdate(dto);
		return userAuthentication.authenticate(dto.getUsername(), dto.getPassword());
	}

	public JwtResponse login(UserDto dto) {
		return userAuthentication.authenticate(dto.getUsername(), dto.getPassword());
	}

	public void verifyAccount(String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ExceptionHandler("user not found", ErrorCodes.ERROR_CODE_USER_NOT_FOUND));

		user.setEnable(true);

		userRepository.save(user);
	}
}
