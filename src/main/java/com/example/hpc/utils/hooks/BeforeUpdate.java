package com.example.hpc.utils.hooks;

import com.example.hpc.model.dto.DtoBase;
import com.example.hpc.model.entity.EntityBase;

public interface BeforeUpdate<TEntity extends EntityBase, TDto extends DtoBase> {
	void update(TDto dto, TEntity entity);
}
