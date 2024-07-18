package mainpackage.users.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Ipassword {
	 public static String hashPassword(String password) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashedBytes = md.digest(password.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for (byte b : hashedBytes) {
	                sb.append(String.format("%02x", b));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }

}
