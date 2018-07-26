package com.sound.batteries.utils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class HmacUtil {
	private static final String KEY_MAC = "HmacMD5";

	/**
	 * HMAC加密
	 *
	 * @param data
	 *            数据
	 * @param key
	 *            秘钥
	 * @return 签名结果
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key.getBytes(), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
	}

	/**
	 * byte数组转换为HexString
	 */
	public static String byteArrayToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
        byte[] var2 = bytes;
        int var3 = bytes.length;
        for(int var4 = 0; var4 < var3; ++var4) {
            byte aByte = var2[var4];
            int v = aByte & 255;
            if(v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		String inputStr = "{\"somek\":\"somev\"}";
		byte[] inputData = inputStr.getBytes();
		String key = "somekey";

		System.out.println(HmacUtil.byteArrayToHexString(HmacUtil.encryptHMAC(inputData, key)));
	}
}
