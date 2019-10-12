package com.fcy.BigData.Hadoop;


import java.io.*;
import java.util.Random;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-28  19:30
 */
public class PrepareFile {
    public static void main(String[] args) throws IOException {
        createSparkFile();
    }
    public static void createSparkFile() throws IOException {
        String path="G:\\words.txt";
        String[] content={"java","c","c++","scala","php","python","c#","js"};
        String[] split={" ","\t","\f","\n","\r"};
        Random random=new Random();
        File file=new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        for(int i=0;i<10000000;i++){
            int index=random.nextInt(10);
            for(int j=0;j<index;j++) {
                int innerIndex=random.nextInt(7);
                writer.write(content[innerIndex]);
                writer.write(split[0]);
            }
            writer.write(split[4]);
        }
        writer.close();
    }
}
