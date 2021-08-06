package com.example.hpc.model.domain;

public class UserDomain extends DomainBase {

	private String username;

	private String email;

	private RoleDomain role;

	public RoleDomain getRole() {
		return role;
	}

	public void setRole(RoleDomain role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
