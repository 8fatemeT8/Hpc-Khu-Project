package com.example.hpc.service.bases;

import com.example.hpc.model.domain.DomainBase;
import com.example.hpc.model.dto.DtoBase;
import com.example.hpc.model.entity.EntityBase;
import com.example.hpc.model.repository.RepositoryBase;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.mapper.MapperBase;
import com.example.hpc.utils.predicates.PredicateBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * this class has the ServiceBase methods and it also has the getAll method
 * @param <TEntity>
 * @param <TDto>
 * @param <TDomain>
 * @param <TRepository>
 * @param <TMapper>
 * @param <TCriteria>
 * @param <TPredicate>
 */
public class ServiceWithSearchBase<TEntity extends EntityBase, TDto extends DtoBase,
		TDomain extends DomainBase, TRepository extends RepositoryBase<TEntity>,
		TMapper extends MapperBase<TEntity, TDto, TDomain>, TCriteria, TPredicate extends PredicateBase<TEntity, TCriteria>>
		extends ServiceBase<TEntity,TDto,TDomain,TRepository,TMapper> {


	private final TPredicate predicate;
	private final TMapper tMapper;

	public ServiceWithSearchBase(TRepository tRepository, TMapper tMapper, TPredicate predicate) {
		super(tRepository, tMapper);
		this.tMapper = tMapper;
		this.predicate = predicate;
	}

	/**
	 * this method gets result by searching and generating database query
	 * @param criteria
	 * @param pageable
	 * @return
	 */
	public PagedResult<TDomain> getAll(TCriteria criteria, Pageable pageable) {
		List<TDomain> listResult =  tMapper.toDomainList(predicate.getResult(criteria, pageable));
		long count = size();
		return new PagedResult<>(listResult,pageable, count);
	}
}
