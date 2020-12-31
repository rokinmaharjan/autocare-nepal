package com.policynepal.autocare.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.policynepal.autocare.user.model.User;
import com.policynepal.autocare.user.service.UserService;

@Component
public class AuthenticationUtils {

	@Autowired
	private UserService userService;

	public User getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			throw new IllegalArgumentException("Authentication Required.");
		}
		Object principal = auth.getPrincipal();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;

		return userService.findByUsername(user.getUsername());
	}

	public String getUsername() {
		User user = getAuthenticatedUser();

		return user.getUsername();
	}

	public String getUserId() {
		User user = getAuthenticatedUser();

		return user.getId();
	}
}
