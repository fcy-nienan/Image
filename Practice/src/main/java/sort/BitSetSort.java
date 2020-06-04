package sort;

import CommonUtil.IOUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.HashMap;
import java.util.logging.Logger;

@Slf4j
public class BitSetSort {
    private static Logger logger = Logger.getLogger(BitSetSort.class.getName());
    //33554432 long类型数组大小
    public static void main(String args[]) throws Exception {
        BitSetSortDemo();
        outerSort.Verify.valid("E:\\outerSort\\BitSetSortedData");

    }
    public static void fillBitSet(BitSet bitSet,HashMap<Integer,Integer> map,int[] array) throws InterruptedException {
        try {
            for (int i = 0; i < array.length; i++) {
                int value = array[i];
                if (bitSet.get(value)) {
                    if (map.get(value) != null) {
                        map.put(value, map.get(value) + 1);
                    } else {
                        map.put(value, 1);
                    }
                } else {
                    bitSet.set(value);
                }
            }
        }catch (Throwable t){
            System.out.println(bitSet.size());
            System.out.println(bitSet.length());
            t.printStackTrace();
            Thread.sleep(1000000);
            System.exit(0);
        }
    }
    public static void computeNeedSize(long maxValue){
        long nums=maxValue/64;
        DecimalFormat format=new DecimalFormat("0.00");
        float KB=  (float)maxValue/8f/1024;
        float MB=  (float)maxValue/8f/1024/1024;
        float GB=  (float)maxValue/8f/1024/1024/1024;
        String KBS=format.format(KB);
        String MBS=format.format(MB);
        String GBS=format.format(GB);
        logger.info("最大数字:"+maxValue+",long数组个数:"+nums+" 大小:"+KBS+"K,"+MBS+"M,"+GBS+"G");
    }
    public static void BitSetSortDemo() throws IOException, InterruptedException {
        BitSet bitSet=new BitSet();
        HashMap<Integer,Integer> map=new HashMap();
        String path="E:\\outerSort\\data";
        long maxValue=maxValue(path);
        computeNeedSize(maxValue);

        BufferedReader reader= IOUtil.BufferedReader(path);
        String msg=null;
        while ((msg= reader.readLine())!=null){
            if (!"".equals(msg)){
                int[] ints = transferToIntArray(msg);
                msg=null;
                fillBitSet(bitSet,map,ints);
            }
        }
        logger.info("填充完毕!"+map.size());

        forBitSet1(bitSet,map);
    }
    public static long maxValue(String path) throws IOException {
        BufferedReader reader= IOUtil.BufferedReader(path);
        String msg=null;
        long maxValue=Long.MIN_VALUE;
        while ((msg=reader.readLine())!=null){
            long[] ints=transferToLongArray(msg);
            msg=null;
            for (long anInt : ints) {
                if (maxValue<anInt){
                    maxValue=anInt;
                }
            }
        }
        return maxValue;
    }
    public static void forBitSet1(BitSet bitSet,HashMap<Integer,Integer> map) throws IOException, InterruptedException {
        System.gc();
        StringBuilder builder=new StringBuilder();
        int i=bitSet.nextSetBit(0);


        if (i==-1)return;
        builder.append(i);

        int threshold=1000;
        int thresholdCount=1;
        BufferedWriter writer = IOUtil.BufferedWriter("E:\\outerSort\\BitSetSortedData");


        while (true){
            i++;
            i= bitSet.nextSetBit(i);
            if (i<0)break;
            if (map.containsKey(i)){
                int count=map.get(i);
                for (int j=0;j<count;j++){
                    builder.append(",").append(i);
                    thresholdCount++;
                }
                map.remove(i);
            }
            builder.append(",").append(i);
            thresholdCount++;
            if (thresholdCount==threshold){
                writer.write(builder.toString());
                writer.newLine();
                writer.flush();
                builder.delete(0,builder.length());
                thresholdCount=0;
            }
        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();
        builder=null;
    }
    //将一个字符串转化为一个int数组
    private static int[] transferToIntArray(String msg){
        String[] split = msg.split(",");
        int[] intArr=new int[split.length];
        for (int i = 0; i < split.length; i++) {
            intArr[i]=Integer.parseInt(split[i]);
        }
        return intArr;
    }
    private static long[] transferToLongArray(String msg){
        String[] split=msg.split(",");
        long[] longArr=new long[split.length];
        for (int i=0;i<longArr.length;i++){
            longArr[i]=Long.parseLong(split[i]);
        }
        return longArr;
    }
}
