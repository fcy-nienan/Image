package leetcode.Day2;

import sort.sortUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution1 {
    public static void main(String[] args)throws Exception {
        System.out.println(gcd(1,3));
        System.out.println(gcd(2,4));
        System.out.println(gcd(3,5));
        System.out.println(gcd(36,24));
        System.out.println(lfg1(27));
        System.out.println(rev("I am a student"));
//        sort2();
        char[][] data=new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words=new String[]{"oath","pea","eat","rain"};
        List<String> words1 = findWords(data, words);
        System.out.println(Arrays.toString(words1.toArray()));
    }
    //求最小公倍数
    public static int gcd1(int x,int y){
        int gcd=gcd(x,y);
        return x*y/gcd;
    }
    //最大公约数 4 6 2   5 7 1
    public static int gcd(int x,int y){
        return (x%y==0)?y:gcd(y,x%y);
    }
    //牛顿迭代法求立方根
    public static double lfg1(double d){
        double x=1;
        double x0=x-(x*x*x-d)/(3*x*x);
        while (Math.abs(x-x0)>0.000000001){
            x=x0;
            x0=x-(x*x*x-d)/(3*x*x);
        }
        return Math.round(x0*10.0)/10.0;
    }
    //字符串反转
    public static String rev(String str){
        return new StringBuilder(str).reverse().toString();
    }
    //最长上身子序列
    public static int LCS(int[] nums){
        int[] dp=new int[nums.length];
        dp[0]=1;
        int result=0;
        for (int i=1;i<nums.length;i++){
            int maxVal=0;
            for (int j=0;j<i;j++){
                if (nums[i]>nums[j]){
                    maxVal=Math.max(maxVal,dp[j]);
                }
            }
            dp[i]=maxVal+1;
            result=Math.max(dp[i],result);
        }
        return result;
    }
    //输入描述:
    //输入一串字符。
    //
    //输出描述:
    //对字符中的
    //各个英文字符（大小写分开统计），数字，空格进行统计，并按照统计个数由多到少输出,如果统计的个数相同，则按照ASII码由小到大排序输出 。如果有其他字符，则对这些字符不用进行统计。
    public static void sort() throws IOException {
        class entry implements Comparable{
            private char c;
            private int count;
            public entry(char c){
                this.c=c;
                this.count=0;
            }
            public entry (char c,int count){
                this.c=c;
                this.count=count;
            }
            @Override
            public int hashCode() {
                return c;
            }

            @Override
            public int compareTo(Object o) {
                entry o1= (entry) o;
                if (o1.count>this.count)return -1;
                if (o1.count<this.count)return 1;
                if (o1.count==this.count){
                    return o1.c-this.c;
                }
                return 0;
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str=br.readLine())!=null){
            char[] chArr = str.toCharArray();
            HashMap<Character,Integer> map=new HashMap();
            for (int i=0;i<chArr.length;i++){
                if (map.containsKey(chArr[i])){
                    map.put(chArr[i],map.get(chArr[i])+1);
                }else{
                    map.put(chArr[i],1);
                }
            }
            entry[] entries=new entry[map.size()];
            Iterator<Map.Entry<Character, Integer>> iterator1 = map.entrySet().iterator();
            int j=0;
            TreeSet<entry> treeSet=new TreeSet<>();
            while (iterator1.hasNext()){
                Map.Entry<Character, Integer> next = iterator1.next();
                entries[j]=new entry(next.getKey(),next.getValue().intValue());
                treeSet.add(entries[j]);
            }
            StringBuilder builder=new StringBuilder();
            Iterator<entry> entryIterator = treeSet.descendingIterator();
            while (entryIterator.hasNext()){
                entry next = entryIterator.next();
                builder.append(next.c).append("--"+next.count).append(",");
            }
            System.out.println(builder.toString());
        }
    }
    public static void sort2()throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str=br.readLine())!=null) {
            char[] chArr = str.toCharArray();
            int[] temp = new int[255];
            for (char c : chArr) {
                temp[c]++;
            }
            int max=0;
            for (int i : temp) {
                if (i>max){
                    max=i;
                }
            }
            StringBuilder builder=new StringBuilder();
            while (max>0){
                for (int i=0;i<temp.length;i++){
                    if (temp[i]==max){
                        builder.append((char)i);
                    }
                }
                max--;
            }
            System.out.println(builder.toString());
        }
    }

    //堆排序
    public static void heapSort(int[] nums){
        for (int i=nums.length/2-1;i>=0;i--){
            adjustHeap(nums,i,nums.length);
        }
        for (int i=1;i<nums.length;i++){
            sortUtil.swap(nums,0,nums.length-i);
            adjustHeap(nums,0,nums.length-i);
        }
    }
    public static void adjustHeap(int[] nums,int current,int len){
        for (int i=current*2+1;i<len;i=current*2+1){
            if (i+1<len&&nums[i+1]>nums[i]){
                i=i+1;
            }
            if (nums[i]>nums[current]){
                int temp=nums[i];
                nums[i]=nums[current];
                nums[current]=temp;
                current=i;
            }else{
                break;
            }
        }
    }
    public static void sortAD(int[] nums,int len,int flag){
        if (flag==0){
            selectAsc(nums);
        }else if (flag==1){
            insertDesc(nums);
        }
    }
    public static void selectAsc(int[] array){
        for (int i=0;i<array.length-1;i++){
            int minIndex=i;
            for (int j=i+1;j<array.length;j++){
                if (array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            swap(array,i,minIndex);
        }
    }
    public static void insertDesc(int[] array){
        for (int i=1;i<array.length;i++){
            int j=i-1;
            int temp=array[i];
            while (j>=0&&temp>array[j]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=temp;
        }
    }
    private static void swap(int[] nums,int left,int right){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }
    public static List<String> findWords(char[][] board, String[] words) {
        int[] temp=new int[255];
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                temp[board[i][j]]++;
            }
        }
        List<String> result=new ArrayList<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            boolean flag=true;
            for (char aChar : chars) {
                if (temp[aChar]<=0){
                    flag=false;
                }
            }
            if (flag){
                for (char aChar : chars) {
                    temp[aChar]--;
                }
                result.add(word);
            }
        }
        return result;
    }
}
