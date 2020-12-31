package com.policynepal.autocare.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.policynepal.autocare.user.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findByUsername(String username);

	public User findByUsernameAndIdNot(String username, String userId);
}
