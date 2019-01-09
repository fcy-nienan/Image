package com.fcy.Parser.LEX;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-10  1:53
 */
@Data
public class KeyWord {
    private static int num=1;
    static String[] keyword={"void","char","int","float","double","long","short","boolean"
            ,"byte","if","else","switch","case","return","do","while","for","break","continue"
            ,"volatile","static","final","default"};
    static String[] operator={"{","}","(",")","[","]",",",".",">","<",">=","<=","==","!=",
    "+","-","*","/","++","--","%","&","|","!","+=","*=","-=","%=","/=",";",":","<<",">>",
    "="};


    static String[] typeWord={"void","char","byte","boolean","int","short","long",
    "float","double"};
    static String[] controlWord={"if","else","for","do","while","switch","case","default",
    "break","continue","return"};
    static String[] modifierWord={"static","final","volatile","struct"};
    static String[] operatorWord={"(",")","[","]",",",".",">","<",">=","<=","==","!=",
            "+","-","*","/","++","--","%","&","|","!","+=","*=","-=","%=","/=",";",":","<<",">>",
            "="};
    static String[] startWord={"{","void","char","byte","boolean","int","short","long",
    "float","double","if","for","do","while","switch","return"};
    static String[] endWord={"}",";"};


    static List<Symbol> keywords=new ArrayList<>();
    static List<Symbol> operators=new ArrayList<>();
    public static void init(){
        init(keywords,keyword);
        init(operators,operator);
    }
    public static void init(List list,String[] strings){
        for(int i=0;i<strings.length;i++){
            Symbol symbol=new Symbol(strings[i],++num);
            list.add(symbol);
        }
    }
//    检查是否是类型符
    public static boolean isTypeWord(String str){
        return checkWordType(typeWord,str);
    }
//    检查是否是控制符
    public static boolean isControlWord(String str){
        return checkWordType(controlWord,str);
    }
//    检查是否是修饰符
    public static boolean isModifierWord(String str){
        return checkWordType(modifierWord,str);
    }
//    检查是否是操作符
    public static boolean isOperatorWord(String str){
        return checkWordType(operatorWord,str);
    }
//    检查是否是开始符
    public static boolean isStartWord(String str){
        return checkWordType(startWord,str);
    }
//    检查是否是终结符
    public static boolean isEndWord(String str){
        return checkWordType(endWord,str);
    }
    public static boolean checkWordType(String[] word,String str){
        for(int i=0;i<word.length;i++){
            if(word[i].equals(str))
                return true;
        }
        return false;
    }
}
