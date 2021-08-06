package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtResponse;
import com.example.hpc.model.dto.ChangePasswordDto;
import com.example.hpc.model.dto.UserDto;
import com.example.hpc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	private UserService userService;

	public AccountController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/verify")
	public ResponseEntity<?> verify(@RequestParam String token) {
		userService.verifyAccount(token);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/register")
	public ResponseEntity<JwtResponse> register(@RequestBody @Valid UserDto dto) {
//		return ResponseEntity.ok(userService.create(dto));
		return ResponseEntity.ok().build();
	}


	@GetMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody @Valid UserDto dto) {
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
