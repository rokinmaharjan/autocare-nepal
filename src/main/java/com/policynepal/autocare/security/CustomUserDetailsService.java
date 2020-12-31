package com.policynepal.autocare.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.policynepal.autocare.user.model.User;
import com.policynepal.autocare.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User dbUser = userRepository.findByUsername(username);

		if (dbUser != null) {
			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			for (String role : dbUser.getRoles()) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantedAuthorities.add(authority);
			}
					
			return new org.springframework.security.core.userdetails.User(
					dbUser.getUsername(), dbUser.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException(String.format("User '%s' not found", username));
		}
	}

}
