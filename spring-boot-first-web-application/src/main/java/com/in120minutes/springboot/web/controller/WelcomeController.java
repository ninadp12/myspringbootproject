package com.in120minutes.springboot.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showWelcomePage( ModelMap modelMap) {
		modelMap.put("name", getLoggedInUserName());
		return "wellcome";
	}
	
	private String getLoggedInUserName() {
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
		
	}
	
//	@RequestMapping(value="/login",method=RequestMethod.POST)
//	public String showWelcomePage( ModelMap modelMap, @RequestParam String name,@RequestParam String password) {
////		boolean isValidUser = loginService.validateUser(name, password);
////		if (!isValidUser) {
////			modelMap.put("errorMessage", "Invalid Credentials");
////			return "login";
////		}
//		modelMap.put("name", name);
//		return "wellcome";
//	}
	
	@RequestMapping("/details")
	private String userDetails(ModelMap modelMap) {
		modelMap.put("fullName", "Ninad Paralikar");
		modelMap.put("address", "Ambika Nagar Digras");
		modelMap.put("gender", "MALE");
		modelMap.put("dob", "06/08/1992");
     return "userDetails";
	}

}
