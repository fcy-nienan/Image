package AllDemo.java.util;

import CommonUtil.BitUtil;

import java.util.Arrays;

public class DemoEncoding {
    public static void main(String[] args)throws Exception {
        String name="聂楠";
        System.out.println(BitUtil.bytesToBinary(name.getBytes()));
        System.out.println(BitUtil.bytesToBinary(name.getBytes("gbk")));
        System.out.println(BitUtil.bytesToHexString(name.getBytes()));
        System.out.println(BitUtil.bytesToHexString(name.getBytes("gbk")));
    }
}
