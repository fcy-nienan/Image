package com.fcy.Algorithm.Sort;

public class QuickSort{

     public static void main(String []args){
        System.out.println("Hello World");
        int[] a = {12,20,5,16,15,1,30,45,23,9,9,12,12,12};
		for(int i = 0; i<a.length; i++){
             System.out.print(a[i]+"  ");
         }
		 System.out.println();
        int start = 0;
        int end = a.length-1;
        sort(a,start,end);
        for(int i = 0; i<a.length; i++){
             System.out.println(a[i]);
         }
        
     }
     /*public static void mysort(int[] a,int low,int high){
		 int start=low;
		 int end=high;
		 int key=a[start];
		 while(start<end){
			while(start<end&&a[end]>=key)
					end--;
			if(a[end]<=key){
				int temp=a[start];
				a[start]=a[end];
				a[end]=temp;
			}
			while(start<end&&a[start]<=key)
				start++;
			if(a[start]>=key){
				int temp=a[start];
				a[start]=a[end];
				a[end]=temp;
				
			}
		}
		if(start>low)mysort(a,low,start);
	 }*/
     public static void sort(int[] a,int low,int high){
         int start = low;
         int end = high;
         int key = a[low];
         while(end>start){
             //�Ӻ���ǰ�Ƚ�
             while(end>start&&a[end]>=key)  //���û�бȹؼ�ֵС�ģ��Ƚ���һ����ֱ���бȹؼ�ֵС�Ľ���λ�ã�Ȼ���ִ�ǰ����Ƚ�
			 //end>start�����У����û�У���a[end]>=keyʱend--,������end<startʱ��a[end]>=keyʱ����ִ��end--
                 end--;
			System.out.println(end);
             if(a[end]<=key){
                 int temp = a[end];
                 a[end] = a[start];
                 a[start] = temp;
             }
			 for(int dd = 0; dd<a.length; dd++){
				System.out.print(a[dd]+"  ");
			 }
			 System.out.println();
             //��ǰ����Ƚ�
             while(end>start&&a[start]<=key)//���û�бȹؼ�ֵ��ģ��Ƚ���һ����ֱ���бȹؼ�ֵ��Ľ���λ��
                start++;
			System.out.println(start);
             if(a[start]>=key){
                 int temp = a[start];
                 a[start] = a[end];
                 a[end] = temp;
             }
			  for(int dd = 0; dd<a.length; dd++){
				System.out.print(a[dd]+"  ");
			 }
			 System.out.println();
         //��ʱ��һ��ѭ���ȽϽ�����start=end���ؼ�ֵ��λ���Ѿ�ȷ���ˡ���ߵ�ֵ���ȹؼ�ֵС���ұߵ�ֵ���ȹؼ�ֵ�󣬵������ߵ�˳���п����ǲ�һ���ģ���������ĵݹ����
         }
         //�ݹ���ã��ݹ����ֹ����Ϊstart!=low������ֻʣ���һ��Ԫ��ʱ
         if(start!=low) sort(a,low,start-1);//������С���һ������λ�õ��ؼ�ֵ����-1
		 //ΪʲôҪstart>low��,��Ϊһ��ѭ���ȽϽ�����start-1���������ߵ�С����ĳ���,���ó��ȵ���ԭ�����lowʱ˵������Ѿ��������
         if(end!=high) sort(a,start+1,high);//�ұ����С��ӹؼ�ֵ����+1�����һ��
		 //ͬ��,��end����ԭ�����highʱ˵���ұ����������
     }
     public static void quickSort1(int[] data,int start,int end){
         int low=start,high=end;
         int key=data[low];
         while (low<high){
             while (low<high&&data[low]<=key)
                 low++;
             while (low<high&&data[high]>key){
                 high--;
             }
             if (low<high){
                 int tmp=data[low];
                 data[low]=data[high];
                 data[high]=tmp;
             }
         }
         quickSort1(data,start,low-1);

     }
     
}