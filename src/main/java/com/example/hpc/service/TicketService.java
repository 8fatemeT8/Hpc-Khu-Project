package com.example.hpc.service;

import com.example.hpc.model.domain.TicketDomain;
import com.example.hpc.model.dto.TicketDto;
import com.example.hpc.model.entity.Ticket;
import com.example.hpc.model.repository.TicketRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.criteria.TicketCriteria;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.TicketMapper;
import com.example.hpc.utils.predicates.TicketPredicate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TicketService extends ServiceWithSearchBase<Ticket, TicketDto, TicketDomain,
		TicketRepository, TicketMapper, TicketCriteria, TicketPredicate> implements BeforeAdd<Ticket,TicketDto>, BeforeUpdate<Ticket,TicketDto> {

	public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, TicketPredicate ticketPredicate) {
		super(ticketRepository, ticketMapper, ticketPredicate);
	}

	@Override
	public void add(TicketDto ticketDto, Ticket ticket) {
		ticket.setCreateDate(Instant.now());
	}

	@Override
	public void update(TicketDto ticketDto, Ticket ticket) {
		ticket.setUpdateDate(Instant.now());
	}
}
