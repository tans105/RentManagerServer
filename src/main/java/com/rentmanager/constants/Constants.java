package com.rentmanager.constants;

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
	public static final String UNABLE_TO_CREATE = "Unable to create User";
	public static final String USER_ADDED_SUCCESSFULLY = "User added Successfully";
	public static final String MOBILE_NUMBER_EXISTS = "Mobile number already present";
	public static final String EMAIL_REGISTERED = "Email ID already registered";
	/*
	 * Config paths
	 */
	public static final String HIBERNATE_CONFIG_PATH = "com/rentmanager/hibernate/Hibernate.cfg.xml";
	public static final String MODULE_JSON_PATH = "module.json";
	public static final String PERSONAL_DETAILS_SCHEMA_PATH = "formschema/personal_details_schema.json";
	public static final String NEW_USER_SCHEMA_PATH = "formschema/new_user.json";
	public static final String EDIT_USER_SCHEMA_PATH = "formschema/personal_details_schema.json";

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
	public static final String ROLE_ID = "roleId";
	public static final String HOSTEL_ID = "hostelId";
	public static final String HOSTEL_NAME = "hostelName";
	public static final String USER_ID = "userId";

	/*
	 * Secret Encryption Key
	 */

	public static final String SECRET = "secretkey";
	public static final String DEFAULT_PASSWORD = "5f4dcc3b5aa765d61d8327deb882cf99";

	/*
	 * JWT Response Messages
	 */

	public static final String INVALID_TOKEN = "Token Invalid!";
	public static final String TOKEN_EXPIRED = "Token Expired! , login Again";
	public static final String MISSING_AUTH_HEADER = "Missing or invalid Authorization header.";

	/*
	 * Request Methods
	 */

	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String OPTIONS = "OPTIONS";

	/*
	 * Form Schema Entity
	 */

	public static final String PERSONAL_DETAILS = "personalDetails";
	public static final String NEW_USER = "newUser";
	
	/*
	 * MAP KEYS
	 */
	public static final String ATTRIBUTE="attribute";
	public static final String OPERATOR="operator";
	public static final String VALUE="value"; 
	
	/*
	 * Operators
	 */
	
	public static final String OPERATOR_EQUALS= " = " ;
	public static final String OPERATOR_LESS_THAN=" < ";
	public static final String OPERATOR_LESS_THAN_OR_EQUALS=" <= ";
	public static final String OPERATOR_GREATER_THAN=" > ";
	public static final String OPERATOR_GREATER_THAN_OR_EQUALS=" >= ";
	public static final String OPERATOR_STARTS_WITH=" STARTS ";
	public static final String OPERATOR_CONTAINS=" CONTAINS ";
	public static final String OPERATOR_IN=" IN ";
	public static final String OPERATOR_ALLOCATED= "IS";
	public static final String OPERATOR_NOT_EQUALS="<>";

}
