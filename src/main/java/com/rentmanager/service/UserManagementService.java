package com.rentmanager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.rentmanager.constants.Constants;
import com.rentmanager.dao.GenericDao;
import com.rentmanager.dao.UserManagementDao;
import com.rentmanager.entity.database.Login;
import com.rentmanager.entity.database.PersonalDetails;

public class UserManagementService extends GenericService {
	public String userId;
	private static final String USER_ID = "userId";
	private static final String RESPONSE_MSG = "responseMsg";
	UserManagementDao dao;

	public UserManagementService(String userId) {
		this.userId = userId;
		dao = new UserManagementDao();

	}

	public Map<String, String> authorizeAndStoreNewUser(String roleId, String hostelId, PersonalDetails pd) {
		UserManagementDao dao = new UserManagementDao();
		String userId = null;
		String validationResponse = validateNewUser(pd);
		Map<String, String> responseMap = new HashMap<String, String>();
		if (Strings.isNullOrEmpty(validationResponse)) {
			Login login = new Login();
			login.setRoleId(Integer.parseInt(roleId));
			login.setActive(Boolean.TRUE);
			login.setPassword(Constants.DEFAULT_PASSWORD);
			login.setHostelId(hostelId);
			userId = dao.saveEntity(login);
			if (Strings.isNullOrEmpty(userId)) {
				return null;
			}
			pd.setUserId(userId);
			GenericDao gdao = new GenericDao();
			gdao.saveOrUpdateEntity(pd);
		} else {
			responseMap.put(USER_ID, null);
			responseMap.put(RESPONSE_MSG, validationResponse);
		}
		responseMap.put(USER_ID, pd.getUserId());
		responseMap.put(RESPONSE_MSG, validationResponse);
		return responseMap;
	}

	private String validateNewUser(PersonalDetails pd) {
		return dao.validateProfile(pd);
	}

	public List<Map<String, Object>> getTableData(String hostelId) {
		return dao.getTableData(userId, hostelId);
	}

}
