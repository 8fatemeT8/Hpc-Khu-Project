package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.WalletDomain;
import com.example.hpc.model.dto.WalletDto;
import com.example.hpc.model.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {PersonMapper.class})
public interface WalletMapper extends MapperBase<Wallet, WalletDto, WalletDomain> {

    @Override
    default Wallet createNew() {
        return new Wallet();
    }
}
