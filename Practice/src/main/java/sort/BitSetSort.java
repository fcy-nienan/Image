package sort;

import CommonUtil.IOUtil;
import CommonUtil.StartWatch;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.HashMap;
import java.util.logging.Logger;

@Slf4j
public class BitSetSort {
    private static Logger logger = Logger.getLogger(BitSetSort.class.getName());
    private static String waitForSort="E:\\outerSort\\data";
    private static String sortedPath="E:\\outerSort\\BitSetSortedData";
    //33554432 long类型数组大小
    public static void main(String args[]) throws Exception {
        outerSort.PrepareFile.PrepareFile(waitForSort,10000000,11111111,4000);
        BitSetSortDemo();
        outerSort.Verify.valid(sortedPath);
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
            System.out.println(map.size());
            t.printStackTrace();
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
        StartWatch startWatch=new StartWatch();
        startWatch.init();
//        long maxValue=maxValue(waitForSort);
//        computeNeedSize(maxValue);
//        startWatch.cost("computeMaxValue");

        BufferedReader reader= IOUtil.BufferedReader(waitForSort);
        String msg=null;
        while ((msg= reader.readLine())!=null){
            if (!"".equals(msg)){
                int[] ints = transferToIntArray(msg);
                fillBitSet(bitSet,map,ints);
            }
        }
        logger.info("填充完毕!"+map.size());
        startWatch.cost("fillData");
        forBitSet(bitSet,map);
        startWatch.cost("writeToFile");
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
    public static void forBitSet(BitSet bitSet,HashMap<Integer,Integer> map) throws IOException, InterruptedException {
        StringBuilder builder=new StringBuilder();
        int i=bitSet.nextSetBit(0);

        if (i==-1)return;
        builder.append(i).append(",");

        int threshold=1000;
        int thresholdCount=1;
        BufferedWriter writer = IOUtil.BufferedWriter(sortedPath);

        while (true){
            i++;
            i= bitSet.nextSetBit(i);
            if (i<0)break;
            builder.append(i).append(",");
            thresholdCount++;
            if (thresholdCount==threshold){
                clearBuilderAndFlush(builder,writer);
                thresholdCount=0;
            }
            if (map.containsKey(i)){
                int count=map.get(i);
                for (int j=0;j<count;j++){
                    builder.append(i).append(",");
                    thresholdCount++;
                    if (thresholdCount==threshold){
                        clearBuilderAndFlush(builder,writer);
                        thresholdCount=0;
                    }
                }
                map.remove(i);
            }
        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }
    private static void clearBuilderAndFlush(StringBuilder builder, BufferedWriter writer) throws IOException {
        builder.deleteCharAt(builder.length()-1);
        writer.write(builder.toString());
        writer.newLine();
        writer.flush();
        builder.delete(0,builder.length());
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
