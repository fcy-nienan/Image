package AllDemo.java.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.logging.Logger;

public class DemoCoder {
    private static Logger logger = Logger.getLogger(DemoCoder.class.getName());

    public static void main(String args[]) throws Exception {
        String url = "http://www.baidu.com/tt/q?name=123";
        byte[] encode = Base64.getEncoder().encode(url.getBytes());
        System.out.println(new String(encode));
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(new String(decode));

        byte[] encode1 = Base64.getUrlEncoder().encode(url.getBytes());
        System.out.println(new String(encode1));
        byte[] decode1 = Base64.getUrlDecoder().decode(encode1);
        System.out.println(new String(decode1));

        String encode2 = URLEncoder.encode(url,"utf-8");
        System.out.println(encode2);
        String decode2 = URLDecoder.decode(encode2,"utf-8");
        System.out.println(decode2);

        String encode3 = URLEncoder.encode(url,"gbk");
        System.out.println(encode3);
        String decode3 = URLDecoder.decode(encode2,"gbk");
        System.out.println(decode3);
    }
}
