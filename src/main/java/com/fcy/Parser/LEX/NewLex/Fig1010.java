package com.fcy.Parser.LEX.NewLex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-06  11:44
 */
public class Fig1010 {
    public static void main(String[] args) throws IOException {
//        if (args.length!=1){
//            System.out.println("Wrong number cmd line args");
//            System.exit(1);
//        }
        File file=new File("G:\\test");
        file.createNewFile();
        String content="+ 17 * 2 5";
        FileOutputStream outputStream=new FileOutputStream(file);
        outputStream.write(content.getBytes("UTF-8"));
        outputStream.close();

        Scanner infile=new Scanner(file);
        Fig1010TokenMgr tm=new Fig1010TokenMgr(infile);
        Fig1010Parser parser=new Fig1010Parser(tm);
        try{
            parser.parse();
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }


    }
}
interface Fig1010Constants{
    int EOF=0;
    int UNSIGNED=1;
    int PLUS=2;
    int MINUS=3;
    int TIMES=4;
    int DIVIDE=5;
    int ERROR=6;
    String[] tokenImage={
            "<EOF>",
            "<UNSIGNED>",
            "\"+\"",
            "\"-\"",
            "\"*\"",
            "\"/\"",
            "<ERROR>"
    };
}
