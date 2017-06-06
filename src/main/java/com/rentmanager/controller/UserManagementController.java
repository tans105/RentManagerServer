package com.rentmanager.controller;

import io.jsonwebtoken.Claims;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentmanager.constants.Constants;
import com.rentmanager.entity.NewUser;
import com.rentmanager.entity.database.PersonalDetails;
import com.rentmanager.entity.dto.PersonalDetailsResponseDTO;
import com.rentmanager.entity.dto.UserManagementDTO;
import com.rentmanager.service.ProfileManagementService;
import com.rentmanager.service.UserManagementService;

/**
 * @author : tanmay
 * @created : 06-Jun-2017
 */
@RestController
@RequestMapping("/api/user")
public class UserManagementController {
	private static Logger logger = LoggerFactory.getLogger(UserManagementController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getNewUserForm", method = RequestMethod.GET)
	public UserManagementDTO fetchProfileDetails(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		ProfileManagementService service = new ProfileManagementService(claims.get(Constants.USER_ID).toString());
		UserManagementDTO response = new UserManagementDTO();
		response.setFormSchema(service.getGenericFormSchema(Constants.NEW_USER, Constants.NEW_USER_SCHEMA_PATH));
		response.setPersonalDetails(new PersonalDetails());
		response.setStateMst(service.getStateMst());
		response.setIdProofMst(service.getIdproofMst());
		response.setSuccess(Boolean.TRUE);
		return response;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public PersonalDetailsResponseDTO storeProfileDetails(@RequestBody final NewUser user, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		PersonalDetailsResponseDTO response = new PersonalDetailsResponseDTO();
		return response;
	}
}
