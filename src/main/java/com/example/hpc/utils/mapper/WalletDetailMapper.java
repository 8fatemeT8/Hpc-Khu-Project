package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.WalletDetailDomain;
import com.example.hpc.model.dto.WalletDetailDto;
import com.example.hpc.model.entity.WalletDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {})
public interface WalletDetailMapper extends MapperBase<WalletDetail, WalletDetailDto, WalletDetailDomain> {

    @Override
    default WalletDetail createNew() {
        return new WalletDetail();
    }
}
