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

import com.google.common.base.Strings;
import com.rentmanager.constants.Constants;
import com.rentmanager.entity.database.PersonalDetails;
import com.rentmanager.entity.dto.PersonalDetailsResponseDTO;
import com.rentmanager.service.ProfileManagementService;

/**
 * 
 * @author tanmay
 *
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileManagementController {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ProfileManagementController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "fetchProfile", method = RequestMethod.GET)
	public PersonalDetailsResponseDTO fetchProfileDetails(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		ProfileManagementService service = new ProfileManagementService(claims.get(Constants.USER_ID).toString());
		PersonalDetailsResponseDTO response = new PersonalDetailsResponseDTO();
		response.setFormSchema(service.getGenericFormSchema(Constants.PERSONAL_DETAILS, Constants.PERSONAL_DETAILS_SCHEMA_PATH));
		response.setPersonalDetails(service.getPersonalDetails());
		response.setStateMst(service.getStateMst());
		response.setIdProofMst(service.getIdproofMst());
		response.setSuccess(Boolean.TRUE);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "saveProfile", method = RequestMethod.POST)
	public PersonalDetailsResponseDTO storeProfileDetails(@RequestBody final PersonalDetails pd, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		ProfileManagementService service = new ProfileManagementService(claims.get(Constants.USER_ID).toString());
		PersonalDetailsResponseDTO response = new PersonalDetailsResponseDTO();
		String responseMsg = service.saveOrUpdateProfile(pd);
		if (Strings.isNullOrEmpty(responseMsg)) {
			response.setSuccess(Boolean.TRUE);
			response.setResponseMsg("Update Successful!");
		} else {
			response.setSuccess(Boolean.FALSE);
			response.setResponseMsg(responseMsg);
		}

		return response;
	}
}
