package com.rentalmanager.controller;

import io.jsonwebtoken.Claims;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalmanager.constants.Constants;
import com.rentalmanager.entity.database.PersonalDetails;
import com.rentalmanager.service.UserService;

/**
 * 
 * @author tanmay
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	private static Logger logger = LoggerFactory.getLogger(ApiController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public PersonalDetails fetchProfileDetails(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		logger.debug("Profile Details for " + claims.get(Constants.USER_ID));
		return new UserService().getPersonalDetails(claims.get(Constants.USER_ID).toString());

	}
}
