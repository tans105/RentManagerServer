package com.rentmanager.service;

import com.google.common.base.Strings;
import com.rentmanager.dao.GenericDao;
import com.rentmanager.dao.UserManagementDao;
import com.rentmanager.entity.database.Login;
import com.rentmanager.entity.database.PersonalDetails;

/**
 * @author : tanmay
 * @created : 06-Jun-2017
 */
public class UserManagementService extends GenericService {
	public String userId;

	public UserManagementService(String userId) {
		this.userId = userId;
	}

	public String authorizeAndStoreNewUser(String roleId, String hostelId, PersonalDetails pd) {
		UserManagementDao dao = new UserManagementDao();
		Login login = new Login();
		login.setRoleId(Integer.parseInt(roleId));
		login.setActive(Boolean.TRUE);
		login.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
		login.setHostelId(hostelId);
		String userId = dao.saveEntity(login);
		if (Strings.isNullOrEmpty(userId)) {
			return null;
		}
		pd.setUserId(userId);
		GenericDao gdao = new GenericDao();
		gdao.saveOrUpdateEntity(pd);
		return userId;
	}

}
