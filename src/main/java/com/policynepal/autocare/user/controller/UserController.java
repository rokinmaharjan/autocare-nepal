package com.policynepal.autocare.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policynepal.autocare.exceptionhandler.GlobalException;
import com.policynepal.autocare.user.dto.UserDto;
import com.policynepal.autocare.user.model.User;
import com.policynepal.autocare.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PreAuthorize("hasAuthority('SUPER_ADMIN')")
	@GetMapping()
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@PostMapping()
	public User addUser(@RequestBody UserDto userDto) throws GlobalException {
		return userService.addUser(userDto);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") String userId, @RequestBody UserDto userDto) throws GlobalException {
		return userService.updateUser(userId, userDto);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") String userId) throws GlobalException {
		return userService.getUser(userId);
	}
	
	@PostMapping("/signup")
	public User addNormalUser(@RequestBody UserDto userDto) throws GlobalException {
		return userService.addUser(userDto);
	}
	
	
	@PreAuthorize("hasAuthority('SUPER_ADMIN')")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable("id") String userId) throws GlobalException {
		userService.deleteUser(userId);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("success", true);
		
		return response;
	}
}
