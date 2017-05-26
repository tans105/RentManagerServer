package com.rentalmanager.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalmanager.constants.Constants;
import com.rentalmanager.dao.GenericDao;
import com.rentalmanager.entity.GenericResponseDTO;
import com.rentalmanager.entity.Module;
import com.rentalmanager.entity.UserLogin;
import com.rentalmanager.entity.database.HostelMst;
import com.rentalmanager.entity.database.Login;
import com.rentalmanager.entity.database.PersonalDetails;
import com.rentalmanager.entity.database.RoleMst;
import com.rentalmanager.service.UserService;
import com.rentalmanager.utils.PasswordUtil;
/**
 * 
 * @author tanmay
 *
 */
@RestController
@RequestMapping("/user")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	public GenericResponseDTO login(@RequestBody final UserLogin login) throws ServletException {
		UserService service = new UserService();
		logger.debug("LOGGED IN USER " + login.getUserId());
		Login userProfile = service.getLogin(login.getUserId());
		GenericResponseDTO response = new GenericResponseDTO();
		if (userProfile == null) {
			return generateResponse(response, Constants.USER_NOT_FOUND, Boolean.FALSE, null, null);
		} else {
			PasswordUtil passUtil = new PasswordUtil();
			if (!userProfile.getActive()) {
				return generateResponse(response, Constants.INACTIVE_USER, Boolean.FALSE, null, null);
			}
			if (passUtil.comparePassword(login.getPassword(), userProfile.getPassword())) {
				RoleMst role = service.getRole(userProfile.getRoleId());
				PersonalDetails pd = service.getPersonalDetails(userProfile.getUserId());
				HostelMst hostel = service.getHostelDetails(userProfile.getHostelId());
				if (hostel == null) {
					return generateResponse(response, Constants.HOSTEL_BLOCKED, Boolean.FALSE, null, null);
				}
				HashMap<String, Object> claims = assignClaims(role, pd, hostel);
				String token = Jwts.builder().setSubject(login.getUserId()).setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, Constants.SECRET).compact();
				userProfile.setLastLoginDtm(new Date());
				userProfile.setToken(token);
				service.updateLoginDetails(userProfile);
				return generateResponse(response, Constants.SUCCESSFUL_AUTHENTICATION, Boolean.TRUE, token, service.readModuleList(userProfile.getRoleId()));
			} else {
				return generateResponse(response, Constants.PASSWORD_INCORRECT, Boolean.FALSE, null, null);
			}
		}
	}

	private GenericResponseDTO generateResponse(GenericResponseDTO response, String responseMsg, Boolean success, String token, List<Module> list) {
		response.setResponseMsg(responseMsg);
		response.setSuccess(success);
		response.setToken(token);
		response.setModuleList(list);
		return response;
	}

	private HashMap<String, Object> assignClaims(RoleMst role, PersonalDetails pd, HostelMst hostel) {
		HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put(Constants.ROLE, role.getRole());
		claims.put(Constants.FIRST_NAME, pd.getFirstName());
		claims.put(Constants.MIDDLE_NAME, pd.getMiddleName());
		claims.put(Constants.LAST_NAME, pd.getLastName());
		claims.put(Constants.HOSTEL_NAME, hostel.getHostelName());
		claims.put(Constants.USER_ID, pd.getUserId());
		claims.put(Constants.HOSTEL_ID, hostel.getHostelId());
		return claims;
	}

}
