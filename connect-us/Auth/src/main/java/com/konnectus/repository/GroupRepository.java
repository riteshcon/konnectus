package com.konnectus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.konnectus.domain.Group;

public interface GroupRepository extends MongoRepository<Group, String>, QueryByExampleExecutor<Group>{

}
