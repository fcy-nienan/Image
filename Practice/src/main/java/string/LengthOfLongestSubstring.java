package string;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {

    }
    public int lengthOfLongestSubstring(String s) {
        int[] array = new int[128];
        int left = 0,len = 0;
        for (int i=0;i<s.length();i++){
            left = Math.max(left,array[s.charAt(i)]);
            len = Math.max(len,i-left + 1);
            array[s.charAt(i)] = i + 1;
            //这里最好是都加上一,因为如果只有一个字符,那么计算得到的len = 0
            //如果不是一个字符,那么是否加上一都可以,字符串 abca  array值是 0 1 2 3  计算得到的最大值是3-0 = 3
        }
        return len;
    }
}
