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
import com.rentmanager.entity.NewUser;
import com.rentmanager.entity.database.PersonalDetails;
import com.rentmanager.entity.dto.UserManagementResponseDTO;
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
	public UserManagementResponseDTO fetchProfileDetails(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		ProfileManagementService service = new ProfileManagementService(claims.get(Constants.USER_ID).toString());
		UserManagementResponseDTO response = new UserManagementResponseDTO();
		response.setFormSchema(service.getGenericFormSchema(Constants.NEW_USER, Constants.NEW_USER_SCHEMA_PATH));
		response.setRoleMst(service.getRoleSelectList());
		response.setPersonalDetails(new PersonalDetails());
		response.setStateMst(service.getStateMst());
		response.setIdProofMst(service.getIdproofMst());
		response.setSuccess(Boolean.TRUE);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public UserManagementResponseDTO storeProfileDetails(@RequestBody final NewUser user, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		UserManagementResponseDTO response = new UserManagementResponseDTO();
		UserManagementService service = new UserManagementService(claims.get(Constants.USER_ID).toString());
		String userId = service.authorizeAndStoreNewUser(user.getRoleId(), claims.get(Constants.HOSTEL_ID).toString(), user.getPersonalDetails());
		if (!Strings.isNullOrEmpty(userId)) {
			response.setSuccess(Boolean.TRUE);
			response.setUserId(userId);
			response.setResponseMsg(Constants.USER_ADDED_SUCCESSFULLY);
		} else {
			response.setSuccess(Boolean.FALSE);
			response.setResponseMsg(Constants.UNABLE_TO_CREATE);
		}

		return response;
	}
}
