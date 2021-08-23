package com.example.hpc.utils.filtering.predicates;

import com.example.hpc.model.entity.User;
import com.example.hpc.utils.filtering.criteria.UserCriteria;
import com.example.hpc.utils.filtering.predicates.PredicateBase;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class UserPredicate extends PredicateBase<User, UserCriteria> {
	public UserPredicate(EntityManager entityManager) {
		super(entityManager, User.class);
	}

	@Override
	public List<Predicate> filter(UserCriteria userCriteria) {
		return predicates;
	}
}
