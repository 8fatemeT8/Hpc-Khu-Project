package com.example.hpc.service;

import com.example.hpc.model.domain.DomainBase;
import com.example.hpc.model.dto.DtoBase;
import com.example.hpc.model.dto.UserDto;
import com.example.hpc.model.entity.EntityBase;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.RepositoryBase;
import com.example.hpc.utils.exceptions.ErrorCodes;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeDelete;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.MapperBase;
import com.example.hpc.utils.predicates.PredicateBase;
import com.example.hpc.utils.validation.Validations;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


public class ServiceBase<TEntity extends EntityBase, TDto extends DtoBase,
        TDomain extends DomainBase, TRepository extends RepositoryBase<TEntity>, TMapper extends MapperBase<TEntity, TDto, TDomain>, TCriteria, TPredicate extends PredicateBase<TEntity, TCriteria>> {
    private TRepository repository;
    private TMapper mapper;
    private TPredicate predicate;

    public ServiceBase(TRepository repository, TMapper mapper, TPredicate predicate) {
        this.repository = repository;
        this.mapper = mapper;
        this.predicate = predicate;
    }

    public TDomain createAndUpdate(TDto dto) {
        TEntity entity = mapper.toEntity(dto);

        ((Validations<TEntity>) this).validate(entity);
        if (this instanceof BeforeAdd && dto.getId() == null) {
            ((BeforeAdd<TEntity, TDto>) this).add(dto, entity);
        }
        if (this instanceof BeforeUpdate && dto.getId() != null) {
            ((BeforeUpdate<TEntity, TDto>) this).update(dto, entity);
        }
        return mapper.toDomain(repository.save(entity));
    }

    public TDomain getOne(Long id) throws ExceptionHandler {
        return mapper.toDomain(repository.findById(id)
                .orElseThrow(() -> new ExceptionHandler("entity not found", ErrorCodes.ERROR_CODE_ENTITY_NOT_FOUND)));
    }

    public void delete(Long id) {
        TEntity entity = repository.findById(id)
                .orElseThrow(() -> new ExceptionHandler("entity not found", ErrorCodes.ERROR_CODE_ENTITY_NOT_FOUND));
        if (this instanceof BeforeDelete)
            ((BeforeDelete<TEntity>) this).delete(entity);

        repository.delete(entity);
    }

    public List<TDomain> getAll(TCriteria criteria, Pageable pageable) {
        return mapper.toDomainList(predicate.getResult(criteria, pageable));
    }
}
