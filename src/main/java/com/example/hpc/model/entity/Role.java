package com.example.hpc.model.entity;

import com.example.hpc.utils.enums.UserRoles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role  extends EntityBase{

    @Column(name = "role_name")
    private UserRoles roleName;

    private String description;

    public UserRoles getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRoles roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
