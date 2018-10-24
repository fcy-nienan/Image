package com.fcy.Util;

public class MyStringUtil{
	public static String captureName(String name,boolean flag){//首字符大小写
		char[] cs=name.toCharArray();
		if(flag)//true
			cs[0]-=32;//大写
		else//false
			cs[0]+=32;//小写
		return String.valueOf(cs);
	}
	public static String captureName(String name) {//首字符大写
       name = name.substring(0, 1).toUpperCase() + name.substring(1);
       return  name;
      
    }
}