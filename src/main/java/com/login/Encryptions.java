package com.login;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptions {
	
	 public static String getHashMD5(String unhashed) {
	        return getHashMD5(unhashed, "");
	    }
	 
	 
	 public static String getHashMD5(String unhashed, String salt) {
	        // Hash the password.
	        final String toHash = salt + unhashed + salt;
	        MessageDigest messageDigest = null;
	        try {
	            messageDigest = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException ex) {
	            return "00000000000000000000000000000000";
	        }
	        messageDigest.update(toHash.getBytes(), 0, toHash.length());
	        String hashed = new BigInteger(1, messageDigest.digest()).toString(16);
	        if (hashed.length() < 32) {
	            hashed = "0" + hashed;
	        }
	        //To display hashed to upper case:
	        //System.out.println(hashed.toUpperCase());
	        return hashed.toUpperCase();
	        
	    }


}