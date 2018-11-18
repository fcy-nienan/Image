package com.fcy.Algorithm.LeetCode;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-16  1:17
 */
public class Match {
    public static void main(String[] args) {

    }
    public static boolean ddMathc(char[] s,int sndex,char[] p,int pndex){
        if (sndex<=s.length&&pndex<=p.length&&s[sndex]==p[pndex])
            return true;
        if (sndex==s.length&&p.length==pndex){
            return true;
        }
        if (p[pndex]=='*'){
            return ddMathc(s,sndex+1,p,pndex-1)
                    ||ddMathc(s,sndex+1,p,pndex)
                    ||ddMathc(s,sndex+1,p,pndex+1);
        }
        return true;
    }
}
