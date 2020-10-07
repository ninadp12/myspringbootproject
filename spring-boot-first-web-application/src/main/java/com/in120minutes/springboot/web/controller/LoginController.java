package com.in120minutes.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in120minutes.springboot.web.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	// Inject Login Service
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String showLoginPage( ModelMap modelMap) {
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String showWelcomePage( ModelMap modelMap, @RequestParam String name,@RequestParam String password) {
		boolean isValidUser = loginService.validateUser(name, password);
		if (!isValidUser) {
			modelMap.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		modelMap.put("name", name);
		return "wellcome";
	}
	
	@RequestMapping("/details")
	private String userDetails(ModelMap modelMap) {
		modelMap.put("fullName", "Ninad Paralikar");
		modelMap.put("address", "Ambika Nagar Digras");
		modelMap.put("gender", "MALE");
		modelMap.put("dob", "06/08/1992");
     return "userDetails";
	}

}
