package com.example.hpc.utils.hooks;

import com.example.hpc.model.dto.DtoBase;
import com.example.hpc.model.entity.EntityBase;

public interface BeforeAdd<TEntity extends EntityBase, TDto extends DtoBase> {
	void add(TDto dto, TEntity entity);
}
