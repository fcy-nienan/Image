package AllDemo.java.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Logger;

public class DemoCollections {
    private static Logger logger = Logger.getLogger(DemoCollections.class.getName());

    public static void main(String args[]) throws Exception {
//        返回的不是通常的list
        List emptyList = Collections.EMPTY_LIST;
        Map emptyMap = Collections.EMPTY_MAP;
        Set emptySet = Collections.EMPTY_SET;
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
//        返回的是其内部包装的list
        list = Collections.synchronizedList(list);
        Collections.reverse(list);
        System.out.println(list);
        Collections.swap(list, 1, 8);
        System.out.println(list);
        Collections.fill(list, 1);
        System.out.println(list);



    }
}
