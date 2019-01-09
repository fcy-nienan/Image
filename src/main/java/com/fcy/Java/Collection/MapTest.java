package com.fcy.Java.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        List<Map<String,Integer>> list=new ArrayList<>();
        for(int j=0;j<5;j++) {
            Map<String, Integer> map = new HashMap();
            for (int i = 0; i < 10; i++) {
                map.put("key" + i, i);
            }
            list.add(map);
        }
        System.out.println(list.size());
        System.out.println(list);
    }
}
