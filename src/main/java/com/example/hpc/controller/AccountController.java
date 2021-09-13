package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtResponse;
import com.example.hpc.model.dto.ChangePasswordDto;
import com.example.hpc.model.dto.UserDto;
import com.example.hpc.service.ReCaptchaVerifierService;
import com.example.hpc.service.UserService;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  all users can call this apis and there is no filtering for access
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

	private UserService userService;
	private ReCaptchaVerifierService reCaptchaVerifierService;

	public AccountController(UserService userService, ReCaptchaVerifierService reCaptchaVerifierService) {
		this.userService = userService;
		this.reCaptchaVerifierService = reCaptchaVerifierService;
	}

	@GetMapping("/verify")
	public ResponseEntity<?> verify(@RequestParam String token) {
		userService.verifyAccount(token);
		return ResponseEntity.ok().build();
	}

	// disable create user
	@PostMapping("/register")
	public ResponseEntity<JwtResponse> register(@RequestBody @Valid UserDto dto) {
//		return ResponseEntity.ok(userService.create(dto));
		return ResponseEntity.ok().build();
	}


	@GetMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody @Valid UserDto dto, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse) {
		boolean verified = reCaptchaVerifierService.verify(recaptchaResponse);
		if (!verified) {
			throw new ExceptionHandler("RECAPTCHA_VERIFICATION_ERROR", HttpStatus.NOT_ACCEPTABLE.value());
		}
		return ResponseEntity.ok(userService.login(dto));
	}

	@PostMapping("/forget-password")
	public ResponseEntity<?> forgetPassword(String username) {
		userService.forgetPassWord(username);
		return ResponseEntity.ok("ok");
	}

	@PutMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody @Valid ChangePasswordDto changePasswordDTO) {
		userService.resetPassword(changePasswordDTO);
		return ResponseEntity.ok("ok");
	}

	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDTO) {
		userService.resetPassword(changePasswordDTO);
		return ResponseEntity.ok("ok");
	}
}
