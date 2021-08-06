package com.example.hpc.model.dto;

import com.example.hpc.utils.validation.Email;
import com.example.hpc.utils.validation.Password;

public class UserDto extends DtoBase {
	private String username;

	@Password
	private String password;

	@Email
	private String email;

	private RoleDto role;

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
