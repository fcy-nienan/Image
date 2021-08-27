package CommonUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BitUtil {
    public static void main (String args[]) throws IOException {
        int i=11111;
        byte[] bytes=null;
        String name="聂楠";
        System.out.println(bytes2hex02(name.getBytes()));
//        int to byte
        BigInteger integer=BigInteger.valueOf(i);
        bytes = integer.toByteArray();
        System.out.println(bytes.length);

//        int to byte
        ByteBuffer buffer=ByteBuffer.allocate(4);
        buffer.putInt(i);
        bytes = buffer.array();

//        int to byte
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(i);
        dos.flush();
        bytes = bos.toByteArray();

        //        byte to int
        buffer=ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.BIG_ENDIAN);
        System.out.println(buffer.getInt());

    }
    public static String bytes2hex02(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        for (byte b : bytes)
        {
            // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
            tmp = Integer.toHexString(0xFF & b);
            if (tmp.length() == 1)// 每个字节8为，转为16进制标志，2个16进制位
            {
                tmp = "0" + tmp;
            }
            sb.append(tmp);
        }

        return sb.toString();

    }
    public static int[] byteArrayToIntArray(byte[] bytes){
        int[] value=new int[bytes.length/4];
        for (int i=0;i<bytes.length/4;i++){
            value[i]=byteToInt(new byte[]{
                    bytes[i*4],
                    bytes[i*4+1],
                    bytes[i*4+2],
                    bytes[i*4+3]
            });
        }
        return value;
    }
    public static byte[] intArrayToByteArray(int[] data){
        byte[] bytes=new byte[data.length*4];
        for (int i=0;i<data.length;i++){
            byte[] bytes1 = intToByte(i);
            System.arraycopy(bytes1,0,bytes,i*4,4);
        }
        return bytes;
    }
    public static int byteToInt(byte[] bytes,ByteOrder order){
        if (order==ByteOrder.BIG_ENDIAN){
            return byteToIntBig(bytes);
        }else{
            return byteToIntLittle(bytes);
        }
    }
    public static byte[] intToByte(int i,ByteOrder order){
        if (order.equals(ByteOrder.BIG_ENDIAN)){
            return intToBytesBig(i);
        }else{
            return intToBytesLittle(i);
        }
    }
    public static byte[] intToByte(int i){
        return intToByte(i,ByteOrder.nativeOrder());
    }
    public static int byteToInt(byte[] bytes){
        return byteToInt(bytes,ByteOrder.nativeOrder());
    }
    private static int byteToIntLittle(byte[] bytes){
        return (bytes[3]&0xff)<<24|
                (bytes[2]&0xff)<<16|
                (bytes[1]&0xff)<<8|
                (bytes[0]&0xff);
    }
    private static int byteToIntBig(byte[] bytes){
        return (bytes[0]&0xff<<24)|
                (bytes[1]&0xff<<16)|
                (bytes[2]&0xff<<8)|
                (bytes[3]&0xff);
    }
    private static byte[] i2b(int i){
        byte[] bytes=new byte[4];
        bytes[0]=(byte)((i>>24)&0xff);
        bytes[1]=(byte)((i>>16)&0xff);
        bytes[2]=(byte)((i>>8)&0xff);
        bytes[3]=(byte)(i&0xff);
        return bytes;
    }
    private static int b2i(byte[] bytes){
        int i=(bytes[0]&0xff)<<24;
        i+=(bytes[1]&0xff)<<24;
        i+=(bytes[2]&0xff)<<8;
        i+=(bytes[3]&0xff);
        return i;
    }
    private static byte[] intToBytesLittle(int i){
        byte[] bytes=new byte[4];
        bytes[3]=(byte)(i>>>24&0xff);
        bytes[2]=(byte)(i>>>16&0xff);
        bytes[1]=(byte)(i>>>8&0xff);
        bytes[0]=(byte)(i&0xff);
        return bytes;
    }
    private static byte[] intToBytesBig(int i){
        byte[] bytes=new byte[4];
        bytes[0]=(byte)(i>>>24&0xff);
        bytes[1]=(byte)(i>>>16&0xff);
        bytes[2]=(byte)(i>>>8&0xff);
        bytes[3]=(byte)(i&0xff);
        return bytes;
    }
    public static int setOne(int i,int index){
        return i|(1<<index);
    }
    public static int cleanZero(int i,int index){
        return i&~(1<<index);
    }
    public static String bytesToBinary(byte[] bytes){
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return binary.toString();
    }
    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();
    public static String bytesToHexString(byte[] bytes){
        StringBuilder r = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }
}
