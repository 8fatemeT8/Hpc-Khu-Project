package com.example.hpc.config.jwt;

import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.UserRepository;
import com.example.hpc.utils.exceptions.ErrorCodes;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public JwtUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ExceptionHandler("user not found", ErrorCodes.ERROR_CODE_USER_NOT_FOUND));
		if (user == null) {
			onUserNotFound();
		}
		return user;
	}

	@NonNull
	public User getCurrentUser() {
		final Optional<String> currentUser = SecurityUtils.getCurrentUserLogin();

		if (currentUser.isEmpty()) {
			onUserNotFound();
		}

		final String currentUserName = currentUser.get();

		final User user = userRepository.findByUsername(currentUserName)
				.orElseThrow(() -> new ExceptionHandler("user not found", ErrorCodes.ERROR_CODE_USER_NOT_FOUND));

		if (user == null) {
			onUserNotFound();
		}

		return user;
	}

	private void onUserNotFound() {
		throw new ExceptionHandler(" User not Found", ErrorCodes.ERROR_CODE_USER_NOT_FOUND);
	}
}
