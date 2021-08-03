package com.example.hpc.utils.predicates;


import com.example.hpc.model.entity.EntityBase;
import com.example.hpc.model.entity.Job_;
import com.example.hpc.utils.criteria.IdFilter;
import com.example.hpc.utils.criteria.StringFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public abstract class PredicateBase<TEntity extends EntityBase, TCriteria> {
    @PersistenceContext
    protected final EntityManager entityManager;
    protected CriteriaBuilder cb;
    protected CriteriaQuery<TEntity> cq;
    protected Root<TEntity> root;
    protected List<Predicate> predicates = new ArrayList<>();


    public PredicateBase(EntityManager entityManager, Class<TEntity> entityClass) {
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
        this.cq = cb.createQuery(entityClass);
        this.root = cq.from(entityClass);
    }

    public abstract List<TEntity> filter(TCriteria criteria);


    protected <TColumn> CriteriaBuilder.In<TColumn> getValueIn(String column, List<TColumn> values) {
        CriteriaBuilder.In<TColumn> in = cb.in(root.get(column));
        for (TColumn value : values) {
            in.value(value);
        }
        return in;
    }

    protected void addStringPredicate(StringFilter filter,String columnName ){
        if (filter.getEquals() != null) {
            predicates.add(cb.equal(root.get(columnName), filter.getEquals()));
        } else if (filter.getContains() != null) {
            predicates.add(cb.like(root.get(columnName), "%" + filter.getContains() + "%"));
        } else if (filter.getStartWith() != null) {
            predicates.add(cb.like(root.get(columnName), filter.getStartWith() + "%"));
        } else if (filter.getIn() != null) {
            predicates.add(getValueIn(columnName, filter.getIn()));
        }
    }

    protected void addIntegerPredicate(IdFilter filter, String columnName){
        if (filter.getEquals() != null) {
            predicates.add(cb.equal(root.get(columnName), filter.getEquals()));
        } else if (filter.getIn() != null) {
            predicates.add(getValueIn(columnName, filter.getIn()));
        }
    }

    protected void addBooleanPredicate(Boolean filter, String columnName){
        if (filter) {
            predicates.add(cb.isTrue(root.get(columnName)));
        } else
            predicates.add(cb.isFalse(root.get(columnName)));    }
}
