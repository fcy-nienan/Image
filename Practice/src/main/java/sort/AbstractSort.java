package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractSort {
    private int sortCount=1000;
    private int arrayLen=100;
    protected List<String> disOrderList=new ArrayList();
    protected List<String> sequenceList=new ArrayList();
    protected List<String> reserveList=new ArrayList();
    protected List<int[]> sortedList=new ArrayList<>();
    protected List<int[]> srcList=new ArrayList<>();
    protected List<Integer> arrayType=new ArrayList<>();
    public AbstractSort sortCount(int sortCount){
        this.sortCount=sortCount;
        return this;
    }
    public int getArrayLen(){
        return this.arrayLen;
    }
    public AbstractSort arrayLen(int arrayLen){
        this.arrayLen=arrayLen;
        return this;
    }
    protected abstract void sort(int[] array);
    public void execute(){
        long start=System.nanoTime();
        long mstart=System.currentTimeMillis();
        int sequenceOrder=0,reserveOrder=0,disOrder=0,sortCount=this.sortCount,arrayLen=this.arrayLen;
        for(int i=0;i<sortCount;i++) {
            int[] array = sortUtil.produceArray(arrayLen);
            srcList.add(Arrays.copyOf(array,array.length));
            sort(array);
            sortedList.add(array);
        }
        long end=System.nanoTime();
        long mend=System.currentTimeMillis();
        for(int[] array:sortedList) {
            int orderType = sortUtil.checkArray2(array);
            arrayType.add(orderType);
        }

        for (int i=0;i<arrayType.size();i++) {
            int orderType=arrayType.get(i);
            int[] array=sortedList.get(i);
            if (orderType == 0) {
                disOrder++;
                disOrderList.add(Arrays.toString(array));
            } else if (orderType == 1) {
                sequenceOrder++;
                sequenceList.add(Arrays.toString(array));
            } else {
                reserveOrder++;
                reserveList.add(Arrays.toString(array));
            }
        }

        System.out.printf("array len: %s --  total count: %s --  sequence order: %s --  reserve order: %s  --  disorder: %s  \r\n",arrayLen,sortCount,sequenceOrder,reserveOrder,disOrder);
        System.out.printf("cost time: %s nano  %s  million \r\n",(end-start),(mend-mstart));
        for (int i = 0; i < arrayType.size(); i++) {
            if (arrayType.get(i)==0){
                System.out.println(Arrays.toString(srcList.get(i)));
                System.out.println(Arrays.toString(sortedList.get(i)));
                break;
            }
        }
    }
}
