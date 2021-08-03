package com.example.hpc.model.domain;

public class RoleDomain extends DomainBase{
    private String roleName;

    private String description;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
