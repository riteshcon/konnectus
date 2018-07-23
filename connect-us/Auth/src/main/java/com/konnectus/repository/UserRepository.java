package com.konnectus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.konnectus.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
