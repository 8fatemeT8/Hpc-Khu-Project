package com.example.hpc.utils.filtering.predicates;

import com.example.hpc.model.entity.Admin;
import com.example.hpc.utils.filtering.criteria.AdminCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminPredicate extends PredicateBase<Admin, AdminCriteria> {
    public AdminPredicate(EntityManager entityManager) {
        super(entityManager, Admin.class);
    }

    @Override
    public List<Predicate> filter(AdminCriteria adminCriteria) {
        return new ArrayList<>();
    }
}
