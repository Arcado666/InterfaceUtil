package com.qiang.utils;

import org.apache.log4j.Logger;

import com.manyi.encry.Base64;
import com.manyi.encry.IdEncrypter;

import com.manyi.utils.Md5Utils;


public class AuthUtil {
private static Logger logger = Logger.getLogger(AuthUtil.class);
	
	// ����ַ���
	private static final String SPILT_STR = "%@%";
	// ��Կ�ַ���
	private static final String IW_ENCRYPT_STRING = "dfsafeDdfgDFDEU&(^GHD&)*#dj";

	public static final String IW_APP_LOGIN_TICKET_KEY = "uticket";

	public static String getLoginTicket(long userId) {
		if (userId == 0) {
			return null;
		}
		String userCode = IdEncrypter.encodeId(userId);
		String md5Str = Md5Utils.md5(userCode + SPILT_STR + IW_ENCRYPT_STRING);
		String value = Base64.encodeString(userCode + SPILT_STR + md5Str);
		return value;
	}
}
