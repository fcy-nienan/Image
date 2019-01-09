package com.fcy.Java.Charset;

import java.lang.*;

public class Decode
 {
	public static void main(String args[])throws Exception{
		 String username="聂楠";
		 System.out.println(username);
		 
		 System.out.print("\r\n"+"Unicode码   ");
		 for(int i=0;i<username.codePointCount(0,2);i++)
			System.out.print(Integer.toHexString(username.codePointAt(i))+" ");
		 
		System.out.print("\r\n"+"utf-8编码后     ");
		 byte[] utf=username.getBytes("utf-8");
		 for(int i=0;i<utf.length;i++){
			 System.out.print(Integer.toHexString(utf[i]).toUpperCase()+" ");
		 }
		 		 
		 System.out.print("\r\n"+"gbk编码后       ");
		 byte[] gbk=username.getBytes("gbk");
		 for(int i=0;i<gbk.length;i++){
			 System.out.print(Integer.toHexString(gbk[i]).toUpperCase()+" ");
		 }		 
		 
		 System.out.print("\r\n"+"ISO-8859-1编码后");
		 byte[] iso=username.getBytes("ISO-8859-1");
		 for(int i=0;i<iso.length;i++){
			 System.out.print(Integer.toHexString(iso[i]).toUpperCase()+" ");
		 }
		 System.out.println(new String(username.getBytes("ISO-8859-1"),"ISO-8859-1"));
		 System.out.println();
		 String x1=new String(utf,"gbk");
		 String x2=new String(gbk,"utf-8");
		 String x3=new String(gbk,"ISO-8859-1");
		 String x4=new String(utf,"ISO-8859-1");
		 System.out.println(x1);
		 System.out.println(x2);
		 System.out.println(x3);
		 System.out.println(x4);
		 
		 for(int i=0;i<x1.codePointCount(0,2);i++)
			System.out.print(Integer.toHexString(x1.codePointAt(i))+" ");
		 
		 byte[] y1=x1.getBytes("gbk");
		 byte[] y2=x2.getBytes("utf-8");
		 byte[] y3=x3.getBytes("ISO-8859-1");
		 byte[] y4=x4.getBytes("ISO-8859-1");
		 System.out.print("\r\n"+"utf-8编码后gbk解码");
		 for(int i=0;i<y1.length;i++){
			 System.out.print(Integer.toHexString(y1[i]).toUpperCase()+" ");
		 }
		 
		 String yin2=new String(y1,"utf-8");
		 System.out.println("jjkk"+yin2);
		 System.out.print("\r\n"+"gbk编码后utf-8解码     ");
		 for(int i=0;i<y2.length;i++){
			 System.out.print(Integer.toHexString(y2[i]).toUpperCase()+" ");
		 }
		 System.out.print("\r\n"+"gbk编码后ISO-8859-1解码");
		 for(int i=0;i<y3.length;i++){
			 System.out.print(Integer.toHexString(y3[i]).toUpperCase()+" ");
		 }
		 System.out.print("\r\n"+"utf-8编码后ISO-8859-1解码");
		 for(int i=0;i<y4.length;i++){
			 System.out.print(Integer.toHexString(y4[i]).toUpperCase()+" ");
		 }
		 
		 System.out.println(new String(y2,"gbk"));
		 System.out.println(new String(y1,"utf-8"));
		 
	}
}