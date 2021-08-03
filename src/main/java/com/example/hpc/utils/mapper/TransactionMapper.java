package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.TransactionDomain;
import com.example.hpc.model.dto.TransactionDto;
import com.example.hpc.model.entity.Transaction;
import com.example.hpc.utils.enums.TransactionStatusConverter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {PersonMapper.class, TransactionStatusConverter.class})
public interface TransactionMapper extends MapperBase<Transaction, TransactionDto, TransactionDomain> {
    @Override
    default Transaction createNew() {
        return new Transaction();
    }
}
