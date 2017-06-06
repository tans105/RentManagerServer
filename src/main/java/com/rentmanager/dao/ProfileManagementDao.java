package com.rentmanager.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

	public boolean saveOrUpdateProfile(PersonalDetails pd) {
		GenericDao dao = new GenericDao();
		return dao.saveOrUpdateEntity(pd);
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
