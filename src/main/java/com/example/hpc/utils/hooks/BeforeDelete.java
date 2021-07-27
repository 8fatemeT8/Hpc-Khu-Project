package com.example.hpc.utils.hooks;


import com.example.hpc.model.entity.EntityBase;

public interface BeforeDelete<TEntity extends EntityBase> {
	void delete(TEntity entity);
}
