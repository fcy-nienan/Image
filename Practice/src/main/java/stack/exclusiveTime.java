package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
//n = 2
//        logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
//["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
public class exclusiveTime {
    public static void main(String[] args) {
        int n=2;
        List<String> logs=new ArrayList<>();
        logs.add("0:start:0");
        logs.add("0:start:2");
        logs.add("0:end:5");
        logs.add("1:start:6");
        logs.add("1:end:6");
        logs.add("0:end:7");
        int[] ints = exclusiveTime(n, logs);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] exclusiveTime(int n, List<String> logs) {
        Stack<int[]> stack=new Stack();
        int[] res=new int[n];
        for (int i=0;i<logs.size();i++){
            String s = logs.get(i);
            String[] split = s.split(":");
            int start=Integer.parseInt(split[0]);
            String mid=split[1];
            int end=Integer.parseInt(split[2]);
            if (mid.equals("start")){
                stack.push(new int[]{start,end});
            }else{
                int[] peek = stack.pop();
                int exclusive=end-peek[1]+1;
                res[peek[0]]+=exclusive;
                if (!stack.empty()){
                    res[stack.peek()[0]]-=exclusive;
                }
            }
        }
        return res;
    }
}
