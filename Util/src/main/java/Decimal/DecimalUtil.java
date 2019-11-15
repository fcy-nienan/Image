package Decimal;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DecimalUtil {
    public static void main (String args[]) throws IOException {
        int i=11111;
        byte[] bytes=new byte[4];


//        int to byte
        BigInteger integer=BigInteger.valueOf(i);
        bytes = integer.toByteArray();
        System.out.println(bytes.length);

//        byte to int
        ByteBuffer buffer=ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.BIG_ENDIAN);
        System.out.println(buffer.getInt());

//        int to byte
        buffer=ByteBuffer.allocate(4);
        buffer.putInt(i);
        bytes = buffer.array();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(i);
        dos.flush();
        bytes = bos.toByteArray();

    }
    public static int byteToIntLittle(byte[] bytes){
        return (bytes[3]&0xff)<<24|
                (bytes[2]&0xff)<<16|
                (bytes[1]&0xff)<<8|
                (bytes[0]&0xff);
    }
    public static int byteToIntBig(byte[] bytes){
        return (bytes[0]&0xff<<24)|
                (bytes[1]&0xff<<16)|
                (bytes[2]&0xff<<8)|
                (bytes[3]&0xff);
    }
    public static byte[] intToBytesLittle(int i){
        byte[] bytes=new byte[4];
        bytes[3]=(byte)(i>>>24&0xff);
        bytes[2]=(byte)(i>>>16&0xff);
        bytes[1]=(byte)(i>>>8&0xff);
        bytes[0]=(byte)(i&0xff);
        return bytes;
    }
    public static byte[] intToBytesBig(int i){
        byte[] bytes=new byte[4];
        bytes[0]=(byte)(i>>>24&0xff);
        bytes[1]=(byte)(i>>>16&0xff);
        bytes[2]=(byte)(i>>>8&0xff);
        bytes[3]=(byte)(i&0xff);
        return bytes;
    }
}
