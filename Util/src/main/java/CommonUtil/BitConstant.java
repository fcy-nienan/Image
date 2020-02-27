package CommonUtil;

import java.util.logging.Logger;

public class BitConstant {
    public static int setOne(int i,int index){
        return i|(1<<index);
    }
    public static int cleanZero(int i,int index){
        return i&~(1<<index);
    }
    public static int StringToBinary(String str){
        int r=0;
        char[] chars=str.toCharArray();
        for (int i=0;i<chars.length;i++) {
            char aChar=chars[i];
            if (aChar=='1'){
                r|=(1<<i);
            }
        }
        return r;
    }
}
