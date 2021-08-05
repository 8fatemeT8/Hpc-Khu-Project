package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerBase;
import com.example.hpc.model.domain.AdminDomain;
import com.example.hpc.model.dto.AdminDto;
import com.example.hpc.model.entity.Admin;
import com.example.hpc.model.repository.AdminRepository;
import com.example.hpc.service.AdminService;
import com.example.hpc.utils.mapper.AdminMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends ControllerBase<Admin, AdminDto, AdminDomain, AdminRepository, AdminMapper, AdminService> {

	public AdminController(AdminService adminService) {
		super(adminService);
	}
}
