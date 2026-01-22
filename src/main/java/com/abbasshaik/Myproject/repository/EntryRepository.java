package com.abbasshaik.Myproject.repository;

import com.abbasshaik.Myproject.entities.Entry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepository extends MongoRepository<Entry, ObjectId> {
}
