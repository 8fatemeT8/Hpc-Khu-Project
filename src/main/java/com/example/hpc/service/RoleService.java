package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.RoleDomain;
import com.example.hpc.model.dto.RoleDto;
import com.example.hpc.model.entity.Role;
import com.example.hpc.model.repository.RoleRepository;
import com.example.hpc.service.bases.ServiceBase;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.mapper.RoleMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleService extends ServiceBase<Role, RoleDto, RoleDomain, RoleRepository, RoleMapper> {

    private final RoleRepository roleRepository;
    private JwtUserDetailsService jwtUserDetailsService;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper, JwtUserDetailsService jwtUserDetailsService) {
        super(roleRepository, roleMapper);
        this.roleRepository = roleRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    public Role getByRoleName(UserRoles role) {
        return roleRepository.findByRoleName(role);
    }

    @Override
    public RoleDomain createAndUpdate(RoleDto roleDto) {
        validateAccess();
        return super.createAndUpdate(roleDto);
    }

    @Override
    public RoleDomain getOne(Long id) throws ExceptionHandler {
        validateAccess();
        return super.getOne(id);
    }

    @Override
    public void delete(Long id) {
        validateAccess();
        super.delete(id);
    }

    private void validateAccess() {
        if (Arrays.asList(UserRoles.MASTER, UserRoles.STUDENT).contains(jwtUserDetailsService.getCurrentUser().getRole().getRoleName()))
            throw new ExceptionHandler("you cant do this action", HttpStatus.FORBIDDEN.value());
    }
}
