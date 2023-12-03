package interview;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class interview {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first =  br.readLine();
        String second = br.readLine();
        char[] x = first.toCharArray();
        char[] y = second.toCharArray();
        System.out.println(first);
        System.out.println(second);
        System.out.println(count(x,y,x.length-1,y.length-1));

    }
    public static int count(char[] x,char[] y,int i,int j){
        if(i == -1){
            return j+1;
        }
        if(j == -1){
            return i+1;
        }
        System.out.println(x[i] == y[j]);
        if(x[i] == y[j]){
            return count(x,y,i-1,j-1);
        }else{
            return Math.min(Math.min(count(x,y,i,j-1)+1,count(x,y,i-1,j)+1),count(x,y,i-1,j-1)+1);
        }
    }
    public static int compute(int n){
        int[] dp = new int[n+1];
        dp[1] = 0;
        dp[2] = 1;
        for (int i=3;i<=n;i++){
            int factor = i/3;
            int mod = i%3 + factor;
            dp[i] = factor + dp[mod];
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
    public static void bubbleSort(char[] str){
        for (int i=0;i<str.length;i++){
            for (int j=str.length-1;j>=0;j--){
                char c = str[j];
                int compare = str[j];
                if (c>='A' && c<='Z'){
                    compare = compare + 32;
                }
                if (compare < str[i]){
                    char tmp = str[j];
                    str[j] = str[j-1];
                    str[j-1] = tmp;
                }
            }
        }
    }
    public static void positionCompute(String s){
        String[] dots = s.split(";");
        int left_right = 0;
        int up_down = 0;
        for (int i = 0; i < dots.length; i++) {
            String d = dots[i];
            if(d.matches("[ASWD]\\d{1,2}")){
                System.out.println(d);
                char first = d.toCharArray()[0];
                int move = Integer.parseInt(d.substring(1));
//                System.out.println(first+","+move);
                if (first == 'A'){
                    left_right = left_right - move;
                } else if (first == 'D') {
                    left_right = left_right + move;
                } else if (first == 'W') {
                    up_down = up_down + move;
                } else if (first == 'S') {
                    up_down = up_down - move;
                }
            }
        }
        System.out.println("("+left_right+","+up_down+")");
    }
    public static void com() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        TreeSet<Integer> set = new TreeSet();
        int len = Integer.parseInt(br.readLine());
        for(int i=0;i<len;i++){
            int d = Integer.parseInt(br.readLine());
            set.add(d);
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
