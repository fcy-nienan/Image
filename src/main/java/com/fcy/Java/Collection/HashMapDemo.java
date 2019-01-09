package com.fcy.Java.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
//        testHashMap();
        testHashMapFourTraversing();
    }
    /*
    * HashMap的四种遍历
    * */
    public static void testHashMapFourTraversing(){
        HashMap<String,String> map=new HashMap<>();
        for(int i=0;i<10;i++){
            map.put("key:"+i,"  "+i+":value");
        }
        System.out.println("First");
        Iterator<Map.Entry<String,String>> iterator=map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry=iterator.next();
            System.out.println(entry.getKey()+entry.getValue());
        }
        System.out.println("Second");
        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+entry.getValue());
        }
        System.out.println("Third");
        Set<String> set=map.keySet();
        set.forEach(e->{
            System.out.println(e+map.get(e));
        });
        System.out.println("Four");
        Iterator<String> keys=map.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            System.out.println(key+map.get(key));
        }
        System.out.println("Only Traversing Values");
        Collection<String> values=map.values();
        Set<String> sets=new HashSet<>(values);
        sets.forEach(e->{
            System.out.println(e);
        });
    }
    /*
    * 测试HashMap的Contains系列方法
    * */
    public static void testHashMap(){
        HashMap<User,User> map=new HashMap<>();
        User u = null;
        for(int i=0;i<10;i++){
            User uu=new User(i+"username",i+"password");
            u=uu;
            map.put(uu,uu);
        }
        Set<Map.Entry<User,User>> set=map.entrySet();
        set.forEach(e->{
            System.out.println(e.getKey()+"  "+e.getValue());
        });
        User utemp=new User("1username", "1password");
        System.out.println(map.containsKey(utemp));
        System.out.println(map.containsValue(utemp));
        System.out.println(map.containsKey(u));
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private String username;
        private String password;
        public boolean equals(Object o){
            User u=(User)o;
            if((u.getPassword().equals(this.password)) &&
                    u.getUsername().equals(this.username))
                return true;
            else
                return false;
        }
        /*public int hashCode(){
            return 0;
        }*/
    }
}

