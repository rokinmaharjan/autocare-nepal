package com.policynepal.autocare.comment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.policynepal.autocare.comment.model.DeletedComment;

public interface DeletedCommentRepository extends MongoRepository<DeletedComment, String> {

}
