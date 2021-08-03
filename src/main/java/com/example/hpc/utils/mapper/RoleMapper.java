package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.RoleDomain;
import com.example.hpc.model.dto.RoleDto;
import com.example.hpc.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {})
public interface RoleMapper extends MapperBase<Role, RoleDto, RoleDomain> {
    @Override
    default Role createNew() {
        return new Role();
    }
}
