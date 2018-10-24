package me.struct.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-07-29  11:20
 */
public class MyMap {
    private transient Node[] table;
//    private transient int capacity;
    private transient int size;
    private transient float loadFactor;
    private transient int thrshold;
    private static final float DEFAULT_LOAD_FACTOR=0.75f;
    private static final int DEFAULT_CAPACITY=1<<4;
    private static final int MAX_CAPACITY=1<<30;
    public MyMap(){
        this.loadFactor=DEFAULT_LOAD_FACTOR;
    }
    public MyMap(int cap,float loadFactor){
        thrshold=tableSizeFor(cap);
        this.loadFactor=loadFactor;
    }
    /**
    *@descripiton 返回大于给定的值并距离给定的值的最近的2的整数幂的一个数
     * eg: put 8 return 8
     * put 7 return 8
     * put 9 return 16
     * put 15 return 16
    */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAX_CAPACITY) ? MAX_CAPACITY : n + 1;
    }
    static class Node{
        Object key;
        Object value;
        Node next;
        public Node(Object key,Object value,Node next){
            this.key=key;
            this.value=value;
            this.next=next;
        }
    }
    public int getHash(Object key){
        int h=(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return h;
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public void put(Object key,Object value){
        int len=0;
        if(table==null||(len=table.length)==0){
            len=resize().length;
        }
        int index=getHash(key);
//        System.out.println("index:"+((len-1)&index));
        Node inserted=new Node(key,value,null);
        if(table[(len-1)&index]==null){
            table[(len-1)&index]=inserted;
        }else{//hash clash
            linkedInsert((len-1)&index,inserted);
        }
        ++size;
        if(size>thrshold){
            resize();
        }
    }
    public Object get(Object key){
        int index=getHash(key);
        Node node=table[(table.length-1)&index];
        for(;;){
            if(node==key||(node.hashCode()==key.hashCode()&&node.equals(key))){
                return node.value;
            }
            node=node.next;
        }
    }
    public Node[] resize(){
        int oldCap,oldThr;
        int newCap=0,newThr=0;
        oldCap=table==null?0:table.length;
        oldThr=thrshold;
        Node[] oldTab=table;
        if (oldCap > 0) {
            if (oldCap >= MAX_CAPACITY) {//如果旧的容量大于0并且大于最大容量
                thrshold = Integer.MAX_VALUE;//设置扩容阈值为最大容量的大约两倍
                return oldTab;//直接返回
            }
            else if ((newCap = oldCap << 1) < MAX_CAPACITY &&
                    oldCap >= DEFAULT_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAX_CAPACITY && ft < (float)MAX_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
        }
//        if(oldCap==0){//刚开始初始化
//            if(oldThr>0){
//                newCap=oldThr;
//                newThr= (int) (newCap*loadFactor);
//            }else {
//                newCap = DEFAULT_CAPACITY;
//                newThr = (int) (oldCap * loadFactor);
//            }
//        }else if(oldCap>0){
//            if(oldCap>MAX_CAPACITY){
//                newCap=Integer.MAX_VALUE;
//                newThr=Integer.MAX_VALUE;
//            }else{
//                newCap*=2;
//                newThr= (int) (newCap*loadFactor);
//            }
//        }
        Node[] newTab=new Node[newCap];
        thrshold=newThr;
        table=newTab;
        System.out.println("capacity:"+newCap+" thrshold:"+newThr);
        if(oldTab!=null){
            for(int i=0;i<oldCap;i++){
                Node node=oldTab[i];
                if(node!=null) {
                    if (node.next != null) {//如果链表不为空
                        for (; ; ) {//循环取出链表中的数据并复制到新数组中
                            if (node.next == null)
                                break;
                            int newIndex = getHash(node);
                            linkedInsert((newCap-1)&newIndex, node);
                            node = node.next;
                        }
                    } else {
                        int newIndex = getHash(node.key);
                        newTab[(newCap-1)&newIndex] = node;
                    }
                }
            }
        }
        return newTab;
    }
    private void linkedInsert(int index,Node inserted){
        Node node=table[index];
        if(node==null){
            table[index]=new Node(inserted.key,inserted.value,null);
            return;
        }
        for(;;){
            if(node.next==null)
                break;
            node=node.next;
        }
        node.next=new Node(inserted.key,inserted.value,null);
    }
    /*
    * put操作的时候要判断容量
    * 如果需要则要进行扩容操作
    * 扩容后又要进行重新哈希操作
    * put操作的时候还需要判断是否哈希冲突
    * 如果哈希冲突的时候则挂载在最后的一个链表节点上
    * */
    public static void main(String args[]) {
        testMyMap();
//        test();

    }
    static void testMyMap(){
//        1073741824
        MyMap map=new MyMap();
        for(int i=0;i<1073741824;i++){
            map.put("key"+i,"value"+i);
        }
        System.out.println(map.table.length);
    }
    static void test(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1<<30);
        System.out.println(tableSizeFor(12));
        System.out.println(tableSizeFor(10));
        System.out.println(tableSizeFor(9));
        System.out.println(tableSizeFor(8));
        System.out.println(tableSizeFor(7));
    }
}
