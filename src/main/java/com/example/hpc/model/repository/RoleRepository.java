package com.example.hpc.model.repository;

import com.example.hpc.model.entity.Role;
import com.example.hpc.utils.enums.UserRoles;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends RepositoryBase<Role> {

	Role findByRoleName(UserRoles roles);
}
