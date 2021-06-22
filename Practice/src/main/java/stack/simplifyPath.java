package stack;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class simplifyPath {
    public static void main(String args[]) throws Exception {
        System.out.println(simplifyPath("/a/b/../c/./d"));
        System.out.println(simplifyPath("/a/b/../c/./d"));



    }
    public static String simplifyPathII(String path){
        return Paths.get(path).normalize().toString();
    }
    public static String simplifyPath(String path) {
        String[] splits=path.split("\\/");
        Stack<String> stack=new Stack<>();
        for(String s:splits){
            if (s.equals("")) {
                continue;
            }else if (s.equals(".")) {

            }else if (s.equals("..")){
                if (!stack.empty()){
                    stack.pop();
                }
            }else{
                stack.push(s);
            }
        }
        if (stack.empty())return "/";
        StringBuilder builder=new StringBuilder();
        builder.append("/");

        for(int i=0;i<stack.size();i++){
            builder.append(stack.get(i));
            builder.append("/");
        }
        builder.deleteCharAt(builder.length()-1);

        return builder.toString();
    }

}
