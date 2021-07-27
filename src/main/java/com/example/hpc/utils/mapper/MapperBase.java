package com.example.hpc.utils.mapper;


import com.example.hpc.model.domain.DomainBase;
import com.example.hpc.model.dto.DtoBase;
import com.example.hpc.model.entity.EntityBase;

import java.util.List;

public interface MapperBase<TEntity extends EntityBase, TDto extends DtoBase, TDomain extends DomainBase> {

	TEntity createNew();

	TEntity toEntity(TDomain domain);

	TEntity toEntity(TDto dto);

	List<TEntity> toEntityListDomain(List<TDomain> domains);

	List<TEntity> toEntityListDto(List<TDto> dtos);

	TDomain toDomain(TEntity entity);

	TDto toDto(TEntity entity);

	List<TDomain> toDomainList(List<TEntity> entities);

	List<TDto> toDtoList(List<TEntity> entities);
}
