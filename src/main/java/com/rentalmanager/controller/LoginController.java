package com.rentalmanager.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.rentalmanager.constants.Constants;
import com.rentalmanager.entity.LoginResponseDTO;
import com.rentalmanager.entity.UserLogin;
import com.rentalmanager.entity.database.Login;
import com.rentalmanager.entity.database.PersonalDetails;
import com.rentalmanager.entity.database.RoleMst;
import com.rentalmanager.service.UserService;
import com.rentalmanager.utils.PasswordUtil;

@RestController
@RequestMapping("/user")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	public LoginResponseDTO login(@RequestBody final UserLogin login) throws ServletException {
		UserService service = new UserService();
		//		Users userProfile = service.getUser(login.getUserId());
		logger.debug("LOGGED IN USER " + login.getUserId());
		Login userProfile = service.getLogin(login.getUserId());
		LoginResponseDTO response = new LoginResponseDTO();
		if (userProfile == null) {
			response.setResponseMsg(Constants.USER_NOT_FOUND);
			response.setSuccess(Boolean.FALSE);
			response.setToken(null);
			return response;
		} else {
			PasswordUtil passUtil = new PasswordUtil();
			if (!userProfile.getActive()) {
				response.setResponseMsg(Constants.INACTIVE_USER);
				response.setSuccess(Boolean.FALSE);
				response.setToken(null);
				return response;
			}
			if (passUtil.comparePassword(login.getPassword(), userProfile.getPassword())) {
				RoleMst role = service.getRole(userProfile.getRoleId());
				PersonalDetails pd = service.getPersonalDetails(userProfile.getUserId());
				
				HashMap<String, Object> claims = new HashMap<String, Object>();
				claims.put("role", role.getRole());
				claims.put("firstName", pd.getFirstName());
				if (Strings.isNullOrEmpty(pd.getMiddleName()))
					claims.put("middleName", pd.getMiddleName());
				if (Strings.isNullOrEmpty(pd.getMiddleName()))
					claims.put("lastName", pd.getLastName());

				response.setResponseMsg(Constants.SUCCESSFUL_AUTHENTICATION);
				response.setSuccess(Boolean.TRUE);
				response.setModuleList(service.readModuleList(userProfile.getRoleId()));
				response.setToken(Jwts.builder().setSubject(login.getUserId()).setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
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
