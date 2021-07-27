package com.example.hpc.model.repository;

import com.example.hpc.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends RepositoryBase<User> {
	Optional<User> findByUsername(String username);
}
