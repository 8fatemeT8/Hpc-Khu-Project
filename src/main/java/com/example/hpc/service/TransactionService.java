package com.example.hpc.service;

import com.example.hpc.model.domain.TransactionDomain;
import com.example.hpc.model.dto.TransactionDto;
import com.example.hpc.model.entity.Transaction;
import com.example.hpc.model.repository.TransactionRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.criteria.TransactionCriteria;
import com.example.hpc.utils.mapper.TransactionMapper;
import com.example.hpc.utils.predicates.TransactionPredicate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends ServiceWithSearchBase<Transaction, TransactionDto,
		TransactionDomain, TransactionRepository, TransactionMapper, TransactionCriteria, TransactionPredicate> {

	public TransactionService(TransactionRepository transactionRepository,
							  TransactionMapper transactionMapper, TransactionPredicate transactionPredicate) {
		super(transactionRepository, transactionMapper, transactionPredicate);
	}
}
