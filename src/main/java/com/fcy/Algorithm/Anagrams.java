package com.fcy.Algorithm;

import java.util.*;

public class Anagrams{
	public static void main(String args[]){
		String s="yycc";
		//System.out.println(s.charAt(0)+s.charAt(1));
		//real_deal(args);
		System.out.println(comparae("4321","1234"));
		char[] sc={'a','y','c','2','1','0','t','s','r','f','b','c'};
		string_quick_sort(sc,0,sc.length-1);
		for(char sss:sc){
			System.out.print(sss+"  ");
		}
		
	}
	public static int[][] pre_deal(String[] data){
		int[][] s=new int[data.length][2];
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length();j++){
				s[i][0]+=data[i].charAt(j);
				s[i][1]=i;
			}
		}
		return s;
	}
	public static void real_deal(String[] data){
		int[][] ascii=pre_deal(data);
		for(int i=0;i<ascii.length;i++){
			System.out.println(ascii[i][0]);
		}
		mysort(ascii,0,ascii.length-1);
		
		for(int i=0;i<ascii.length;i++){
			for(int j=0;j<ascii[i].length;j++){
				System.out.print(ascii[i][j]+"  ");
			}
			System.out.println();
		}
		display(ascii,data);
		
	}
	public static void display(int[][] ascii,String[] data){
		int i=0;
		int j=0;
		int key=ascii[0][0];
		while(i<data.length){
			while(key!=ascii[i][0]){
				System.out.println(i+"  "+j);
				if(j>=3){
					while(j>0){
						System.out.print(data[ascii[j-1][1]]+"   ");
						j--;
					}
				}
				if(j==2){
					//System.out.println(data[ascii[i-j][1]]+"  "+data[ascii[i-j+1][1]]);
					if(comparae(data[ascii[i-j][1]],data[ascii[i-j+1][1]]))
						System.out.println(data[ascii[i][1]]+"  "+data[ascii[i+1][1]]);
					j-=2;
				}
				if(j==1){
					j=0;
				}
				key=ascii[i][0];
				System.out.println(key);
			}
			j++;
			i++;
		}
	}
	public static boolean comparae(String a,String b){
		if(a.length()!=b.length())return false;
		if(a==null&&b==null)return true;
		if(a==null||b==null)return false;
		boolean[] com={false,false,false,false};
		boolean[] temp={false,false,false,false};
		int count=0;
		for(int i=0;i<a.length();i++){
			for(int j=0;j<b.length();j++){
				//System.out.println(a.charAt(i)+"  "+b.charAt(j));
				if(a.charAt(i)-(b.charAt(j))==0&&temp[j]==false){
					com[i]=true;
					temp[j]=true;
					break;
					//System.out.println(com[i]);
				}
			}
		}
		for(boolean s:com){
			if(s==false)
				return false;
		}
		return true;
	}
	public static void find_half(String data){
		
	}
	public static void string_quick_sort(char[] data,int low,int high){
		int start=low;
		int end=high;
		char key=data[low];
		while(start<end){
			while(start<end&&data[end]>=key)
				end--;
			if(data[end]<=key){
				char temp=data[start];
				data[start]=data[end];
				data[end]=temp;
			}
			while(start<end&&data[start]<=key)
				start++;
			if(data[start]>=key){
				char temp=data[start];
				data[start]=data[end];
				data[end]=temp;
			}
			if(start!=low)string_quick_sort(data,low,start-1);
			if(end!=high)string_quick_sort(data,start+1,high);
		}
	}
	public static void second_quick_sort(int[] data,int low,int high){
		int start=low;
		int end=high;
		int key=data[start];
		while(start<end){
			while(start<end&&data[end]>=key)
				end--;
			if(data[end]<=key){
				int temp=data[start];
				data[start]=data[end];
				data[end]=temp;
			}
			while(start<end&&data[start]<=key)
				start++;
			if(data[start]>=key){
				int temp=data[start];
				data[start]=data[end];
				data[end]=temp;
			}
			if(start!=low)second_quick_sort(data,low,start-1);
			if(end!=high)second_quick_sort(data,start+1,high);
		}
	}
	public static void quick_sort(int[] data,int low,int high){
		int start=low;
		int end=high;
		int key=data[low];
		while(start<end){
			while(start<end&&key<=data[end])
				end--;
			if(key>data[end]){
				int temp=data[start];
				data[start]=data[end];
				data[end]=temp;
			}
			while(start<end&&data[start]<=key)
				start++;
			if(data[start]>key){
				int temp=data[start];
				data[start]=data[end];
				data[end]=temp;
			}
		}
		if(start!=low)quick_sort(data,low,start-1);
		if(end!=high)quick_sort(data,start+1,high);
	}
	public static void mysort(int[][] data,int low,int high){
		int start=low,end=high;
		int key=data[start][0];
		while(start<end){
			while(start<end&&data[end][0]>=key)
				end--;
			if(data[end][0]<=key){
				int temp0=data[start][0];
				data[start][0]=data[end][0];
				data[end][0]=temp0;
				
				int temp1=data[start][1];
				data[start][1]=data[end][1];
				data[end][1]=temp1;
			}
			while(start<end&&data[start][0]<=key)
				start++;
			if(data[end][0]>=key){
				int temp=data[start][0];
				data[start][0]=data[end][0];
				data[end][0]=temp;
				
				int temp1=data[start][1];
				data[start][1]=data[end][1];
				data[end][1]=temp1;
			}
			if(start!=low)mysort(data,low,start-1);
			if(end!=high)mysort(data,start+1,high);
		}
	}
}