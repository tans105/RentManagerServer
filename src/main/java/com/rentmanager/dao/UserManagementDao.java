package com.rentmanager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentmanager.constants.Constants;
import com.rentmanager.entity.database.Login;
import com.rentmanager.entity.database.PersonalDetails;
import com.rentmanager.utils.DbUtil;
import com.rentmanager.utils.HibernateUtils;

/**
 * @author : tanmay
 * @created : 06-Jun-2017
 */
public class UserManagementDao {

	public String saveEntity(Login login) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(login);
			tx.commit();
			tx = null;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			{
				if (tx != null) {
					tx.rollback();
					tx = null;
				}
				DbUtil.closeSession(session);
			}
		}
		return login.getUserId();
	}

	public String validateProfile(PersonalDetails pd) {
		GenericDao dao = new GenericDao();
		String responseMsg = null;
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("mobile", pd.getMobile());
		PersonalDetails pdCopy = null;
		pdCopy = dao.getEntityByProperty(attributes, PersonalDetails.class);
		if (pdCopy != null) {
			responseMsg = Constants.MOBILE_NUMBER_EXISTS;
			return responseMsg;
		}
		attributes.clear();
		attributes.put("email", pd.getEmail());
		pdCopy = dao.getEntityByProperty(attributes, PersonalDetails.class);
		if (pdCopy != null) {
			responseMsg = Constants.EMAIL_REGISTERED;
			return responseMsg;
		}
		return responseMsg;
	}

	public List<Map<String, Object>> getTableData(String userId, String hostelId) {
		String query = "select l.user_id,l.hostel_id,pd.first_name,pd.last_name,r.role from login l inner join role_mst r on l.role_id=r.role_id inner join personal_details pd on l.user_id=pd.user_id where l.active='true' and l.hostel_id='" + hostelId + "' order by l.user_id";
		GenericDao dao = new GenericDao();
		return dao.executeSQlQueryReturnAsListOfMaps(query);
	}
}
