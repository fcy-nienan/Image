package stack;

import java.util.Stack;
import java.util.logging.Logger;

//150. 逆波兰表达式求值
//示例 1：
//
//输入: ["2", "1", "+", "3", "*"]
//输出: 9
//解释: ((2 + 1) * 3) = 9
//示例 2：
//
//输入: ["4", "13", "5", "/", "+"]
//输出: 6
//解释: (4 + (13 / 5)) = 6
//示例 3：
//
//输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
//输出: 22
//解释:
//  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22
public class evalRPN {
    private static Logger logger = Logger.getLogger(evalRPN.class.getName());

    public static int evalRPN(String[] tokens) {
        Stack<String> stack=new Stack<>();
        for(String s:tokens){
            if (s.equals("+")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=x+y;
                stack.push(String.valueOf(z));
            }else if (s.equals("-")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=y-x;
                stack.push(String.valueOf(z));
            }else if (s.equals("*")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=x*y;
                stack.push(String.valueOf(z));
            }else if (s.equals("/")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=y/x;
                stack.push(String.valueOf(z));
            }else{
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
