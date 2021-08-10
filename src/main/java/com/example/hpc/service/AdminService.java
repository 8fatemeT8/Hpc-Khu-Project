package com.example.hpc.service;

import com.example.hpc.model.domain.AdminDomain;
import com.example.hpc.model.dto.AdminDto;
import com.example.hpc.model.entity.Admin;
import com.example.hpc.model.repository.AdminRepository;
import com.example.hpc.service.bases.ServiceBase;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AdminService extends ServiceBase<Admin, AdminDto, AdminDomain, AdminRepository, AdminMapper> implements BeforeAdd<Admin, AdminDto>, BeforeUpdate<Admin,AdminDto> {

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        super(adminRepository, adminMapper);
    }

    @Override
    public void add(AdminDto adminDto, Admin admin) {
        admin.setCreateDate(Instant.now());
    }

    @Override
    public void update(AdminDto adminDto, Admin admin) {
        admin.setUpdateDate(Instant.now());
    }
}
