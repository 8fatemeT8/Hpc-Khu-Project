package com.example.hpc.utils.predicates;

import com.example.hpc.model.entity.Transaction;
import com.example.hpc.utils.criteria.TransactionCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class TransactionPredicate extends PredicateBase<Transaction, TransactionCriteria> {
	public TransactionPredicate(EntityManager entityManager) {
		super(entityManager, Transaction.class);
	}

	@Override
	public List<Predicate> filter(TransactionCriteria transactionCriteria) {
		return predicates;
	}
}
