package com.fcy.Algorithm;

import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public class CRC {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        CRC32 crc32=new CRC32();
        crc32.update("a".getBytes());
        System.out.println(Integer.toHexString((int)crc32.getValue()));
        int i=3;
        i*=4+1;
        System.out.println(i);
        int k=Integer.parseInt("10110010000",2);
        int p=Integer.parseInt("11001",2);
        System.out.println("k:"+k+" p:"+p);
        Integer m=k^p;
        System.out.println(Integer.toBinaryString(m));
    }
    public static int parseString(String s,int ra){
        int result=0;
        int radix=ra;
        for(int i=0;i<s.length();i++){
            int digit=Character.digit(s.charAt(i),radix);
            result=result*radix;
            System.out.println("result"+result);
            result+=digit;
            System.out.println("after:"+result);
        }
        return result;
    }
    /*
    * 123=  0*10+1   1*10+2  12*10+3
    * 1111=  0*2+1   1*2+1   3*2+1   7*2+1
    * 111=7  7个2然后加上个位数上的1等于15
    * 12=12  12个10然后加上个位数上的3扥估123
    * */
    public static String Make_CRC(byte[] data) {
        byte[] buf = new byte[data.length];// 存储需要产生校验码的数据
        for (int i = 0; i < data.length; i++) {
            buf[i] = data[i];
        }
        int len = buf.length;
        int crc = 0xFFFF;//16位
        for (int pos = 0; pos < len; pos++) {
            if (buf[pos] < 0) {
                crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of
                // crc
            } else {
                crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc
            }
            for (int i = 8; i != 0; i--) { // Loop over each bit
                if ((crc & 0x0001) != 0) { // If the LSB is set
                    crc >>= 1; // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                } else
                    // Else LSB is not set
                    crc >>= 1; // Just shift right
            }
        }
        String c = Integer.toHexString(crc);
        if (c.length() == 4) {
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 3) {
            c = "0" + c;
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 2) {
            c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
        }
        return c;
    }
}
