package com.fcy.Net.NATTransfromation;

import java.util.Arrays;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-13  18:07
 */
public class BaseUtil {
//    public static byte[] addBytes(byte[] var1,byte[] var2){
//        byte[] bytes=new byte[var1.length+var2.length];
//        System.arraycopy(var1,0,bytes,0,var1.length);
//        System.arraycopy(var2,0,bytes,var1.length,var2.length);
//        return bytes;
//    }
    public static byte[] addBytes(byte[] ...var){
        int len=0;
        for(int i=0;i<var.length;i++){
            len+=var[i].length;
        }
        byte[] bytes=new byte[len];
        len=0;
        for(int i=0;i<var.length;i++){
            byte[] var2=var[i];
            System.arraycopy(var2,0,bytes,len,var2.length);
            len+=var2.length;
        }
        return bytes;
    }
    public static void main(String[] args) {
        byte[] var1=new byte[]{'a','b','c','1'};
        byte[] var2=new byte[]{'4','6','9','0'};
        byte[] var3=new byte[]{'1','2','3','4','5'};
        byte[] bytes=addBytes(var1,var2,var3);
        System.out.println(Arrays.toString(bytes));
    }

}
