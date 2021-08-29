package com.example.hpc.utils.filtering.predicates;

import com.example.hpc.model.entity.WalletDetail;
import com.example.hpc.utils.filtering.criteria.WalletDetailCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class WalletDetailPredicate extends PredicateBase<WalletDetail, WalletDetailCriteria> {

	public WalletDetailPredicate(EntityManager entityManager) {
		super(entityManager, WalletDetail.class);
	}

	@Override
	public List<Predicate> filter(WalletDetailCriteria walletDetailCriteria) {
		return predicates;
	}

	// TODO : master and person can get own walletDetail
}
