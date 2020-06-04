package Imitate.RSA;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/*算法描述：任何一个自然数，总可以表示成为如下的形式之一：
 6N，6N+1，6N+2，6N+3，6N+4，6N+5 (N=0，1，2，…)
显然，当N≥1时，6N，6N+2，6N+3，6N+4都不是素数，
只有形如6N+1和6N+5的自然数有可能是素数。所以，除了2和3之外， 
所有的素数都可以表示成6N±1的形式(N为自然数)。
*/
public class RSADemo{
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
	public static void main(String args[]){
		//System.out.println(encrypt(new BigInteger("24")));
		//System.out.println(decrypt(new BigInteger("24")));
		//System.out.println(toByte("nienan"));
		RSA();
	}
}