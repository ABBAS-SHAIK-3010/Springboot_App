package com.abbasshaik.Myproject.repository;

import com.abbasshaik.Myproject.entities.Entry;
import com.abbasshaik.Myproject.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    void deleteByUsername(String username);

}
