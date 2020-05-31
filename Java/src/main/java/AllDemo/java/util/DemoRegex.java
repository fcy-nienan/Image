package AllDemo.java.util;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoRegex {
    private static Logger logger = Logger.getLogger(DemoRegex.class.getName());

    public static void main(String args[]) throws Exception {
        Pattern ptn = Pattern.compile("(?<=from )\\btbl_\\w*\\b",Pattern.UNICODE_CASE) ;
        Matcher m = ptn.matcher(" from tbl_123 tbl_345 fromtbl") ;
        while(m.find()){
            System.out.println(m.group());
        }
    }
}
