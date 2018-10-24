package com.fcy.Algorithm;
/*
* 求素数
* */
public class prime{
	private static void prime2(int num) {  
			long start = System.currentTimeMillis();  
			for (int n = 2; n <= 3; n++) {  
				System.out.print(n + " ");  
			}  
			label1: for (int n = 1;; n++) {  
				label2: for (int m = 0; m <= 1; m++) {  
					int tmp = 2 * (3 * n + m) - 1;  
					if (tmp > num)  
						break label1;  
					for (int k = 2; k * k <= tmp; k++)  
						if (tmp % k == 0)  
							if (m == 0)  
								continue label2;  
							else  
								continue label1;  
					System.out.print(tmp + " ");  
				}  
			}  
			System.out.println();  
			System.out.print("时间：");  
			long end = System.currentTimeMillis();  
			System.out.println(end - start);  
	}  
	public static void main(String args[]){
		prime2(1000000);
		for(int i=1;i<=999883;i++){
			if(999883%i==0){
				System.out.print(i);
			}
		}
	}
}