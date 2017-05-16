package com.rentalmanager.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalmanager.constants.Constants;
import com.rentalmanager.entity.LoginResponseDTO;
import com.rentalmanager.entity.UserLogin;
import com.rentalmanager.entity.Users;
import com.rentalmanager.service.UserService;
import com.rentalmanager.utils.PasswordUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Map<String, List<String>> userDb = new HashMap<>();

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public LoginResponseDTO login(@RequestBody final UserLogin login) throws ServletException {
		UserService service = new UserService();
		Users userProfile = service.getUser(login.getEmail());
		LoginResponseDTO response = new LoginResponseDTO();
		if (userProfile == null) {
			response.setResponseMsg(Constants.USER_NOT_FOUND);
			response.setSuccess(Boolean.FALSE);
			response.setToken(null);
			return response;
		} else {
			PasswordUtil passUtil = new PasswordUtil();
			if (passUtil.comparePassword(login.getPassword(), userProfile.getPassword())) {
				response.setResponseMsg(Constants.SUCCESSFULL_AUTHENTICATION);
				response.setSuccess(Boolean.TRUE);
				response.setToken(Jwts.builder().setSubject(login.getEmail()).claim("roles", userDb.get(login.getEmail())).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey")
						.compact());
				return response;
			} else {
				response.setResponseMsg(Constants.PASSWORD_INCORRECT);
				response.setSuccess(Boolean.FALSE);
				response.setToken(null);
				return response;
			}
		}
	}

}
