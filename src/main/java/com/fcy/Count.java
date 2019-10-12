package com.fcy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-19  14:37
 */
public class Count {
    public static final String FILE_COUNT="total";
    public static Map<String,Long> countMap=new ConcurrentHashMap<>(128);
//split函数,第二个参数是需要切分的长度,如果1则直接返回原数组,如果2则先切分第一个,然后把之后的所有当作另一个
//    数组只有两个元素,但是由于第二个是余下的所有数据,所以打印的时候和切分所有一样
    public static void main(String[] args) {
        String s="1|@|2|@|3|@|4|@|5|@|";
        String[] arr=s.split("\\|\\@\\|",-1);
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
        System.out.println(s.split(",",3).length);
    }
}
