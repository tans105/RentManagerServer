package com.rentmanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.common.base.Strings;
import com.rentmanager.constants.Constants;
import com.rentmanager.entity.database.PersonalDetails;
import com.rentmanager.utils.DbUtil;
import com.rentmanager.utils.HibernateUtils;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class ProfileManagementDao {

	public PersonalDetails getPersonalDetails(String userId) {
		GenericDao dao = new GenericDao();
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("userId", userId);
		return dao.getEntityByProperty(filter, PersonalDetails.class);
	}

	public String saveOrUpdateProfile(PersonalDetails pd) {
		String validationResponse = validateProfileWithUserId(pd);
		if (Strings.isNullOrEmpty(validationResponse)) {
			GenericDao dao = new GenericDao();
			dao.saveOrUpdateEntity(pd);
		}
		return validationResponse;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String validateProfileWithUserId(PersonalDetails pd) {
		GenericDao dao = new GenericDao();
		String responseMsg = null;
		List list = new ArrayList<Map>();
		HashMap<String, String> map = new HashMap<String, String>();
		appendToList(list, map, "mobile", Constants.OPERATOR_EQUALS, pd.getMobile());
		appendToList(list, map, "userId", Constants.OPERATOR_NOT_EQUALS, pd.getUserId());
		PersonalDetails pdCopy = null;
		pdCopy = dao.getEntityByProperty(list, PersonalDetails.class);
		if (pdCopy != null) {
			responseMsg = Constants.MOBILE_NUMBER_EXISTS;
			return responseMsg;
		}
		list.clear();
		appendToList(list, map, "email", Constants.OPERATOR_EQUALS, pd.getEmail());
		appendToList(list, map, "userId", Constants.OPERATOR_NOT_EQUALS, pd.getUserId());
		pdCopy = dao.getEntityByProperty(list, PersonalDetails.class);
		if (pdCopy != null) {
			responseMsg = Constants.EMAIL_REGISTERED;
			return responseMsg;
		}
		return responseMsg;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void appendToList(List list, HashMap<String, String> map, String attribute, String operator, String value) {
		map.put(Constants.ATTRIBUTE, attribute);
		map.put(Constants.OPERATOR, operator);
		map.put(Constants.VALUE, value);
		list.add(map.clone());
		map.clear();
	}

	@SuppressWarnings("unchecked")
	public List<String> getStateMst() {
		String query = "select name from state_mst order by name";
		Session session = null;
		Transaction tx = null;
		List<String> stateList = new LinkedList<String>();
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createSQLQuery(query);
			stateList = q.list();
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

		return stateList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getIdProofMst() {
		String query = "select name from idproof_mst";
		Session session = null;
		Transaction tx = null;
		List<String> idProofList = new LinkedList<String>();
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createSQLQuery(query);
			idProofList = q.list();
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

		return idProofList;

	}
}
