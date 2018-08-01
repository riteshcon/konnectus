package com.konnectus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.konnectus.domain.Group;

public interface GroupRepository extends MongoRepository<Group, String>{

}
