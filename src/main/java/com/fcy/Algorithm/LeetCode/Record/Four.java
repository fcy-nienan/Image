package com.fcy.Algorithm.LeetCode.Record;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-19  23:49
 */
public class Four {
    public static void main(String[] args) {
        System.out.println(numDecodings("2265321"));
    }
    public static int numDecodings(String s) {
        if (s.length() < 1)
            return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        char[] ch = s.toCharArray();
        int count = 2;
        while (count <= s.length()) {
            int sum = (int) (ch[count - 2] - '0') * 10 + (int) (ch[count - 1] - '0');
            if (ch[count - 1] != '0') {//当后面一位数字大于0时，可以提前执行 dp[i]=dp[i-1];
                dp[count] = dp[count - 1];
            }
            //再次判定是否满足条件，加上dp[i-2]即可
            if (sum <= 26 && sum >= 10) {
                dp[count] += dp[count - 2];
                count++;
            }
        }
            System.out.println(Arrays.toString(dp));
            return dp[s.length()];
    }
}
