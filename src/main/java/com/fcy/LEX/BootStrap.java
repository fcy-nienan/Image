package com.fcy.LEX;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-10  8:30
 */
public class BootStrap {
    public static void main(String args[]) throws IOException {
        KeyWord.init();
        LexParse parse=new LexParse();
        while(parse.getRe().getC()!=-1){
            parse.LexAnalyzer();
        }
        parse.getAllLex().forEach(e->{
            System.out.println(e);
        });
        AnalyLex.dis();
    }
}