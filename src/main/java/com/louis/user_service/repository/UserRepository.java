package com.louis.user_service.repository;

import com.louis.user_service.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {}
