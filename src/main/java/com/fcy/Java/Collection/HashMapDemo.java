package com.fcy.Java.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class HashMapDemo<K,V> {
    private Node<K,V>[] table;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node<K,V>{
        private int hash;
        private K key;
        private V value;
        private Node<K,V> next;
        public boolean equals(Object o){
            if (o==this)
                return true;
            if (o instanceof Map.Entry){
                Map.Entry entry= (Map.Entry) o;
                if (Objects.equals(entry.getKey(),key)&&Objects.equals(entry.getValue(),value)){
                    return true;
                }
            }
            return false;
        }
    }
//    x=o.hashCode=0000111111110000 1111111100000000
//    h>>>16=                       0000111111110000
//    result=      0000111111110000 1111000011110000
//    So we apply a transform that spreads the impact of higher bits downward
//    所以我们应用一个转换,向下扩展更高位的影响,使得参与运算的尽量是低16位
    public static int hash(Object o){
        int h=0;
        return o==null?0:(h=o.hashCode())^(h>>>16);
    }
    public static void main(String[] args) {

    }
    public Node<K,V>[] resize(){
        int size=0;
        return null;
    }
    public void put(int hash,K k,V v,boolean onlyAbsend,boolean evict){
        int len=0,i;
        Node<K,V>[] tab;
        if ((tab=table)==null||tab.length==0){
            len=(tab=resize()).length;
        }
        Node<K,V> p;
        if ((p=tab[i=(hash&(len-1))])==null){
            p=new Node<>(hash,k,v,null);
        }
    }
//    该方法使得给定的容量都为大于他的最近的二次幂,给定10,返回16,给定7,返回8
//    大于,最近
    public static int tableSizeFor(int cap){
        int n=cap-1;
        n|=n>>>1;//无符号右移一位并或
        n|=n>>>2;
        n|=n>>>4;
        n|=n>>>8;
        n|=n>>>16;
        //上诉使得某数的最高位后面都为1
        return n<0?1:n>Integer.MAX_VALUE?Integer.MAX_VALUE:n+1;
    }
}

