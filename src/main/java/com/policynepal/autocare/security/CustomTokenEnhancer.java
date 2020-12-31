package com.policynepal.autocare.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.policynepal.autocare.user.repository.UserRepository;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		final Map<String, Object> additionalInfo = new HashMap<>();
		
		com.policynepal.autocare.user.model.User u = userRepository.findByUsername(user.getUsername());

		additionalInfo.put("userId", u.getId());
		additionalInfo.put("roles", user.getAuthorities());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

		return accessToken;
	}

}