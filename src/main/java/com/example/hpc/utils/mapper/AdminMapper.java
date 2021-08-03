package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.AdminDomain;
import com.example.hpc.model.dto.AdminDto;
import com.example.hpc.model.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {TicketMapper.class, UserMapper.class})
public interface AdminMapper extends MapperBase<Admin, AdminDto, AdminDomain> {

    @Override
    default Admin createNew() {
        return new Admin();
    }
}
