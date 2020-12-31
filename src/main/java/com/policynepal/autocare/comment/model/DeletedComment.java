package com.policynepal.autocare.comment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deletedComments")
public class DeletedComment {

	@Id
	private String id;
	private Comment comment;

	public DeletedComment() {
		super();
	}

	public DeletedComment(Comment comment) {
		super();
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
