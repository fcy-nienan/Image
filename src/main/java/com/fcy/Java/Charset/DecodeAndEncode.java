package com.fcy.Java.Charset;

public class DecodeAndEncode{
	public static void main(String args[])throws Exception{
		String s="聂楠";
		byte[] sk=s.getBytes("gb2312");
		System.out.println(s);
		StringBuilder si=new StringBuilder();
		for(int i=0;i<sk.length;i++)
		{
			 si.append(Integer.toHexString(sk[i]));
		System.out.println(Integer.toHexString(sk[i]).toUpperCase());
	    }

		System.out.println(si);
		String str="聂楠";
        byte utfByte[]=str.getBytes("UTF-8");
        byte gbkByte[]=str.getBytes("GBK");
        byte isoByte[]=str.getBytes("ISO-8859-1");
        //UTF->GBK->UTF
        String str11=new String(utfByte,"GBK");
        String str12=new String(str11.getBytes("GBK"),"UTF-8");
        System.out.println(str12);
        //UTF->ISO->UTF
        String str21=new String(utfByte,"ISO-8859-1");
        String str22=new String(str21.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(str22);
        //GBK->UTF->GBK
        String str31=new String(gbkByte,"UTF-8");
        String str32=new String(str31.getBytes("UTF-8"),"GBK");
        System.out.println(str32);
        //GBK->ISO->GBK
        String str41=new String(gbkByte,"ISO8859-1");
        String str42=new String(str41.getBytes("ISO8859-1"),"GBK");
        System.out.println(str42);
	}
}