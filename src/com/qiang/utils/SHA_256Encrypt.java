package com.qiang.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA_256Encrypt {
	public static void main(String[] args){	
		String t= "abcd";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(t.getBytes());
			for(byte b:md.digest())
				System.out.format("%02X",b);
			
			System.out.println();
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		String s= Encrypt(t);
	    System.out.println(s);
	}
	
	public static String Encrypt(String strSrc){
        MessageDigest md = null;
        String strDes = "";
        if(strSrc == null || strSrc.length() == 0) {
        	 return strDes;
        }
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
            return strDes;
        } catch (Exception e) {
        	e.printStackTrace();
            return strDes;
		}
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
