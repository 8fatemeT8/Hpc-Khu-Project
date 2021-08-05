package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.TicketDomain;
import com.example.hpc.model.dto.TicketDto;
import com.example.hpc.model.entity.Ticket;
import com.example.hpc.model.repository.TicketRepository;
import com.example.hpc.service.TicketService;
import com.example.hpc.utils.criteria.TicketCriteria;
import com.example.hpc.utils.mapper.TicketMapper;
import com.example.hpc.utils.predicates.TicketPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController extends ControllerWithSearchBase<Ticket, TicketDto,
		TicketDomain, TicketRepository, TicketMapper, TicketService, TicketCriteria, TicketPredicate> {

	public TicketController(TicketService ticketService) {
		super(ticketService);
	}
}
