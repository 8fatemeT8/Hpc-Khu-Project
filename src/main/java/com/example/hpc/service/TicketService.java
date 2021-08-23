package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.TicketDomain;
import com.example.hpc.model.dto.TicketDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.Ticket;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.TicketRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.filtering.criteria.TicketCriteria;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.TicketMapper;
import com.example.hpc.utils.filtering.predicates.TicketPredicate;
import com.example.hpc.utils.validation.Validations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

@Service
public class TicketService extends ServiceWithSearchBase<Ticket, TicketDto, TicketDomain,
        TicketRepository, TicketMapper, TicketCriteria, TicketPredicate> implements BeforeAdd<Ticket, TicketDto>, BeforeUpdate<Ticket, TicketDto>, Validations<Ticket> {

    private TicketRepository ticketRepository;
    private JwtUserDetailsService jwtUserDetailsService;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper,
                         TicketPredicate ticketPredicate, JwtUserDetailsService jwtUserDetailsService) {
        super(ticketRepository, ticketMapper, ticketPredicate);
        this.ticketRepository = ticketRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    public void add(TicketDto ticketDto, Ticket ticket) {
        ticket.setCreateDate(Instant.now());
    }

    @Override
    public void update(TicketDto ticketDto, Ticket ticket) {
        ticket.setUpdateDate(Instant.now());
    }


    @Override
    public void validate(Ticket ticket) {
        User user = jwtUserDetailsService.getCurrentUser();

        if (ticket.getId() == null) {
            if (Arrays.asList(UserRoles.ADMIN, UserRoles.SYS_ADMIN).contains(user.getRole().getRoleName()))
                throw new ExceptionHandler("you cant create ticket", HttpStatus.FORBIDDEN.value());
        } else {
            if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(user.getRole().getRoleName())) {
                Ticket dbTicket = ticketRepository.getById(ticket.getId());
                if (!dbTicket.getPerson().getUser().getId().equals(user.getId()))
                    throw new ExceptionHandler("you cant change this ticket", HttpStatus.FORBIDDEN.value());
            }else {
                ticket.setAdminUsername(user.getUsername());
            }
        }
    }
}
