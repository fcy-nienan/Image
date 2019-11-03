package CommonUtil;

import java.util.Random;

public class StringUtil {
    public static void main (String args[]) {
        for (int i=0;i<10;i++){
            System.out.println(getRandomString(10,true));
        }
    }
    public static String getRandomString(int len,boolean needUpper){
        Random random=new Random();
        StringBuilder builder=new StringBuilder();
        int increment=65,x;
        for (int i=0;i<len;i++){
            if (needUpper){
                if (random.nextBoolean()){
                    increment=97;
                }else{
                    increment=65;
                }
            }
            x=random.nextInt(26)+increment;
            builder.append((char)x);
        }
        return builder.toString();
    }
}
