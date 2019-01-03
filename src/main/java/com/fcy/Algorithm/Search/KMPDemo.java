package com.fcy.Algorithm.Search;

// 移动位数:=已匹配的字符数 - 对应的部分匹配值
public class KMPDemo{
	private int[] next;
	private String str;
	private String module;
 //next数组求的是一个字符串前缀和后缀最长的共有元素的长度
	public void GetNext(char[] module,int[] next){
		next[0]=0;
		int q,k=0;
		int m=module.length;
		for(q=1;q<m;q++){
			k=next[q-1];
 
			while(module[q]!=module[k]&&k>0){//子串匹配
				System.out.println("子串匹配next数组");
				for(int s:next){
					System.out.print("  "+s);
				}
				System.out.println("子串匹配"+"前k="+k+",q="+q);
				k=next[k-1];
				System.out.println("后k="+k);
			}
			if(module[q]==module[k])//k代表匹配成功的字符数
				next[q]=k+1;
			else
				next[q]=0;
		}
	}
	//
	//ababbdababbdba
	//00120012345600
	public void MyNext(char[] module,int[] next){
		next[0]=0;
		int q,k=0;//q遍历module数组,k代表匹配成功的字符数
		for(q=1;q<module.length;q++){
			while(module[q]!=module[k]&&k>0){
				
			}
			if(module[q]==module[k]){
				k++;
				next[q]=k;
			}else{
				next[q]=0;
				k=0;
			}
			
		}
	}
	public int NoKMP(char[] str,char[] module){
		int m=str.length;
		int n=module.length;
		int loc=-1;
		int count=0;
		for(int i=0;i<m-n;i++){
			for(int j=0;j<n;j++){
				count++;
				System.out.print(count);
				while(str[i+j]!=module[j]){
					loc=-1;
					break;
				}
				loc=i;
			}
		}
		return loc;
	}
	public int KMP(char[] str,char[] module,int[] next){
		int m=str.length;
		int n=module.length;
		GetNext(module,next);//根据待匹配字符串创建next数组(前缀数组)
		int k=0;//k代表匹配成功的字符数
		for(int i=0;i<m;i++){
			System.out.println("i:str[i]"+i+str[i]+"  "+"k:module[k]"+k+module[k]);
			while(k>0&&module[k]!=str[i]){//匹配不成功则寻找子串
				k=next[k-1];//k代表匹配成功的字符数
				System.out.println(k);
			}
			if(str[i]==module[k]){//匹配成功则k++
				k++;
			}
			if(k==n){//k==n说明所有字符都匹配到了
				System.out.println(i-n);
				return i-n;
			}
		}
		return -1;
	}
	public static void main(String args[]){
		char[] str={'b','b','c',' ','a','b','c','d','a','b',' ','a','b','c','d','a','b','c','d','a','b','d','e'};
		char[] module="abaabab".toCharArray();
		int[] next=new int[14];
		new KMPDemo().GetNext(module,next);
		System.out.println("next 数组");
		for(int s:next){
			System.out.print(s);
		}
		/*System.out.println("匹配过程");
		int index=new KMPDemo().NoKMP(str,module);
		for(int i=index;i<index+module.length;i++){
			System.out.print(str[i]);
		}
		System.out.println();
		System.out.println(new KMPDemo().KMP(str,module,next));
		*/
	}
}