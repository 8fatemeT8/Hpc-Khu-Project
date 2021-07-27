package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.UserDomain;
import com.example.hpc.model.dto.UserDto;
import com.example.hpc.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {})
public interface UserMapper extends MapperBase<User, UserDto, UserDomain> {

	default User createNew() {
		return new User();
	}
}
