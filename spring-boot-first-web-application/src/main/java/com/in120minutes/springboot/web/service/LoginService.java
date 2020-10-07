package com.in120minutes.springboot.web.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
    public boolean validateUser(String name, String pass) {
    	return name.equals("Ninad") && pass.equals("Paralikar");
    }
}
