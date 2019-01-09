package com.fcy.Parser.LEX;

import java.io.IOException;

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