package com.fcy.Algorithm.Sort;

public class MyQuick{
	public static void main(String []args){
        System.out.println("Hello World");
        int[] a = {12,20,5,16,15,1,30,45,23,23,9,12,9};
		for(int i = 0; i<a.length; i++){
             System.out.print(a[i]+"  ");
         }
		 System.out.println();
        int start = 0;
        int end = a.length-1;
		System.out.println(a[end]+"  "+start);
        sort(a,end,start);
        for(int i = 0; i<a.length; i++){
             System.out.println(a[i]);
         }
        
     }
	public static void sort(int[] arr,int high,int low){
		int key=arr[low];
		int start=low;
		int end=high;
		while(end>start){
			while(end>start&&arr[end]>=key)
				end--;
			if(arr[end]<=key){
				int temp=arr[start];
				arr[start]=arr[end];
				arr[end]=temp;
			}
			while(end>start&&arr[start]<=key)
				start++;
			if(arr[start]>=key){
				int temp=arr[start];
				arr[start]=arr[end];
				arr[end]=temp;
			}
		}
		if(start!=low)sort(arr,start-1,low);
		if(end!=high)sort(arr,high,start+1);
	}
}