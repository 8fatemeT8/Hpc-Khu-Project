package com.example.hpc.model.dto;

public class RoleDto extends DtoBase{
    private String roleName;

    private String description;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
