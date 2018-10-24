package me.struct.List;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-07-29  10:55
 */
public class MyList {
    private transient Object[] elementDate;
    private static final Object[] DEFAULT_EMPTY_DATE={};
    private static final int DEFAULT_CAPACITY=10;
    private int capacity;
    private int size;
    public MyList(){
        elementDate=DEFAULT_EMPTY_DATE;
    }
    public MyList(int capacity){
        elementDate=new Object[capacity];
    }
    public void add(Object obj){
        checkCapacity(size+1);
        elementDate[size++]=obj;
    }

    public Object get(int index)throws ArrayIndexOutOfBoundsException{
        checkIndex(index);
        Object obj=elementDate[index];
        return obj;
    }
    public void checkCapacity(int grow){
        int expansion=Math.max(elementDate.length,DEFAULT_CAPACITY);
        if(expansion<grow)
            expansion=grow;
        if(grow>capacity) {
            this.elementDate = Arrays.copyOf(elementDate, expansion);
        }
    }
    public void checkIndex(int index)throws ArrayIndexOutOfBoundsException{
        if(index>size){
            throw new ArrayIndexOutOfBoundsException(index+"    :"+size);
        }
    }

    public static void main(String[] args) {
        MyList list=new MyList();
        for(int i=0;i<30;i++){
            list.add("sdf");
        }
        for(int j=0;j<30;j++){
            System.out.println(list.get(j));
        }
    }
}
