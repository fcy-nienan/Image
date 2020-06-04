package Imitate.LEX;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-10  1:39
 */
public class Tools {
    public static void main(String[] args) {

    }
    public static boolean isDigit(char ch){
        int c=(int)ch;
        if(ch>=48&&ch<=57)
            return true;
        else
            return false;

    }
    public static boolean isLetter(char ch){
        if((ch>=65&&ch<=90)||(ch>=97&&ch<=122))
            return true;
        else
            return false;
    }
    public static boolean isUnderline(char ch){
        if(ch=='_')
            return true;
        else
            return false;
    }
    public void getNextChar(){

    }
}
