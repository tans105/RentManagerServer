package com.rentalmanager.constants;

public class Constants {
	
/*
 * Response Messages	
 */
public static final String USER_NOT_FOUND="User not present";
public static final String PASSWORD_INCORRECT="Incorrect Password";
public static final String SUCCESSFUL_AUTHENTICATION="Authenticated Successfully";
public static final String INACTIVE_USER="User is not active, Contact Admin";

/*
 * Config paths
 */
public static final String HIBERNATE_CONFIG_PATH="com/rentalmanager/hibernate/Hibernate.cfg.xml";
public static final String MODULE_JSON_PATH="module.json";

/*
 * Roles
 */
public static final String ROLE_ADMIN="admin";
public static final String ROLE_TENANT="tenant";

/*
 * Module File Structures
 */
public static final String[] MODULE_FIELDS=new String[]{"module_name","module_link"};

}
