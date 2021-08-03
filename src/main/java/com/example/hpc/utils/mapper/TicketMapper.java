package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.TicketDomain;
import com.example.hpc.model.dto.TicketDto;
import com.example.hpc.model.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {PersonMapper.class,AdminMapper.class})
public interface TicketMapper extends MapperBase<Ticket, TicketDto, TicketDomain>{

    @Override
    default Ticket createNew() {
        return new Ticket();
    }
}
