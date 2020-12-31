package com.policynepal.autocare.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deletedUsers")
public class DeletedUser {
	@Id
	private String id;
	private User user;

	public DeletedUser() {
		super();
	}

	public DeletedUser(User user) {
		super();
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
