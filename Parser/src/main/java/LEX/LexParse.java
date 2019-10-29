package com.fcy.Parser.LEX;

package LEX;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-10  2:01
 */
public class LexParse {
    @Getter
    private Reader re;
    @Getter
    private List<StringBuilder> allLex=new ArrayList<>();
    public LexParse(){
        re=new Reader();
    }
    public void LexAnalyzer(){
        int code;
        re.clear();
        re.getNextChar();
        re.getNotSpaceChar();
        switch(re.getC()){
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '_':
                while(re.isLetter()||re.isDigit()||re.isUnderline()){
                    re.concat();
                    re.getNextChar();
                }
                re.retract();
                Tokens tokens=re.checkIsReserveKeyword();
                AnalyLex.results.add(new LexResult(tokens,re.getStrToken().toString()));
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                while(re.isDigit()){
                    re.concat();
                    re.getNextChar();
                }
                re.retract();
                allLex.add(re.getStrToken());
                break;
            case '(':
                operatorNormalStatement();
                break;
            case ')':
                operatorNormalStatement();
                break;
            case '{':
                operatorNormalStatement();
                break;
            case '}':
                operatorNormalStatement();
                break;
            case '[':
                operatorNormalStatement();
                break;
            case ']':
                operatorNormalStatement();
                break;
            case '.':
                operatorNormalStatement();
                break;
            case '-':
                start();
                if(re.getC()=='-'){
                    operatorNormalStatement();
                }else if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '+':
                start();
                if(re.getC()=='='){
                    operatorNormalStatement();
                }else if(re.getC()=='+'){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '*':
                start();
                if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '/':
                start();
                if(re.getC()=='='){
                    operatorNormalStatement();
                }else if(re.getC()=='/'){
                    re.getNextChar();
                    while(re.getC()!='\n'&&re.getC()!=-1){
                        re.getNextChar();
                    }
                }else if(re.getC()=='*'){
                    re.getNextChar();
                    int r=re.getRow();
                    while(re.getC()!='*'){
                        if(re.getC()==-1){
                            re.error("UnClosed Statement!",r+1);
                        }
                        re.getNextChar();
                    }
                    re.getNextChar();
                    if(re.getC()!='/')
                        re.error("UnClosed Statement!",r+1);
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '%':
                start();
                if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '&':
                start();
                if(re.getC()=='&'){
                    operatorNormalStatement();
                }else if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '!':
                start();
                if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '|':
                start();
                if(re.getC()=='|'){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
            case '<':
                start();
                if(re.getC()=='<'){
                    operatorNormalStatement();
                }else if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '>':
                start();
                if(re.getC()=='>'){
                    operatorNormalStatement();
                }else if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '=':
                start();
                if(re.getC()=='='){
                    operatorNormalStatement();
                }else{
                    operatorSpecialStatement();
                }
                break;
            case '^':
                operatorNormalStatement();
                break;
            case '?':
                operatorNormalStatement();
                break;
            case ',':
                operatorNormalStatement();
                break;
            case ':':
                operatorNormalStatement();
                break;
            case ';':
                operatorNormalStatement();
                break;
            default:
                if(re.getC()==-1){
                    break;
                }
                re.error("Invalid Symbol!!!");
        }
    }
    public void operatorNormalStatement(){
        re.concat();
        Tokens tokens=re.checkIsReserveOperator();
        AnalyLex.results.add(new LexResult(tokens,re.getStrToken().toString()));
        allLex.add(re.getStrToken());
    }
    public void start(){
        re.concat();
        re.getNextChar();
    }
    public void operatorSpecialStatement(){
        re.retract();
        Tokens tokens=re.checkIsReserveOperator();
        AnalyLex.results.add(new LexResult(tokens,re.getStrToken().toString()));
        allLex.add(re.getStrToken());
    }
    public void deal(char...c){
        for(int i=0;i<c.length;i++){
            if(re.getC()==c[i]){
                operatorNormalStatement();
                return;
            }
        }
        operatorSpecialStatement();
    }
}