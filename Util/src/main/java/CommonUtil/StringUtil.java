package CommonUtil;

import java.util.Random;

public class StringUtil {
    public static boolean isNotBlank(String s){
        return "".equals(s);
    }
    public static String getRandomNumber(int len){
        Random random=new Random();
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<len;i++){
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
    public static String getRandomString(int len){
        return getRandomString(len,true);
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
