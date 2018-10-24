package com.fcy.Algorithm;

public class MathUtil{
	public static int HCF(int a,int b){//求最大公约数
		if(a==0&&b==0)return 0;
		if(a==0)return b;
		if(b==0)return a;
		int x=0;
		if(a<b){//使a为大数,b位小数
			int temp=a;
			a=b;
			b=temp;
		}
		while(a%b!=0){//辗转相除法
			int tem=a%b;
			a=b;
			b=tem;	
		}
		return b;
	}
	public static int LCD(int a,int b){//求最小公倍数
		int x=HCF(a,b);
		if(x==0)return 0;
		return a*b/x;
	}
	public static int Max(int a,int b){
		return a>b?a:b;
	}
	public static void main(String args[]){
		System.out.println(HCF(3,5));
		System.out.println(LCD(0,0));
	}
}