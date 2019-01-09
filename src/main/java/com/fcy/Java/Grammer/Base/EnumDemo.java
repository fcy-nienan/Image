package com.fcy.Java.Grammer.Base;

import java.util.*;

public class EnumDemo {
    public static void main(String[] args) {
        testEnumImpInterface();
        test t=test.rr;
        System.out.println(t.toString());
    }
    enum myColor{
        RR("红色",1),YY("绿色",2),GG("绿色1",3),BB("黑色",4),WW("白色",5);
        private String name;
        private int index;
        private myColor(String name,int index){
            this.name=name;
            this.index=index;
        }
        public void show(){
            System.out.println("this is enum");
        }
    }
    enum test{
        rr("test","23",1);
        private String name;
        private int index;
        private String password;
        private test(String name,String password,int index){
            this.name=name;
            this.password=password;
            this.index=index;
        }
        public String toString(){
            return this.name+index+password;
        }
    }
    enum UserSort implements Comparator<Integer>{
        ASC{
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        },
        DESC{
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }
    };
    public static void testEnumImpInterface(){
        Random random=new Random();
        List<Integer> all=new ArrayList();
        for(int i=0;i<10;i++){
            all.add(random.nextInt(20));
        }
        Collections.sort(all,UserSort.DESC);
        all.forEach(e->{
            System.out.println(e);
        });
    }
}
