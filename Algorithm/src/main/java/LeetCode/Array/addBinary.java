package LeetCode.Array;

import java.util.logging.Logger;

public class addBinary {
    private static Logger logger = Logger.getLogger(addBinary.class.getName());

    public static void main(String args[]) throws Exception {
        System.out.println(addBinary("110", "11"));
    }
    public static String addBinary(String a,String b){
        StringBuilder builder=new StringBuilder();
        int caps=0;
        for (int i=a.length()-1,j=b.length()-1;i>=0||j>=0;i--,j--){
            int sum=caps;
            sum+=i>=0?a.charAt(i)-'0':0;
            sum+=j>=0?b.charAt(j)-'0':0;
            builder.append(sum%2);
            caps=sum/2;
        }
        if (caps>0)builder.append(caps);
        return builder.reverse().toString();
    }
}
