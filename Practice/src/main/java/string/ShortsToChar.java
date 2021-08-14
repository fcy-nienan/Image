package string;

import java.util.Arrays;

public class ShortsToChar {
    public static void main(String[] args) {
        String s="loveleetcode";
        char c='e';
        int[] ints = new ShortsToChar().shortestToChar(s, c);
        System.out.println(Arrays.toString(ints));
    }
    public int[] shortestToChar(String s, char c) {
        int[] answer = new int[s.length()];
        // 需要额外注意临界条件,为什么是MAX/2和MIN/2?
        // 不一定是这两个MAX 两倍的strLength也可以,但如果字符串有MAX大小呢?
        // 两倍的strLength就越界了
        int prev=Integer.MIN_VALUE/2;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)==c)prev=i;
            answer[i] = i-prev;
        }

        prev = Integer.MAX_VALUE/2;
        for (int i=s.length()-1;i>=0;i--){
            if (s.charAt(i)==c)prev=i;
            answer[i]=Math.min(answer[i],prev-i);
        }
        return answer;
    }
}
