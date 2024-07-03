package org.ShopingApp.SpringStarter.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ShopingApp.SpringStarter.model.Role;
import org.ShopingApp.SpringStarter.model.User;
import org.ShopingApp.SpringStarter.repository.RoleRepository;
import org.ShopingApp.SpringStarter.repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;




import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	RoleRepository rolerepository;
	@Autowired
	UserRepsitory userRepository;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if(userRepository.findUserByEmail(email).isPresent()) {
			
		}
		else {
			User user = new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("faimly_name").toString());
			user.setEmail(email);
			List<Role> roles = new ArrayList<>();
			roles.add(rolerepository.findById(2).get());
			user.setRoles(roles);
			userRepository.save(user);
			
		}
		redirectStrategy.sendRedirect(request, response,"/");
	}
	
	
	
}
