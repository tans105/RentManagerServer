package com.rentmanager.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * 
 * @author tanmay
 *
 */
public class DbUtil {

	public static String getWhereClause(Map<String, QueryFilterCriterion> map,String query){
		
		query=query.replaceAll("WHERE", "where");
		StringBuffer whereClause = new StringBuffer(50);
		try {
			if(map!=null && map.size()>0){
				Set<String> keys = map.keySet();
				if(keys!=null && keys.size()>0){
					if(query.indexOf("where")<=0)
						whereClause.append(" where ");
					int i=0;
					for(String key : keys){
						QueryFilterCriterion criteria = map.get(key);
						if(i!=0 || query.indexOf("where")>0)
						{
							whereClause.append(" and ");
						}
					
						whereClause.append(" lower( cast(").append(criteria.getAttributeName()).append(" as text)) ");
						if(criteria.getOperator().equals(QueryFilterCriterion.OPERATOR_CONTAINS)){	
							whereClause.append(" like ").append("'%").append(criteria.getValue().toString().toLowerCase()).append("%' ");
						}
						else if(criteria.getOperator().equals(QueryFilterCriterion.OPERATOR_EQUALS)){
							whereClause.append(" = ").append("'").append(criteria.getValue().toString().toLowerCase()).append("'");
						}
						i++;
					}	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Where clause is "+whereClause);
		return whereClause.toString().trim().equalsIgnoreCase("where")?null:whereClause.toString();
	}
	
	public static String getDateWhereClause(Map<String,QueryFilterCriterion>map,String query){
		
		query=query.replaceAll("WHERE", "where");
		StringBuffer whereString = new StringBuffer(50);
		try {
			if(map!=null && map.size()>0)
			{
				Set<String> keys = map.keySet();
				if(keys!=null && keys.size()>0)
				{
					if(query.indexOf("where")<=0)
						whereString.append(" where ");
					int i=0;
					for(String key : keys)
					{
						QueryFilterCriterion criteria = map.get(key);
						if(i!=0 || query.indexOf("where")>0)
						{
							whereString.append(" and ");
						}
					
								whereString.append("CAST (").append(criteria.getAttributeName()).append(" as date)");
									if(criteria.getOperator().equals(QueryFilterCriterion.OPERATOR_GREATER_THAN))
								{	
										whereString.append(" > ");
								}
								else if(criteria.getOperator().equals(QueryFilterCriterion.OPERATOR_LESS_THAN)){
									whereString.append(" < ");
								}
								else if (criteria.getOperator().equals(QueryFilterCriterion.OPERATOR_LESS_THAN_OR_EQUALS)) {
									whereString.append(" <= ");
								}
								else if (criteria.getOperator().equals(QueryFilterCriterion.OPERATOR_GREATER_THAN_OR_EQUALS)) {
									whereString.append(" >= ");
								}
								else {
									whereString.append(" = ");
								}
									whereString.append(" cast('").append(criteria.getValue()).append("' as date) ");
								i++;
						}	
					}
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return whereString.toString();

	}
	public static void closeSession(Session session)
	{
		if(session!=null)
		{
			session.flush();
			session.close();
		}
	}
	public static void closePreparedStatement(PreparedStatement pstmt)
	{
		if(pstmt!=null)
		{
			try {
				pstmt.close();
				pstmt=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void rollBackTransaction(Transaction tx)
	{
		if(tx!=null)
		{
			tx.rollback();
		}
	}
}