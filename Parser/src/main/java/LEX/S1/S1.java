package com.fcy.Parser.LEX.S1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-06  12:25
 */
public class S1 {
    public static void main(String[] args) throws FileNotFoundException {
        boolean debug=false;
        Scanner inFile=new Scanner(new File("G:\\test.s"));
        PrintWriter outFile=new PrintWriter("G:\\test.a");

        outFile.println("; from S1 compiler written by ...");

        S1SymTab st=new S1SymTab();
        S1TokenMgr tm=new S1TokenMgr(inFile,outFile,debug);
        S1CodeGen cg=new S1CodeGen(outFile,st);
        S1Parser parser=new S1Parser(st,tm,cg);
        parser.parse();
        outFile.close();

    }
}
interface S1Constants{
    int EOF=0;
    int PRINTLN=1;
    int UNSIGNED=2;
    int ID=3;
    int ASSIGN=4;
    int SEMICOLON=5;
    int LEFTPAREN=6;
    int RIGHTPAREN=7;
    int PLUS=8;
    int MINUS=9;
    int TIMES=10;
    int DIVIDE=11;
    int ERROR=12;
    String[] tokenImage={
            "<EOF>",
            "\"println\"",
            "<UNSIGNED>",
            "<ID>",
            "\"=\"",
            "\";\"",
            "\"(\"",
            "\")\"",
            "\"+\"",
            "\"-\"",
            "\"*\"",
            "\"/\"",
            "<ERROR>"
    };
}
