package MultiAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-13  19:50
 */
public class MultiAddressDemo {
    public static void main(String[] args) throws IOException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface=networkInterfaces.nextElement();
            int mtu=networkInterface.getMTU();
            if (mtu==-1)continue;
            System.out.println(networkInterface);
            System.out.println("MTU:"+mtu);
            byte[] bytes=networkInterface.getHardwareAddress();
            if (bytes!=null){
                System.out.println("MAC:"+ showByteAdHex(bytes));
            }
            System.out.println("----------inetAddresses-----------");
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            if (inetAddresses!=null){
                while (inetAddresses.hasMoreElements()){
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println(inetAddress.getHostAddress());
                }
            }
        }
    }
    private static String showByteAdHex(byte[] bytes){
        StringBuilder builder=new StringBuilder();
        builder.append("(");
        for(int i=0;i<bytes.length;i++){
            builder.append(Integer.toHexString(bytes[i]&0xff));
            builder.append(":");
        }
        builder.deleteCharAt(builder.lastIndexOf(":"));
        builder.append(")");
        return builder.toString();
    }

}
