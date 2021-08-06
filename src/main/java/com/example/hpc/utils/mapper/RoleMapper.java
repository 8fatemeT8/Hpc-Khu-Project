package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.RoleDomain;
import com.example.hpc.model.dto.RoleDto;
import com.example.hpc.model.entity.Role;
import com.example.hpc.utils.enums.UserRolesConverter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {UserRolesConverter.class})
public interface RoleMapper extends MapperBase<Role, RoleDto, RoleDomain> {
	@Override
	default Role createNew() {
		return new Role();
	}
}
