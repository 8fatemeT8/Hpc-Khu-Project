package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.TransactionDomain;
import com.example.hpc.model.dto.TransactionDto;
import com.example.hpc.model.entity.Transaction;
import com.example.hpc.model.repository.TransactionRepository;
import com.example.hpc.service.TransactionService;
import com.example.hpc.utils.criteria.TransactionCriteria;
import com.example.hpc.utils.mapper.TransactionMapper;
import com.example.hpc.utils.predicates.TransactionPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController extends ControllerWithSearchBase<Transaction, TransactionDto, TransactionDomain,
		TransactionRepository, TransactionMapper, TransactionService, TransactionCriteria, TransactionPredicate> {

	public TransactionController(TransactionService transactionService) {
		super(transactionService);
	}
}
