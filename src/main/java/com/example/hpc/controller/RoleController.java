package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerBase;
import com.example.hpc.model.domain.RoleDomain;
import com.example.hpc.model.dto.RoleDto;
import com.example.hpc.model.entity.Role;
import com.example.hpc.model.repository.RoleRepository;
import com.example.hpc.service.RoleService;
import com.example.hpc.utils.mapper.RoleMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController extends ControllerBase<Role, RoleDto, RoleDomain, RoleRepository, RoleMapper, RoleService> {

	public RoleController(RoleService roleService) {
		super(roleService);
	}
}
