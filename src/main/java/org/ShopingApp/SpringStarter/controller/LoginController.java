package org.ShopingApp.SpringStarter.controller;

import org.ShopingApp.SpringStarter.repository.RoleRepository;
import org.ShopingApp.SpringStarter.repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepsitory userRepsitory;
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet()
	{
		return "register";
	}
}
