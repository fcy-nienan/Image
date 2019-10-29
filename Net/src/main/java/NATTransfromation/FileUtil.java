package com.fcy.Net.NATTransfromation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static void save(byte[] bytes,String path){
        File file=new File(path);
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                fileOutputStream=null;
            }
        }
    }
}
