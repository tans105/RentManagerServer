package com.rentalmanager.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalmanager.constants.Constants;
import com.rentalmanager.entity.LoginResponseDTO;
import com.rentalmanager.entity.RoleMst;
import com.rentalmanager.entity.UserLogin;
import com.rentalmanager.entity.Users;
import com.rentalmanager.service.UserService;
import com.rentalmanager.utils.PasswordUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
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
			if (!userProfile.getActive()) {
				response.setResponseMsg("User is not active, Contact Admin");
				response.setSuccess(Boolean.FALSE);
				response.setToken(null);
				return response;
			}
			if (passUtil.comparePassword(login.getPassword(), userProfile.getPassword())) {
				RoleMst role=service.getRole(userProfile.getRoleId());
				response.setResponseMsg(Constants.SUCCESSFUL_AUTHENTICATION);
				response.setSuccess(Boolean.TRUE);
				response.setModuleList(service.readModuleList(userProfile.getRoleId()));
				response.setToken(Jwts.builder().setSubject(login.getEmail()).claim("role", role.getRole()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
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
