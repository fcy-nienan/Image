package com.fcy.Algorithm.Cache;
public class LRU {
    private Object[] objects;
    private Integer[] longs;
    private int index=0;
    public LRU(int size){
        this.objects=new Object[size];
    }
    public static void main(String[] args) {
        LRU lru=new LRU(10);

    }
    public void push(Object object){
        if (index<objects.length&&objects[index]!=null){
            objects[index]=object;
            longs[index++]=0;
        }else{
            for(int i=0;i<longs.length;i++){

            }
        }
    }
    public Object get(int position){
        Object object=objects[position];
        longs[position]++;
        return object;
    }
}