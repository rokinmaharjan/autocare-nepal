package com.policynepal.autocare.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.policynepal.autocare.comment.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

	Page<Comment> findByVehicleId(String vehicleId, PageRequest pageRequest);

}
