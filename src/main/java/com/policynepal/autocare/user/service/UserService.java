package com.policynepal.autocare.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.policynepal.autocare.exceptionhandler.GlobalException;
import com.policynepal.autocare.user.dto.UserDto;
import com.policynepal.autocare.user.model.DeletedUser;
import com.policynepal.autocare.user.model.User;
import com.policynepal.autocare.user.repository.DeletedUserRepository;
import com.policynepal.autocare.user.repository.UserRepository;
import com.policynepal.autocare.utils.CustomBeanUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DeletedUserRepository deletedUserRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User addUser(UserDto userDto) throws GlobalException {
		validateIfUsernameExists(userDto.getUsername());

		User user = new User();
		CustomBeanUtils.copyNonNullProperties(userDto, user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	public User updateUser(String userId, UserDto userDto) throws GlobalException {
		validateIfUsernameExists(userDto.getUsername(), userId);

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new GlobalException("User not found", HttpStatus.BAD_REQUEST));

		CustomBeanUtils.copyNonNullProperties(userDto, user);

		return userRepository.save(user);
	}

	private User validateIfUsernameExists(String username) throws GlobalException {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			throw new GlobalException("Username already taken", HttpStatus.BAD_REQUEST);
		}

		return user;
	}

	private User validateIfUsernameExists(String username, String userId) throws GlobalException {
		User user = userRepository.findByUsernameAndIdNot(username, userId);

		if (user != null) {
			throw new GlobalException("Username already taken", HttpStatus.BAD_REQUEST);
		}

		return user;
	}

	public User getUser(String userId) throws GlobalException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new GlobalException("User not found", HttpStatus.BAD_REQUEST));
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void deleteUser(String userId) throws GlobalException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new GlobalException("User not found", HttpStatus.BAD_REQUEST));
		
		DeletedUser deletedUser = new DeletedUser(user);
		deletedUserRepository.save(deletedUser);
		
		userRepository.deleteById(userId);
	}

}
