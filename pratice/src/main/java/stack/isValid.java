package stack;

import java.util.Stack;
import java.util.logging.Logger;

//示例 1:
//输入: "()"
//输出: true
//示例 2:
//输入: "()[]{}"
//输出: true
//示例 3:
//输入: "(]"
//输出: false
//示例 4:
//输入: "([)]"
//输出: false
//示例 5:
//输入: "{[]}"
//输出: true
public class isValid {
    private static Logger logger = Logger.getLogger(isValid.class.getName());

    public static void main(String args[]) throws Exception {
        String[] array=new String[]{"(((","){","({[]})"};
        for (String s:array) {
            System.out.println(isValid(s));
        }
    }
    public static boolean isValid(String s) {
        if(s==null||s.length()%2==1)return false;
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (c=='('||c=='['||c=='{'){
                stack.push(c);
            }else if (c==')'){
                if (stack.empty())return false;
                char t=stack.pop();
                if (t!='('){
                    return false;
                }
            }else if (c==']'){
                if (stack.empty())return false;
                char t=stack.pop();
                if (t!='['){
                    return false;
                }
            }else if (c=='}'){
                if (stack.empty())return false;
                char t=stack.pop();
                if (t!='{'){
                    return false;
                }
            }else{
                return false;
            }
        }
        if (stack.empty())return true;
        return false;
    }
}
