package com.example.hpc.controller.bases;

import com.example.hpc.model.domain.DomainBase;
import com.example.hpc.model.dto.DtoBase;
import com.example.hpc.model.entity.EntityBase;
import com.example.hpc.model.repository.RepositoryBase;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.mapper.MapperBase;
import com.example.hpc.utils.predicates.PredicateBase;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @param <TEntity>
 * @param <TDto>
 * @param <TDomain>
 * @param <TRepository>
 * @param <TMapper>
 * @param <TService>
 * @param <TCriteria>
 * @param <TPredicate>
 */
public class ControllerWithSearchBase<TEntity extends EntityBase, TDto extends DtoBase, TDomain extends DomainBase,
		TRepository extends RepositoryBase<TEntity>, TMapper extends MapperBase<TEntity, TDto, TDomain>,
		TService extends ServiceWithSearchBase<TEntity, TDto, TDomain, TRepository, TMapper, TCriteria, TPredicate>,
		TCriteria, TPredicate extends PredicateBase<TEntity, TCriteria>>
		extends ControllerBase<TEntity, TDto, TDomain, TRepository, TMapper, TService> {

	private final TService service;

	public ControllerWithSearchBase(TService tService) {
		super(tService);
		this.service = tService;
	}
	// TODO checking has access for all apis



	/**
	 * get list of object by searching
	 * @param criteria
	 * @param pageable
	 * @return ResponseEntity<PagedResult<TDomain>>
	 */
	@GetMapping
	public ResponseEntity<PagedResult<TDomain>> getAll(TCriteria criteria, Pageable pageable) {
		return ResponseEntity.ok().body(service.getAll(criteria, pageable));
	}

}
