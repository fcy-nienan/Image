package FileStateCheck;

import java.io.File;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-19  2:15
 */
public class EveryDay {
    public static void main(String[] args) throws Exception {
        File file=new File("D:\\","sdlfkjsdf");
        byte b=127;
        int y=8923+b;
        byte b1=-100;
        System.out.println(Integer.toBinaryString(b1));
        int z=y+b1;
        System.out.println(z);

        System.out.println(y);


    }
}
