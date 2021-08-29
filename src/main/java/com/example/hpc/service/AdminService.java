package com.example.hpc.service;

import com.example.hpc.model.domain.AdminDomain;
import com.example.hpc.model.dto.AdminDto;
import com.example.hpc.model.entity.Admin;
import com.example.hpc.model.repository.AdminRepository;
import com.example.hpc.service.bases.ServiceBase;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.filtering.criteria.AdminCriteria;
import com.example.hpc.utils.filtering.predicates.AdminPredicate;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.AdminMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AdminService extends ServiceBase<Admin, AdminDto, AdminDomain, AdminRepository, AdminMapper> implements BeforeAdd<Admin, AdminDto>, BeforeUpdate<Admin,AdminDto> {

    private AdminMapper adminMapper;
    private AdminPredicate adminPredicate;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper, AdminPredicate adminPredicate) {
        super(adminRepository, adminMapper);
        this.adminMapper = adminMapper;
        this.adminPredicate = adminPredicate;
    }

    @Override
    public void add(AdminDto adminDto, Admin admin) {
        admin.setCreateDate(Instant.now());
    }

    @Override
    public void update(AdminDto adminDto, Admin admin) {
        admin.setUpdateDate(Instant.now());
    }

    public PagedResult<AdminDomain> getAll(AdminCriteria criteria, Pageable pageable) {
        List<AdminDomain> listResult =  adminMapper.toDomainList(adminPredicate.getResult(criteria, pageable));
        long count = size();
        return new PagedResult<>(listResult,pageable, count);
    }
}
