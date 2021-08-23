package com.example.hpc.utils.filtering.predicates;

import com.example.hpc.model.entity.Wallet;
import com.example.hpc.utils.filtering.criteria.WalletCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class WalletPredicate extends PredicateBase<Wallet, WalletCriteria> {

	public WalletPredicate(EntityManager entityManager) {
		super(entityManager, Wallet.class);
	}

	@Override
	public List<Predicate> filter(WalletCriteria walletCriteria) {
		return predicates;
	}
}
