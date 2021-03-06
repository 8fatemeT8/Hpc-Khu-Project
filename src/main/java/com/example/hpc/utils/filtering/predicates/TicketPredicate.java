package com.example.hpc.utils.filtering.predicates;

import com.example.hpc.model.entity.Ticket;
import com.example.hpc.utils.filtering.criteria.TicketCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class TicketPredicate extends PredicateBase<Ticket, TicketCriteria> {

	public TicketPredicate(EntityManager entityManager) {
		super(entityManager, Ticket.class);
	}

	@Override
	public List<Predicate> filter(TicketCriteria ticketCriteria) {
		return predicates;
	}

	// TODO add filtering for person to get own tickets
}
