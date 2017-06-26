package com.rentmanager.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rentmanager.constants.Constants;
import com.rentmanager.entity.SelectListData;
import com.rentmanager.utils.DbUtil;
import com.rentmanager.utils.HibernateUtils;

/**
 * 
 * @author tanmay
 *
 */
public class GenericDao {

	private static Logger logger = LoggerFactory.getLogger(GenericDao.class);

	@SuppressWarnings("unchecked")
	public List<Object> executeQueryAsList(String qry){
		
		Session session = null;
		Transaction tx = null;
		List<Object> list = new LinkedList<Object>();
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createSQLQuery(qry);
			list = q.list();
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

		return list;
	}
	public <T> T getEntityByProperty(@SuppressWarnings("rawtypes") List<Map> attributesWithOps, Class<T> clazz) {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			for (int i = 0; i < attributesWithOps.size(); i++) {
				if (attributesWithOps.get(i).get(Constants.OPERATOR).equals(Constants.OPERATOR_EQUALS)) {
					criteria.add(Restrictions.eq(attributesWithOps.get(i).get(Constants.ATTRIBUTE).toString(), attributesWithOps.get(i).get(Constants.VALUE).toString()));
				}
				if (attributesWithOps.get(i).get(Constants.OPERATOR).equals(Constants.OPERATOR_NOT_EQUALS)) {
					criteria.add(Restrictions.ne(attributesWithOps.get(i).get(Constants.ATTRIBUTE).toString(), attributesWithOps.get(i).get(Constants.VALUE).toString()));
				}
			}
			criteria.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> entityList = criteria.list();
			tx.commit();
			tx = null;

			if (entityList != null && entityList.size() > 0) {
				Object obj = entityList.get(0);
				return clazz.cast(obj);
			}

		} catch (Exception e) {
			logger.error("Error While Fetching Entity {}.", e);
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}

		return null;
	}

	public <T> T getEntityByProperty(Map<String, Object> property, Class<T> clazz) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			for (Map.Entry<String, Object> entry : property.entrySet()) {
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
			criteria.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> entityList = criteria.list();
			tx.commit();
			tx = null;

			if (entityList != null && entityList.size() > 0) {
				Object obj = entityList.get(0);
				return clazz.cast(obj);
			}
		} catch (Exception e) {
			logger.error("Error While Fetching Entity {}.", e);
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<SelectListData> getSelectListData(Class claz, List<String> attributes, String filter) {
		return getSelectListData(claz.getName(), attributes, filter);
	}

	@SuppressWarnings("rawtypes")
	public List<SelectListData> getSelectListData(String clazStr, List<String> attributes, String filter) {
		Session session = null;
		List list = null;
		Transaction tx = null;

		StringBuffer str = new StringBuffer();
		List<SelectListData> selectList = new ArrayList<SelectListData>(10);
		try {
			SessionFactory sf = HibernateUtils.getSessionFactory();
			session = sf.openSession();

			tx = session.beginTransaction();

			str.append("select distinct ");
			int i = 1;
			for (String attr : attributes) {
				if (i == attributes.size())
					str.append(attr);
				else
					str.append(attr).append(",");
				i++;
			}
			str.append(" from ");
			str.append(clazStr).append(" ");
			if (filter != null)
				str.append(filter);

			Query query = session.createQuery(str.toString());
			query.setCacheable(true);
			list = query.list();

			if (list != null && list.size() > 0) {
				Iterator itr = list.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					SelectListData data = new SelectListData();
					if (obj[0] != null)
						data.setCode(obj[0].toString());
					if (obj[1] != null)
						data.setName(obj[1].toString());
					selectList.add(data);
				}
			}

			tx.commit();
			tx = null;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				tx = null;
			}
			e.printStackTrace();
		} finally {
			if (tx != null) {
				tx.rollback();
				tx = null;
			}
			DbUtil.closeSession(session);
			list = null;
		}
		return selectList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> executeSQlQueryReturnAsListOfMaps(String sqlQuery) {

		Session session = null;
		Transaction tx = null;
		List<Map<String, Object>> list = null;
		try {
			SessionFactory sf = HibernateUtils.getSessionFactory(); // Create session factory which is defined in hibernate.cfg.xml
			session = sf.openSession();
			tx = session.beginTransaction();

			Query query = session.createSQLQuery(sqlQuery);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			list = (List<Map<String, Object>>) query.list();
			tx.commit();
			tx = null;

		} catch (Exception e) {
			logger.error("Error In Executing SQL Query {}.", e);
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		return list;
	}

	public boolean saveOrUpdateEntity(Object obj) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(obj);
			tx.commit();
			tx = null;
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Entity {}.", e);
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public int getTotaRowCount(String queryStr1) {
		Session session = null;
		Transaction tx = null;
		int totalRows = 0;
		try {
			SessionFactory sf = HibernateUtils.getSessionFactory(); // Create session factory which is defined in hibernate.cfg.xml
			session = sf.openSession();
			tx = session.beginTransaction();
			StringBuffer countQry = new StringBuffer(400);
			countQry.append("select count(*) as cnt " + queryStr1.substring(queryStr1.lastIndexOf("from"), queryStr1.length()));
			Query query = session.createSQLQuery(countQry.toString());
			List list = query.list();
			if (list != null) {
				totalRows = ((BigInteger) list.get(0)).intValue();
			}
			tx.commit();
			tx = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		return totalRows;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HashMap> executeSqlQueryReturnAsMap(String qry) {

		Session session = null;
		Transaction tx = null;
		List<HashMap> resultMap = null;

		try {
			SessionFactory sf = HibernateUtils.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(qry);
			query.setCacheable(true);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			resultMap = query.list();
			tx.commit();
			tx = null;

		} catch (Exception e) {
			DbUtil.rollBackTransaction(tx);
			e.printStackTrace();
		} finally {
			DbUtil.closeSession(session);
		}
		return resultMap;
	}

	/**
	 * runa a update on a SQL query,
	 * 
	 * @param qry
	 * @return number of rows updated
	 */
	public int exeucteSqlQueryUpdate(String qry) {

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory sf = HibernateUtils.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			Query q = session.createSQLQuery(qry);
			int ret = q.executeUpdate();
			tx.commit();
			tx = null;
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
				tx = null;
			}
		} finally {
			DbUtil.rollBackTransaction(tx);
			DbUtil.closeSession(session);
			qry = null;
		}
		return 0;
	}

}
