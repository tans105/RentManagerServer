package com.rentmanager.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rentmanager.dao.GenericDao;
import com.rentmanager.entity.database.Login;
import com.rentmanager.entity.dto.GenericResponseDTO;

/**
 * 
 * @author tanmay
 *
 */
public class FilterService {

	public Boolean verifyJWT(String userId, String token) {
		GenericDao dao = new GenericDao();
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("userId", userId);
		filter.put("active", Boolean.TRUE);
		Login login = dao.getEntityByProperty(filter, Login.class);
		return token.equals(login.getToken());
	}

	public void setHttpResponse(Gson gson, GenericResponseDTO genResponse, HttpServletResponse response, String responseMsg, Boolean success, int status) {
		genResponse.setResponseMsg(responseMsg);
		genResponse.setSuccess(success);
		response.setStatus(status);
		try {
			response.getWriter().write(gson.toJson(genResponse));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
