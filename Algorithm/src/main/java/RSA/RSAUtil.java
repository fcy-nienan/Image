package RSA;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.*;
import java.math.BigInteger; 
import java.math.*;
import java.security.SecureRandom;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

/*算法描述：任何一个自然数，总可以表示成为如下的形式之一：
 6N，6N+1，6N+2，6N+3，6N+4，6N+5 (N=0，1，2，…)
显然，当N≥1时，6N，6N+2，6N+3，6N+4都不是素数，
只有形如6N+1和6N+5的自然数有可能是素数。所以，除了2和3之外， 
所有的素数都可以表示成6N±1的形式(N为自然数)。
*/
public class RSAUtil {
	//寻找两个素数p,q
	public  static BigInteger p=new BigInteger("13");
	public  static BigInteger q=new BigInteger("11");
	
	public  static BigInteger n=new BigInteger("29893");
	public  static BigInteger e=new BigInteger("32823");//e与t=(p-1)*(q-1)互素,e<t
	public  static BigInteger d=new BigInteger("20643");//d*e%t==1
	//公钥 (n,e)=(33,7)
	//私钥 (n,d)=(33,3)
	//加密 c=M^d%n
	//解密 M=c^e%n
	//public int text;//明文
	//public int secret;//密文
	public static long getPrime(long src){//产生e,e<t且e与src互素
		for(int i=0;i<src;i++){
			if(src%i==0)
				continue;
		}
		return 1L;
	}
	public static List toByte(String mes){
		byte[] s=mes.getBytes();
		List k=new ArrayList<BigInteger>();
		for(int i=0;i<s.length;i++)
		{
			k.add(s[i]&0xff);
			BigInteger secret=encrypt((BigInteger)k.get(i));
			BigInteger text=decrypt(secret);
			System.out.println("原文:"+k.get(i)+"加密后:"+secret
			+"解密后:"+text);
		}
		return k;
	}
	public static BigInteger encrypt(BigInteger text){//加密
		BigInteger secret=text.pow(d.intValue()).mod(n);
		return secret;
	}
	public static BigInteger decrypt(BigInteger secret){
		BigInteger text=secret.pow(e.intValue()).mod(n);
		return text;
	}
	public static void RSA(){
		SecureRandom sr=new SecureRandom();
		BigInteger p=BigInteger.probablePrime(256,sr);
		BigInteger q=BigInteger.probablePrime(256,sr);
		BigInteger n=p.multiply(q);
		BigInteger t=p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
		
		BigInteger e=new BigInteger("65537");
		BigInteger d=e.modInverse(t);
		
		System.out.println(encrypt(new BigInteger("110")));
		System.out.println(decrypt(new BigInteger("26429")));
		
		System.out.println(p+"  "+p.bitLength());
		System.out.println(q+"  "+q.bitLength());
		byte[] b1=p.toByteArray();
		//大数是通过
		BigInteger ss=new BigInteger(b1);
		System.out.println(ss);
		for(byte s:b1){
			System.out.print(s);
		}
	}
	public static void main(String args[])throws Exception{
		//System.out.println(encrypt(new BigInteger("24")));
		//System.out.println(decrypt(new BigInteger("24")));
		//System.out.println(toByte("nienan"));
		//RSA();
		RSAUtil.generateKeyPair();
		String x=RSAUtil.encrypt("username");
		System.out.println(RSAUtil.encrypt("username"));
		System.out.println(RSAUtil.decrypt(x));
	}
	public static Integer StringToInt(String str){
		int result=0;
		for(int i=0;i<str.length();i++){
			result+=(int)str.charAt(i);
		}
		return result;
	}




	public static String decrypt(String cryptograph) throws Exception{
		/** 将文件中的私钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./privatekey.keystore"));
		Key key = (Key) ois.readObject();
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b1 = decoder.decodeBuffer(cryptograph);
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}


	public static String encrypt(String source) throws Exception{

		/** 将文件中的公钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./publickey.keystore"));
		Key key = (Key) ois.readObject();
		ois.close();
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b1);
	}
	private static void generateKeyPair() throws Exception{
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(1024, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		/** 用对象流将生成的密钥写入文件 */
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("publickey.keystore"));
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("privatekey.keystore"));
		oos1.writeObject(publicKey);
		oos2.writeObject(privateKey);
		/** 清空缓存，关闭文件输出流 */
		oos1.close();
		oos2.close();
	}
}