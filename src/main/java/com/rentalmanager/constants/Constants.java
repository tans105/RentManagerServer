package com.rentalmanager.constants;

/**
 * 
 * @author tanmay
 *
 */
public class Constants {
	/*
	 * JWT Filter constants
	 */

	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER_SPACE = "Bearer ";

	/*
	 * Response Messages
	 */
	public static final String USER_NOT_FOUND = "User not present";
	public static final String PASSWORD_INCORRECT = "Incorrect Password";
	public static final String SUCCESSFUL_AUTHENTICATION = "Authenticated Successfully";
	public static final String INACTIVE_USER = "User is not active, Contact Admin";
	public static final String HOSTEL_BLOCKED = "Sorry! Hostel is Blocked";
	/*
	 * Config paths
	 */
	public static final String HIBERNATE_CONFIG_PATH = "com/rentalmanager/hibernate/Hibernate.cfg.xml";
	public static final String MODULE_JSON_PATH = "module.json";

	/*
	 * Roles
	 */
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_TENANT = "tenant";

	/*
	 * Module File Structures
	 */
	public static final String[] MODULE_FIELDS = new String[] { "module_name", "module_link" };

	/*
	 * Claims
	 */

	public static final String FIRST_NAME = "firstName";
	public static final String MIDDLE_NAME = "middleName";
	public static final String LAST_NAME = "lastName";
	public static final String ROLE = "role";
	public static final String HOSTEL_ID = "hostelId";
	public static final String HOSTEL_NAME = "hostelName";
	public static final String USER_ID = "userId";

	/*
	 * Secret Encryption Key
	 */

	public static final String SECRET = "secretkey";

	/*
	 * JWT Response Messages
	 */

	public static final String INVALID_TOKEN = "Token Invalid!";
	public static final String TOKEN_EXPIRED = "Token Expired! , login Again";
	public static final String MISSING_AUTH_HEADER = "Missing or invalid Authorization header.";

	/*
	 * Methods
	 */

	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String OPTIONS = "OPTIONS";
}
