package stack;

import java.util.EmptyStackException;

public class MinStack<E> {
    int[] element;
    int size;
    int capacity;
    int min=Integer.MAX_VALUE;
    private static final int DEFAULT_CAPACITY=64;
    public MinStack() {
        element=new int[DEFAULT_CAPACITY];
    }

    public void push(int x) {
        int newSize=size+1;
        if (newSize>capacity){
            int newCapacity=capacity+64;
            int[] newValue=new int[newCapacity];
            System.arraycopy(element,0,newValue,0,capacity);
            capacity=newCapacity;
            element=newValue;
        }
        element[size]=x;
        size=newSize;
        min=Math.min(min,x);
    }

    public int pop() {
        if (size<=0)throw new EmptyStackException();
        size=size-1;
        int r=element[size];
        element[size]=0;
        if (r==min){
            min=Integer.MAX_VALUE;
            for(int i=0;i<size-1;i++){
                min=Math.min(min,element[i]);
            }
        }
        return r;
    }

    public Object top() {
        if (size-1<0)throw new EmptyStackException();
        return element[size-1];
    }

    public int getMin() {
        return min;
    }
}
