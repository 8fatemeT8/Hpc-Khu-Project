package com.example.hpc.utils.predicates;


import com.example.hpc.model.entity.EntityBase;
import com.example.hpc.utils.criteria.types.IdFilter;
import com.example.hpc.utils.criteria.types.StringFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * this class makes the database query by the criteria fields
 * @param <TEntity>
 * @param <TCriteria>
 */
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

    public List<TEntity> getResult(TCriteria criteria, Pageable pageable) {
        filter(criteria);
        TypedQuery<TEntity> typedQuery = entityManager.createQuery(cq.select(root).where(predicates.toArray(new Predicate[predicates.size()])));
        if (pageable.isPaged())
            typedQuery.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
        return typedQuery.getResultList();
    }

    public List<TEntity> getResult(TCriteria criteria) {
        filter(criteria);
        TypedQuery<TEntity> typedQuery = entityManager.createQuery(cq.select(root).where(predicates.toArray(new Predicate[predicates.size()])));
        return typedQuery.getResultList();
    }

    public abstract List<Predicate> filter(TCriteria criteria);


    protected <TColumn> CriteriaBuilder.In<TColumn> getValueIn(String column, List<TColumn> values) {
        CriteriaBuilder.In<TColumn> in = cb.in(root.get(column));
        for (TColumn value : values) {
            in.value(value);
        }
        return in;
    }

    protected void addStringPredicate(StringFilter filter, String columnName) {
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

    protected void addIntegerPredicate(IdFilter filter, String columnName) {
        if (filter.getEquals() != null) {
            predicates.add(cb.equal(root.get(columnName), filter.getEquals()));
        } else if (filter.getIn() != null) {
            predicates.add(getValueIn(columnName, filter.getIn()));
        }
    }

    protected void addBooleanPredicate(Boolean filter, String columnName) {
        if (filter) {
            predicates.add(cb.isTrue(root.get(columnName)));
        } else
            predicates.add(cb.isFalse(root.get(columnName)));
    }
}
