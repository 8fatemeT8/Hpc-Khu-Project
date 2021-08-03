package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {JobMapper.class, WalletMapper.class, UserMapper.class})
public interface PersonMapper extends MapperBase<Person, PersonDto, PersonDomain> {

    @Override
    default Person createNew() {
        return new Person();
    }
}
