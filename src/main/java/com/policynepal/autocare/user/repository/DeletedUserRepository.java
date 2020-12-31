package com.policynepal.autocare.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.policynepal.autocare.user.model.DeletedUser;

public interface DeletedUserRepository extends MongoRepository<DeletedUser, String> {

}
