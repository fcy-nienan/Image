package LeetCode.BackTracking;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Parenthesis {
    private static Logger logger = Logger.getLogger(Parenthesis.class.getName());
    private static int count=0;
    private static int backCount=0;
    public static void main(String args[]) throws Exception {
        int parenthesis=10;

        List<String> result=new ArrayList<>();
        generate(new char[parenthesis*2],0,parenthesis,result);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("normal:"+count);
        result.clear();
        backTrack(new char[parenthesis*2],0,parenthesis,0,0,result);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("back:"+backCount);
    }
    private static void backTrack(char[] chars,int n,int max,int left,int right,List<String> result){
        if (n==max*2){
            result.add(new String(chars));
            return;
        }else{
            if (left<max) {//左边括号的数量小于max个
                chars[n] = '(';
                backTrack(chars, n+1, max, left + 1, right, result);
            }
            if (right<left) {//右边的括号小于左边的括号才继续
                chars[n] = ')';
                backTrack(chars, n+1, max, left, right + 1, result);
            }
        }
        backCount++;
    }
    private static void generate(char[] chars,int n,int max,List<String> result){
        if (n==max*2){
            if (isValid(chars))
            result.add(new String(chars));
            return;
        }else {
            chars[n] = '(';
            generate(chars, n + 1, max,result);
            chars[n] = ')';
            generate(chars, n + 1,max, result);
        }
        count++;
    }
    private static boolean isValid(char[] chars){
        if (chars==null||chars.length%2==1)return false;
        int balance=0;
        for (int i=0;i<chars.length;i++){
            if (balance<0)return false;
            if (chars[i]=='('){
                balance++;
            }else{
                balance--;
            }
        }
        return balance==0;
    }

}
