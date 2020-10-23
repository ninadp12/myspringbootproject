package com.in28minuts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WelcomeService{
	@Value("${wellcome.message}")
	private String message;
	
	public String retriveWelcomeMessage() {
		return message;
	}
}
