package com.example.hpc.service;

import com.example.hpc.model.domain.RoleDomain;
import com.example.hpc.model.dto.RoleDto;
import com.example.hpc.model.entity.Role;
import com.example.hpc.model.repository.RoleRepository;
import com.example.hpc.service.bases.ServiceBase;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceBase<Role, RoleDto, RoleDomain, RoleRepository, RoleMapper> {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        super(roleRepository, roleMapper);
        this.roleRepository = roleRepository;
    }

    public Role getByRoleName(UserRoles role){
        return roleRepository.findByRoleName(role);
    }
}
