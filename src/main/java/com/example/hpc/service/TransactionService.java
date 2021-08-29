package com.example.hpc.service;

import com.example.hpc.model.domain.TransactionDomain;
import com.example.hpc.model.dto.TransactionDto;
import com.example.hpc.model.entity.Transaction;
import com.example.hpc.model.repository.TransactionRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.filtering.criteria.TransactionCriteria;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.TransactionMapper;
import com.example.hpc.utils.filtering.predicates.TransactionPredicate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionService extends ServiceWithSearchBase<Transaction, TransactionDto,
		TransactionDomain, TransactionRepository, TransactionMapper, TransactionCriteria, TransactionPredicate> implements BeforeAdd<Transaction,TransactionDto>, BeforeUpdate<Transaction,TransactionDto> {

	public TransactionService(TransactionRepository transactionRepository,
							  TransactionMapper transactionMapper, TransactionPredicate transactionPredicate) {
		super(transactionRepository, transactionMapper, transactionPredicate);
	}

	@Override
	public void add(TransactionDto transactionDto, Transaction transaction) {
		transaction.setCreateDate(Instant.now());
	}

	@Override
	public void update(TransactionDto transactionDto, Transaction transaction) {
		transaction.setUpdateDate(Instant.now());
	}
}
