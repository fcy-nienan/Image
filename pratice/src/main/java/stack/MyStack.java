package stack;

import java.util.Arrays;

public class MyStack {
    private Object[] objects;
    private int size;
    public MyStack(int capacity){
        if (capacity<=0){
            throw new IllegalArgumentException("capacity can not be negative!");
        }
        this.objects=new Object[capacity];
    }
    public MyStack(){
        this(10);
    }
    public void push(Object o){
        ensureCapacity(size+1);
        objects[size]=o;
        size++;
    }
    private void ensureCapacity(int minSize){
        if (minSize>objects.length){
            grow(minSize);
        }
    }
    private void grow(int minSize){
        int oldSize=size;
        int newSize=oldSize+oldSize/2;
        if (newSize<minSize){
            newSize=minSize;
        }
        if (newSize>Integer.MAX_VALUE){
            throw new OutOfMemoryError();
        }
        objects=Arrays.copyOf(objects,newSize);
    }
    public Object pop(){
        ensureStackEmpty();
        size--;
        Object r=objects[size];
        objects[size]=null;
        return r;
    }
    public int size(){
        return size;
    }
    public boolean empty(){
        return size==0;
    }
    private void ensureStackEmpty(){
        int size=size();
        if (size==0){
            throw new NullPointerException("stack is empty!");
        }
    }
    public Object peek(){
        ensureStackEmpty();
        return objects[size-1];
    }
}
