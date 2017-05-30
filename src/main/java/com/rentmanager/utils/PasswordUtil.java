package com.rentmanager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author tanmay
 *
 */


public class PasswordUtil {
	private static MessageDigest md;

	public String encryptPassword(String pass) {
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = pass.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean comparePassword(String requestPassword, String storedPassword) {
		return storedPassword.equals(encryptPassword(requestPassword));
	}
}
