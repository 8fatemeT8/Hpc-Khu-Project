package com.example.hpc.controller.bases;

import com.example.hpc.model.domain.DomainBase;
import com.example.hpc.model.dto.DtoBase;
import com.example.hpc.model.entity.EntityBase;
import com.example.hpc.model.repository.RepositoryBase;
import com.example.hpc.service.bases.ServiceBase;
import com.example.hpc.utils.mapper.MapperBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * this class uses service method and set api for each method
 * @param <TEntity>
 * @param <TDto>
 * @param <TDomain>
 * @param <TRepository>
 * @param <TMapper>
 * @param <TService>
 */
public class ControllerBase<TEntity extends EntityBase, TDto extends DtoBase, TDomain extends DomainBase,
		TRepository extends RepositoryBase<TEntity>, TMapper extends MapperBase<TEntity, TDto, TDomain>,
		TService extends ServiceBase<TEntity, TDto, TDomain, TRepository, TMapper>> {

	private final TService service;

	public ControllerBase(TService service) {
		this.service = service;
	}
	// TODO checking has access for all apis



	/**
	 * get object by id
	 * get id from pathVariable
	 * httpMethod : Get
	 * @param id
	 * @return ResponseEntity<TDomain>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TDomain> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.getOne(id));
	}

	/**
	 * this api validate dto and create one object in db
	 * get data from body
	 * httpMethod : Post
	 * @param dto
	 * @return ResponseEntity<TDomain>
	 */
	@PostMapping
	public ResponseEntity<TDomain> create(@Valid @RequestBody TDto dto) {
		return ResponseEntity.ok().body(service.createAndUpdate(dto));
	}

	/**
	 * update object and save in db
	 * get data from body
	 * httpMethod : Put
	 * @param dto
	 * @return ResponseEntity<TDomain>
	 */
	@PutMapping
	public ResponseEntity<TDomain> update(@Valid @RequestBody TDto dto) {
		return ResponseEntity.ok().body(service.createAndUpdate(dto));
	}

	/**
	 * delete object from db by id
	 * get id in pathVariable
	 * httpMethod : Delete
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
