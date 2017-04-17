package com.qiang.utils;


/***
 * 
 * @author 兴亚
 * 获取加密后的key
 */
public class Encrypt {

	static {
		System.load("/storage/hello/md5/libmd5.so");
	}

	public static native byte[] encrypt(byte[] plainText);
	public static native String decrypt(byte[] headers, byte[] timestamp, byte[] packageName);

	/***
	 *  這個是假的 目前不用
	 * @param plainText  加密锁需要的文本
	 * @return 加密后的key 用语server验证
	 */
	public static  byte[] getEncrypt(byte[] plainText){
		return encrypt(plainText);
	}
	/***
	 *  加密的動作 名字是解密 其實是 加密的動作
	 * @param headers
	 * @param timestamp
	 * @param packageName
	 * @return
	 */
	public static  String decryptKey(byte[] headers, byte[] timestamp, byte[] packageName){
		return decrypt(headers,timestamp,packageName);
	}
	
}
