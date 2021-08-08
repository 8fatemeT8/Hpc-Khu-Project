package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtResponse;
import com.example.hpc.config.jwt.JwtTokenUtil;
import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.config.jwt.UserAuthentication;
import com.example.hpc.model.domain.UserDomain;
import com.example.hpc.model.dto.ChangePasswordDto;
import com.example.hpc.model.dto.UserDto;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.UserRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.EmailService;
import com.example.hpc.utils.ThreadUtils;
import com.example.hpc.utils.criteria.UserCriteria;
import com.example.hpc.utils.exceptions.ErrorCodes;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.UserMapper;
import com.example.hpc.utils.predicates.UserPredicate;
import com.example.hpc.utils.validation.ValidationUtils;
import com.example.hpc.utils.validation.Validations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.Optional;

@Service
public class UserService extends ServiceWithSearchBase<User, UserDto, UserDomain, UserRepository,
        UserMapper, UserCriteria, UserPredicate> implements Validations<User>, BeforeAdd<User, UserDto>, BeforeUpdate<User, UserDto> {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder encoder;
    private EmailService emailService;
    private JwtTokenUtil jwtTokenUtil;
    private UserAuthentication userAuthentication;
    private JwtUserDetailsService jwtUserDetailsService;
    private final UserPredicate userPredicate;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder,
                       EmailService emailService, JwtTokenUtil jwtTokenUtil, UserAuthentication userAuthentication,
                       JwtUserDetailsService jwtUserDetailsService, UserPredicate userPredicate, RoleService roleService
    ) {
        super(userRepository, userMapper, userPredicate);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.emailService = emailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userAuthentication = userAuthentication;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userPredicate = userPredicate;
        this.roleService = roleService;
    }

    @Override
    public void validate(User user) {
        if (user.getId() == null && userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ExceptionHandler("this username taken before", ErrorCodes.ERROR_CODE_USER_NAME_NOT_UNIQUE);

        if (user.getRole() == null)
            throw new ExceptionHandler("the role value must not be null", HttpStatus.NOT_ACCEPTABLE.value());

        if (user.getEmail() == null)
            throw new ExceptionHandler("email field must not be null", HttpStatus.NOT_ACCEPTABLE.value());
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

        user.setVerify(true);

        userRepository.save(user);
    }

    public void forgetPassWord(String username) {
        final String forgetPasswordPageUrl = "http://localhost:8085/account/reset-password?key=";
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty())
            throw new ExceptionHandler("email.not.exist", ErrorCodes.ERROR_CODE_EMAIL_NOT_EXIST);
        final String token = jwtTokenUtil.generateToken(user.get(), JwtTokenUtil.JWT_VERIFY_LIFE_TIME);
        ThreadUtils.createThreadAndStart(() -> {
            try {
                emailService.sendEmailWithLink(user.get().getEmail(), "change password",
                        "dear " + user.get().getUsername() + "<br>" + " please check this url out to change password",
                        forgetPasswordPageUrl + token);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }

    public void resetPassword(ChangePasswordDto changePasswordDTO) {
        User currentUser = jwtUserDetailsService.getCurrentUser();
        ValidationUtils.validNewPasswords(changePasswordDTO, currentUser.getPassword(), encoder);
        currentUser.setPassword(encoder.encode(changePasswordDTO.getNewPass()));
        userRepository.save(currentUser);
    }


    @Override
    @Transactional
    public void add(UserDto userDto, User user) {
        user.setCreateDate(Instant.now());
        user.setRole(roleService.getByRoleName(user.getRole().getRoleName()));

        if (user.getEmail() != null) {
            sendEmailVerify(user);
        } else {
            throw new ExceptionHandler("please enter email", ErrorCodes.ERROR_CODE_USER_DETAIL_IS_NOT_COMPLETE);
        }
        // encrypt password
        user.setPassword(encoder.encode(userDto.getPassword()));

        user.setCreateDate(Instant.now());
    }

    /**
     * cany change username and password and role
     *
     * @param userDto
     * @param user
     */
    @Override
    public void update(UserDto userDto, User user) {
        if (userDto.getEmail() != null) {
            sendEmailVerify(user);
        }
        User dbUser = userRepository.findById(user.getId()).orElseThrow(() -> new ExceptionHandler("user not found", ErrorCodes.ERROR_CODE_USER_NOT_FOUND));
        user.setPassword(dbUser.getPassword());
        user.setUsername(dbUser.getUsername());
        if (user.getRole() == null || user.getRole().getId() == null) {
            user.setRole(dbUser.getRole());
        }
        user.setUpdateDate(Instant.now());
    }

    private void sendEmailVerify(User user) {
        ThreadUtils.createThreadAndStart(() -> {
            String token = jwtTokenUtil.generateToken(user, JwtTokenUtil.JWT_VERIFY_LIFE_TIME);
            try {
                emailService.sendEmailWithLink(user.getEmail(),
                        "verify email", "hi " + user.getUsername() + " please click on the blew link and verify email to do all you want"
                        , "http://localhost:8085/api/account/verify?token=" + token);
            } catch (MessagingException e) {
            }
        });
    }
}
