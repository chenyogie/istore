package cn.itsource.istore.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * ���ܹ�����
 */
public class MD5Util {

	private MD5Util() {

	}

	public static String secret(String str) {

		String secret = null;

		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] bytes = digest.digest(str.getBytes("utf-8"));

			BASE64Encoder encoder = new BASE64Encoder();
			secret = encoder.encode(bytes);

			// �Ѽ��ܺ���ֽ�����ת����ַ���
			// secret = new String(bytes, "UTF-8");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return secret;
	}

}
