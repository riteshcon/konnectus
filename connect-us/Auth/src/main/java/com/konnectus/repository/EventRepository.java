package com.konnectus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.konnectus.domain.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String>{

}
