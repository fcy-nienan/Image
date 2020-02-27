package CommonUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * Md5
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有找到MD5");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
	
	public static void main(String[] args) {
		String p1=md5("nienan19970219");
		String p2=md5("NIENAN19970219");
		String p3=md5("nienan19970229");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
		System.out.println(md5("nienan19970219"));
		System.out.println(md5("1"));
	}
}
