package com.example.hpc.model.domain;

public class RoleDomain extends DomainBase {
	private Integer roleName;

	private String description;

	public Integer getRoleName() {
		return roleName;
	}

	public void setRoleName(Integer roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
