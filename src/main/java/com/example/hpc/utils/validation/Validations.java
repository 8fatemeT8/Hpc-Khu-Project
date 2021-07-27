package com.example.hpc.utils.validation;


import com.example.hpc.model.entity.EntityBase;

public interface Validations<TEntity extends EntityBase> {
	void validate(TEntity entity);
}
