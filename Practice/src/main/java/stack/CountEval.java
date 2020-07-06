package stack;

import java.util.Arrays;

public class CountEval {
    public static void main(String[] args) {
        System.out.println(countEval("0&0",0));
        System.out.println(countEval("0&0&0",0));
        System.out.println(countEval("0&0&0&1",0));
        System.out.println(countEval("0&0&0&1^1",0));
        System.out.println(countEval("0&0&0&1^1|0",0));
    }

    static int[][][] dp;
    static char[] str;

    public static int countEval(String s, int result) {
        str = s.toCharArray();
        dp = new int[str.length][str.length][2];
        setCount();
        System.out.println(Arrays.deepToString(dp));
        return dp[0][str.length - 1][result];
    }

    public static void setCount() {

        //对应运算式的长度
        for (int size = 1; size <= str.length; size += 2) {

            //对应运算式的起始点
            for (int i = 0; i + size <= str.length; i += 2) {
                int end = i + size - 1;//运算式终点
                //只有数字，直接填对应的值
                if (size == 1) {
                    dp[i][i][str[i] - 48] = 1;
                    continue;
                }
                //以当前运算符为分界点，分别获取两边运算式的值，填入dp数组
                for (int j = i + 1; j < (i + size); j += 2) {

                    switch (str[j]) {
                        case '&':
                            dp[i][end][1] += dp[i][j - 1][1] * dp[j + 1][end][1];
                            dp[i][end][0] += dp[i][j - 1][1] * dp[j + 1][end][0];
                            dp[i][end][0] += dp[i][j - 1][0] * dp[j + 1][end][1];
                            dp[i][end][0] += dp[i][j - 1][0] * dp[j + 1][end][0];
                            break;
                        case '|':
                            dp[i][end][1] += dp[i][j - 1][1] * dp[j + 1][end][1];
                            dp[i][end][1] += dp[i][j - 1][1] * dp[j + 1][end][0];
                            dp[i][end][1] += dp[i][j - 1][0] * dp[j + 1][end][1];
                            dp[i][end][0] += dp[i][j - 1][0] * dp[j + 1][end][0];
                            break;
                        case '^':
                            dp[i][end][0] += dp[i][j - 1][1] * dp[j + 1][end][1];
                            dp[i][end][1] += dp[i][j - 1][1] * dp[j + 1][end][0];
                            dp[i][end][1] += dp[i][j - 1][0] * dp[j + 1][end][1];
                            dp[i][end][0] += dp[i][j - 1][0] * dp[j + 1][end][0];
                            break;
                        default:
                            System.out.println("error");
                            return;
                    }
                }

            }
        }
    }

    //布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成
    //s = "1^0|0|1", result = 0  0&0&0 & 1^1|0  0
    public static void countEval1(String s, int result) {
        int[][][] dp = new int[s.length()][s.length()][2];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

    }

    private static void helper(int[][][] dp, String s, int start, int end, int result) {
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                for (int k = 0; k < 2; k++) {
                    if (s.charAt(i) == '&') {

                    } else if (s.charAt(i) == '|') {

                    } else if (s.charAt(i) == '^') {

                    }
                }
            }
        }
    }
}
