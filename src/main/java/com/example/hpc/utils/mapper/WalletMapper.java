package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.WalletDomain;
import com.example.hpc.model.dto.WalletDto;
import com.example.hpc.model.entity.Wallet;
import com.example.hpc.service.PersonService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {PersonMapper.class})
public abstract class WalletMapper implements MapperBase<Wallet, WalletDto, WalletDomain> {

    @Autowired
    private PersonService personService;

    @Override
    public Wallet createNew() {
        return new Wallet();
    }
}
