package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.TicketDomain;
import com.example.hpc.model.dto.TicketDto;
import com.example.hpc.model.entity.Ticket;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.TicketRepository;
import com.example.hpc.service.TicketService;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.filtering.criteria.TicketCriteria;
import com.example.hpc.utils.mapper.TicketMapper;
import com.example.hpc.utils.filtering.predicates.TicketPredicate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/ticket")
public class TicketController extends ControllerWithSearchBase<Ticket, TicketDto,
        TicketDomain, TicketRepository, TicketMapper, TicketService, TicketCriteria, TicketPredicate> {

    public TicketController(TicketService ticketService) {
        super(ticketService);
    }

    // disable this api for now
    @Override
    public ResponseEntity<?> deleteById(Long id) {
//		return super.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
