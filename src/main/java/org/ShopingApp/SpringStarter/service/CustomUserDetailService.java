package org.ShopingApp.SpringStarter.service;

import java.util.Optional;

import org.ShopingApp.SpringStarter.model.CustomUserDetail;
import org.ShopingApp.SpringStarter.model.User;
import org.ShopingApp.SpringStarter.repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService{
@Autowired
UserRepsitory userRepsitory;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepsitory.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("user to nhi mila") );
		
		return user.map(CustomUserDetail::new).get();
	}

}
