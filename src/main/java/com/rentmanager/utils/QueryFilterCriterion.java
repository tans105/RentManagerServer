/*******************************************************************************
 *    
 * 	
 * 	  Project Benq WebService, all source code and data files except images,
 * 	  Copyright 2008-2015 Grit-Innovation Software Pvt. Ltd., India
 * 	
 * 	
 *******************************************************************************/
package com.rentmanager.utils;

import java.io.Serializable;
/**
 * 
 * @author tanmay
 *
 */
public class QueryFilterCriterion implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String OPERATOR_EQUALS= " = " ;
	public static final String OPERATOR_LESS_THAN=" < ";
	public static final String OPERATOR_LESS_THAN_OR_EQUALS=" <= ";
	public static final String OPERATOR_GREATER_THAN=" > ";
	public static final String OPERATOR_GREATER_THAN_OR_EQUALS=" >= ";
	public static final String OPERATOR_STARTS_WITH=" STARTS ";
	public static final String OPERATOR_CONTAINS=" CONTAINS ";
	public static final String OPERATOR_IN=" IN ";
	public static final String OPERATOR_ALLOCATED= "IS";
	
	String attributeName;
	String operator;
	Object value;
	
	/**
	 * Creates a new criterion with the specified
	 * attribute name, operator and value.
	 * 
	 * @param attributeName the name of the attribute to be tested.
	 * @param operator operator the operation, which must be one of the OPERATOR_ constants on the {@link com.dcs.applications.ObjectQueryCommand} class.
	 * @param value the value to compare.
	 */
	public QueryFilterCriterion(
			String attributeName,
			String operator,
			Object value){
		this.attributeName = attributeName;
		this.operator = operator;
		this.value = value;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getOperator() {
		return operator;
	}

	public Object getValue() {
		return value;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
