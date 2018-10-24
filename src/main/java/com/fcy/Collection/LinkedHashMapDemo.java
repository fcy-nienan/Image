package com.fcy.Collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/*
* LinkedHashMap能保证put的数据是有序的
* 通过在每一个Node节点维持两个指针 before和after
* static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
    有序是通过两个指针保证的
* */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        testHashMap();
        testLinkedHashMap();
    }
    public static void testLinkedHashMap(){
        LinkedHashMap<String,String> map=new LinkedHashMap();
        for(int i=0;i<10;i++){
            map.put("key "+i,i+" value");
        }
        Set<Map.Entry<String,String>> entry=map.entrySet();
        entry.forEach(e->{
            System.out.println(e.getKey()+"  "+e.getValue());
        });
    }
    public static void testHashMap(){
        Map<String,String> map=new HashMap<>();
        for(int i=0;i<10;i++){
            map.put("key "+i,i+" value");
        }
        Set<Map.Entry<String,String>> set=map.entrySet();
        set.forEach(e->{
            System.out.println(e.getKey()+"  "+e.getValue());
        });

    }
}
