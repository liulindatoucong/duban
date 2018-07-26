package com.sound.batteries.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Encoder;

public final class AESClass {
	
//	private static final String AES_KEY = "2aFhsv5I9xaM7Z5i";
	
	public static String encrypt(String content) {
		try {
			if(StringUtils.isEmpty(content)) {
                return "";
            }
			String password = JILIINFO.AES_KEY;
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            BASE64Encoder coder = new BASE64Encoder();
            coder.encode(enCodeFormat);
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(byteContent);
            String str = Base64.encode(result);
            return str;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	/**
	2. * 解密
	3. * @param str 待解密内容
	4. * @return
	5. */
	public static String decrypt(String str) {
		try {
				String password = JILIINFO.AES_KEY;
				byte[] content = Base64.decode(str);
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");
	            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
	            secureRandom.setSeed(password.getBytes());
	            kgen.init(128, secureRandom);
	            SecretKey secretKey = kgen.generateKey();
	            byte[] enCodeFormat = secretKey.getEncoded();
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(2, key);
	            byte[] result = cipher.doFinal(content);
	            return new String(result, "UTF-8");
			} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
			} catch (NoSuchPaddingException e) {
			// e.printStackTrace();
			} catch (InvalidKeyException e) {
			// e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
			// e.printStackTrace();
			} catch (BadPaddingException e) {
			// e.printStackTrace();
			}catch (Exception e){
			// e.printStackTrace();
			}
			return "";
	}
	
	
}
