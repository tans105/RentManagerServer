package com.rentalmanager.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalmanager.entity.LoginResponseDTO;
import com.rentalmanager.entity.UserLogin;
import com.rentalmanager.utils.PasswordUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final Map<String, List<String>> userDb = new HashMap<>();

	public UserController() {
		userDb.put("tanmayawasthi105@gmail.com", Arrays.asList("user"));
		userDb.put("mojha2701@gmail.com", Arrays.asList("user", "admin"));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public LoginResponseDTO login(@RequestBody final UserLogin login) throws ServletException {

		LoginResponseDTO response = new LoginResponseDTO();
		if (login.getEmail() == null || !userDb.containsKey(login.getEmail())) {
			response.setResponseMsg("User does not exists");
			response.setSuccess(Boolean.FALSE);
			response.setToken(null);
			return response;
		} else {
			PasswordUtil passUtil = new PasswordUtil();
			if (passUtil.comparePassword(login.getPassword(), "5f4dcc3b5aa765d61d8327deb882cf99")) {
				response.setResponseMsg("User Authenticated");
				response.setSuccess(Boolean.TRUE);
				response.setToken(Jwts.builder().setSubject(login.getEmail()).claim("roles", userDb.get(login.getEmail())).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey")
						.compact());
				return response;
			} else {
				response.setResponseMsg("Incorrect Password");
				response.setSuccess(Boolean.FALSE);
				response.setToken(null);
				return response;
			}
		}
	}

}
