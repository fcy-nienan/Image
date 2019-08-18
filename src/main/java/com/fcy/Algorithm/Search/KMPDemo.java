package com.fcy.Algorithm.Search;

import java.util.Arrays;

// 移动位数:=已匹配的字符数 - 对应的部分匹配值
public class KMPDemo{
 //next数组求的是一个字符串前缀和后缀最长的共有元素的长度
	public int[] GetNext(char[] dest){
		int[] next = new int[dest.length];
		next[0] = 0;
		for(int i = 1,j = 0; i < dest.length; i++){
			while(j > 0 && dest[j]!= dest[i]){
				j = next[j - 1];
			}
			if(dest[i] == dest[j]){
				j++;
			}
			next[i] = j;
		}
		System.out.println(Arrays.toString(next));
		return next;
	}
	public int KMP(char[] str,char[] dest){
		int[] next=GetNext(dest);
		for(int i=0,j=0;i<str.length;i++){
			while(j>0&&str[i]!=dest[j]){
				j=next[j-1];
			}
			if(str[i]==str[j]){
				j++;
			}
			if(j==dest.length){
				return i-j+1;
			}
		}

		for(int i = 0, j = 0; i < str.length; i++){
			while(j > 0 && str[i] != dest[j]){
				j = next[j - 1];
			}
			if(str[i] == dest[j]){
				j++;
			}
			if(j == dest.length){
				return i-j+1;
			}
		}
		return 0;
	}
	public static void main(String args[]){
		String src="ablsjdfskskldfjafsdf",dst="skl";
		int index=kmpD(src,dst);
		System.out.println(index);
	}
	public static int[] getNext(String src){
		int[] next=new int[src.length()];
		next[0]=0;
		for(int i=1,j=0;i<src.length();i++){
			while(j>0&&src.charAt(i)!=src.charAt(j)){
				j=next[j-1];
			}
			if(src.charAt(i)==src.charAt(j)){
				j++;
			}
			next[i]=j;
		}
		return next;
	}
	public static int[] getNext1(String src){
		int[] next=new int[src.length()];
		next[0]=0;
		for(int i=1,j=0;i<src.length();i++){
			while(j>0&&src.charAt(i)!=src.charAt(j)){
				j=next[j-1];
			}
			if(src.charAt(i)==src.charAt(j)){
				j++;
			}
			next[i]=j;
		}
		return next;
	}
	public int kmp(String src,String dst){
		int[] next=getNext1(src);
		int k=0;
		for(int i=0;i<dst.length();i++){
			while(k>0&&src.charAt(k)!=dst.charAt(k)){
				k=next[k-1];
			}
			if(src.charAt(k)==dst.charAt(i)){
				k++;
			}
			if(k==src.length()){
				return i-k+1;
			}
		}
		return -1;
	}

    public static int[] getNext3(String dst){
	    int[] next=new int[dst.length()];
	    next[0]=0;
	    for(int i=1,j=0;i<dst.length();i++){
	        while(j>0&&dst.charAt(i)!=dst.charAt(j)){
	            j=next[j-1];
            }
	        if (dst.charAt(i)==dst.charAt(j)){
	            j++;
            }
	        next[i]=j;
        }
	    return next;
    }
    public static int kmp3(String src,String dst){
	    int[] next=getNext3(dst);
	    for(int i=0,j=0;i<src.length();i++){
	        while(j>0&&src.charAt(i)!=dst.charAt(j)){
	            j=next[j-1];
            }
	        if (src.charAt(i)==dst.charAt(j)){
	            j++;
            }
	        if (j==dst.length()){
	            return i-j+1;
            }
        }
	    return -1;
    }
    public static int[] getNext4(String dst){
		int[] next=new int[dst.length()];
		next[0]=0;
		for(int i=1,j=0;i<dst.length();i++){
			while(j>0&&dst.charAt(i)!=dst.charAt(j)){
				j=next[j-1];
			}
			if (dst.charAt(i)==dst.charAt(j)){
				j++;
			}
			next[i]=j;
		}
		return next;
	}
	public static int kmp4(String src,String dst){
		int[] next=getNext4(dst);
		for(int i=0,j=0;i<src.length();i++){
			while(j>0&&src.charAt(i)!=dst.charAt(j)){
				j=next[j-1];
			}
			if (src.charAt(i)==dst.charAt(j)){
				j++;
			}
			if (j==dst.length()){
				return i-j+1;
			}
		}
		return -1;
	}






























	public static int[] getNext2(String dst){
		int[] next=new int[dst.length()];
		next[0]=0;
		for(int i=1,j=0;i<dst.length();i++){
			while(j>0&&dst.charAt(i)!=dst.charAt(j)){
				j=next[j-1];
			}
			if (dst.charAt(i)==dst.charAt(j)){
				j++;
			}
			next[i]=j;
		}
		return next;
	}
	public static int kmpD(String src,String dst){
		int[] next=getNext2(dst);
		for(int i=0,j=0;i<src.length();i++){
			while(j>0&&src.charAt(i)!=dst.charAt(j)){
				j=next[j-1];
			}
			if (src.charAt(i)==dst.charAt(j)){
				j++;
			}
			if (j==dst.length()){
				return i-j+1;
			}
		}
		return -1;
	}
}