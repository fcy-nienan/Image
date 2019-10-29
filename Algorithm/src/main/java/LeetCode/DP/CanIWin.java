package com.fcy.Algorithm.LeetCode.DP;

import java.util.*;

/**
 * @descripiton:
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。
 * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * 示例：
 * 输入：
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 输出：
 * false
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 * @author: fcy
 * 选择范围x,期望数值y,
 * 玩家A选择一个数m(0<m<x)
 * 对玩家B来说他要选择一个数n(0<n<x),当n+m>y才能赢
 * 可以看做玩家B先选,在{0<x,x!=n,x!=m}中选择一个数,该数大于(y-m)才能赢
 *
 * @date: 2019-03-09  22:45
 */
public class CanIWin {
    public static void main(String[] args) {
        System.out.println(new CanIWin().canIWin(10,20));
        Set<Map.Entry<String,Boolean>> set=memo.entrySet();
        for(Map.Entry<String,Boolean> entry:set){
            String key=entry.getKey();
            boolean value=entry.getValue();
            String[] keys=key.substring(1,key.length()-1).split(",");
            for(int i=0;i<keys.length;i++){
                String kk=keys[i];
                if (kk.trim().equals("true")){
                    System.out.print(i+",");
                }
            }
            System.out.println("----:"+value);
            System.out.println();
        }
    }
    private  static Map<String, Boolean> memo=new HashMap<>(); // key: chosen[] to string, value: canIWinWithSituation return value when chosen to string is key
    public  boolean canIWin(int maxChoosableInteger,int curDesiredTotal){
        if (maxChoosableInteger>20||curDesiredTotal>300)return false;
        if (((1+maxChoosableInteger)*maxChoosableInteger/2<curDesiredTotal))return false;
        if (maxChoosableInteger>=curDesiredTotal)return true;
        boolean[] state=new boolean[maxChoosableInteger+1];
        return can(maxChoosableInteger,curDesiredTotal,state);
    }
    private  boolean can(int maxChoosableInteger,int curDesiredTotal,boolean[] chosen){
        if (curDesiredTotal<=0)
            return false;
        String chooseString=Arrays.toString(chosen);
        if (memo.containsKey(chooseString)){//如果之前搜索过直接返回
            return memo.get(chooseString);
        }
        for(int i=1;i<=maxChoosableInteger;i++){
            if (chosen[i])//如果某个数字已经被选择了直接跳过
                continue;
            chosen[i]=true;//如果没被选择首先将该数字对于的状态置为true
            //然后递归调用,问题变成在maxChoosableInteger中查询curDesiredTotal-i,并且还有一个选择了哪些数字的表
            if (!can(maxChoosableInteger,curDesiredTotal-i,chosen)){
                memo.put(chooseString,true);//如果对方不能赢将这条路径记录下来已访问过
                chosen[i]=false;//将该数字重新设置为false
                return true;//返回true代表自己能赢
            }
            chosen[i]=false;//如果对方这条路径能赢,跳过,重新下一条路径并且将i的状态设置为未被访问
        }
        memo.put(chooseString,false);
        return false;
    }
    private boolean cann(int max,int cur,boolean[] chosen){
        if (cur<=0)return false;
        String choseString=Arrays.toString(chosen);
        if (memo.get(choseString))
            return memo.get(choseString);
        for(int i=1;i<=max;i++){
            if (chosen[i])
                continue;
            chosen[i]=true;
            if (!cann(max,cur-i,chosen)){
                chosen[i]=false;
                memo.put(choseString,true);
                return true;
            }
            chosen[i]=false;
        }
        return false;
    }
}
