package com.example.hpc.model.dto;

public class RoleDto extends DtoBase{
    private Integer roleName;

    private String description;

    public RoleDto(Integer roleName) {
        this.roleName = roleName;
    }

    public RoleDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoleName() {
        return roleName;
    }

    public void setRoleName(Integer roleName) {
        this.roleName = roleName;
    }
}
