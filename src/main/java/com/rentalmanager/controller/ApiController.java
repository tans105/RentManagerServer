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
import com.rentalmanager.entity.dto.PersonalDetailsResponseDTO;
import com.rentalmanager.service.LoginService;
import com.rentalmanager.service.ProfileManagementService;

/**
 * 
 * @author tanmay
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	//	private static Logger logger = LoggerFactory.getLogger(ApiController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public PersonalDetailsResponseDTO fetchProfileDetails(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		ProfileManagementService service = new ProfileManagementService(claims.get(Constants.USER_ID).toString());
		PersonalDetailsResponseDTO response = new PersonalDetailsResponseDTO();
		response.setFormSchema(service.getFormSchema(Constants.PERSONAL_DETAILS, Constants.PERSONAL_DETAILS_SCHEMA_PATH));
		response.setPersonalDetails(service.getPersonalDetails());
		response.setSuccess(Boolean.TRUE);
		return response;
	}
}
