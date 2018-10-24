package com.fcy.LEX;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-10  2:06
 */
public class Reader {
    @Getter
    private int c;
    @Getter
    private int row=0;
    @Getter
    private StringBuilder strToken=new StringBuilder();
    private int index=0;
    RandomAccessFile randomAccessFile;
    static final String fileName="H:\\Thread1.txt";
    public void init(String name){
        try {
            randomAccessFile=new RandomAccessFile(name,"rw");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
    }
    public void clear(){
        strToken=new StringBuilder();
    }
    public Reader(){
        init(fileName);
    }
    public Reader(String name){
        init(name);
    }
    public void getNextChar(){
        try {
            c=randomAccessFile.read();
            index++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(c=='\n'||c==-1)
            row++;
    }
    public void getNotSpaceChar(){
        while(c==' '||c=='\n'||c=='\t'||c=='\r'){
            getNextChar();
        }
    }
    public void concat(){
        strToken.append((char)c);
    }
    public void retract(){
        try {
            randomAccessFile.seek(--index);
            c=' ';
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Tokens checkTypes(String str){
        if(KeyWord.isTypeWord(str))
            return Tokens.Type;
        if(KeyWord.isControlWord(str))
            return Tokens.Control;
        if(KeyWord.isModifierWord(str))
            return Tokens.Modifier;
        else if(KeyWord.isOperatorWord(str))
            return Tokens.Operator;
        else
            return Tokens.Variable;
    }
    public Tokens checkIsReserveKeyword(){
//        return checkIsReserveKeyword(strToken,KeyWord.keyword.length);
        return checkTypes(strToken.toString());
    }
    public Tokens checkIsReserveOperator(){
        return checkTypes(strToken.toString());
    }
//    检查是否是保留字符
    public int checkIsReserveKeyword(StringBuilder str,int end){
        for(int i=0;i<end;i++){
            Symbol symbol=KeyWord.keywords.get(i);
            if(str.toString().equals(symbol.getStr()))
                return symbol.getCoding();
        }
        return 0;
    }
//    检查是否是操作符
    public int checkIsReserveOperator(StringBuilder str){
        for(int i=0;i<KeyWord.operator.length;i++){
            Symbol symbol=KeyWord.operators.get(i);
            if(str.toString().equals(symbol.getStr()))
                return symbol.getCoding();
        }
        return 0;
    }
    public void error(String msg){
        error(msg,row,c);
    }
    public void error(String msg,int row){
        error(msg,row,c);
    }
    public void error(String msg,int r,int cc){
        System.out.println("*********Error*********");
        System.out.println("Row "+r+" "+msg+(char)cc);
        System.out.println("*********Error*********");
        System.exit(0);
    }
    public  boolean isDigit(){
        return isDigit(c);
    }
    public boolean isDigit(int c){
        if(c>=48&&c<=57)
            return true;
        else
            return false;
    }
    public  boolean isLetter(){
        if((c>=65&&c<=90)||(c>=97&&c<=122))
            return true;
        else
            return false;
    }
    public  boolean isUnderline(){
        if(c=='_')
            return true;
        else
            return false;
    }
}
