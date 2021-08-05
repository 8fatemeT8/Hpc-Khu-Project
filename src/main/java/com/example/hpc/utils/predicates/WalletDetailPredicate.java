package com.example.hpc.utils.predicates;

import com.example.hpc.model.entity.WalletDetail;
import com.example.hpc.utils.criteria.WalletDetailCriteria;
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
}
