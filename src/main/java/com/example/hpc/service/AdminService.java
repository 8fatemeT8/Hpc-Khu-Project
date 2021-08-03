package com.example.hpc.service;

import com.example.hpc.model.domain.AdminDomain;
import com.example.hpc.model.dto.AdminDto;
import com.example.hpc.model.entity.Admin;
import com.example.hpc.model.repository.AdminRepository;
import com.example.hpc.utils.mapper.AdminMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends ServiceBase<Admin, AdminDto, AdminDomain, AdminRepository, AdminMapper>{

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        super(adminRepository, adminMapper);
    }
}
