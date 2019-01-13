package com.fcy.Net.NATTransfromation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-12  16:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Command {
    private int tag;
    private int len;
    private byte[] data;
    public Command(int tag,byte[] bytes){
        this.tag=tag;
        this.data=bytes;
        this.len=bytes.length;
    }
    public Command(int tag,String data){
        this.tag=tag;
        this.data=data.getBytes();
        this.len=this.data.length;
    }
    public void setStringData(String data){
        this.data=data.getBytes();
    }
    public String getStringData(){
        return new String(this.data);
    }
    public static void encode(Command command, DataOutputStream outputStream){
        try {
            outputStream.writeInt(command.tag);
            outputStream.writeInt(command.len);
            outputStream.write(command.data);
            outputStream.flush();
        }catch (IOException e){
            System.out.println("编码失败:"+e.getMessage());
        }
//        System.out.println("send command:"+command.getStringData());
    }
    public static Command decode(DataInputStream inputStream) {
        Command command=new Command();
        int tag=0,len=0;
        byte[] bytes=null;
        try {
            tag = inputStream.readInt();
            len = inputStream.readInt();
            System.out.println("get header:"+tag+":"+len);
            bytes=new byte[len];
            int total=0;
            int onceRead=0;
            while (total<len&&total!=len){
                int ava=Math.min(len,inputStream.available());
                onceRead=inputStream.read(bytes,total,ava);
                if (onceRead!=0&&onceRead!=-1){
                    total+=onceRead;
                }
            }
        }catch (IOException e){
            System.out.println("解码失败:"+e.getMessage());
        }
        command.setTag(tag);
        command.setLen(len);
        command.setData(bytes);
        return command;
    }
}
