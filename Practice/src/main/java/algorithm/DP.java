package algorithm;

import java.util.HashMap;
import java.util.Map;

public class DP {
    public static void main(String[] args) {
        System.out.println(new DP().lengthOfLongestSubstring("abcabcbb"));
    }
//    无重复的最长字串--滑动窗口
    public int lengthOfLongestSubstring(String s) {
        if(s==null){
            return 0;
        }DF
        Map<Character,Integer> map = new HashMap();

        int left = 0;
        int right = 1;
        map.put(s.charAt(left),0);
        while (right < s.length()){
            if (s.charAt(left)!=s.charAt(right)){
                map.put(s.charAt(right),right);
                right++;
            }else{
                left++;
                map.get(s.charAt(right))
            }
        }
        return right - left;
    }
}
