package Imitate.LEX.S1;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-06  12:29
 */

public class S1TokenMgr implements S1Constants{
    private Scanner inFile;
    private PrintWriter outFile;
    private boolean debug;
    private char currentChar;
    private int currentColumn;
    private int currentLine;
    private int lineLength;
    private String inputLine;
    private Token token;
    private StringBuffer buffer;
    public S1TokenMgr(Scanner inFile, PrintWriter outFile,boolean debug){
        this.inFile=inFile;
        this.outFile=outFile;
        this.debug=debug;
        currentChar='\n';
        currentLine=0;
        buffer=new StringBuffer();
    }
//    获取下一个token,通过char构成的
    public Token getNextToken(){
        while(Character.isWhitespace(currentChar))
            getNextChar();
        token=new Token();
        token.next=null;
        token.beginLine=currentLine;
        token.beginColumn=currentColumn;
        if (currentChar==EOF){
            token.image="<EOF>";
            token.endLine=currentLine;
            token.endColumn=currentColumn;
            token.kind=EOF;
        }else if (Character.isDigit(currentChar)){
            buffer.setLength(0);
            do{
                buffer.append(currentChar);
                token.endLine=currentLine;
                token.endColumn=currentColumn;
                getNextChar();
            }while(Character.isDigit(currentChar));
            token.image=buffer.toString();
            token.kind=UNSIGNED;
        }else if(Character.isLetter(currentChar)){
            buffer.setLength(0);
            do{
                buffer.append(currentChar);
                token.endColumn=currentLine;
                token.endColumn=currentColumn;
                getNextChar();
            }while (Character.isLetterOrDigit(currentChar));
            token.image=buffer.toString();
            if (token.image.equals("println"))
                token.kind=PRINTLN;
            else
                token.kind=ID;
        }else {
            switch(currentChar){
                case '=':
                    token.kind=ASSIGN;
                    break;
                case ';':
                    token.kind=SEMICOLON;
                    break;
                case '(':
                    token.kind=LEFTPAREN;
                    break;
                case ')':
                    token.kind=RIGHTPAREN;
                    break;
                case '+':
                    token.kind=PLUS;
                    break;
                case '-':
                    token.kind=MINUS;
                    break;
                case '*':
                    token.kind=TIMES;
                    break;
                case '/':
                    token.kind=DIVIDE;
                    break;
                default:
                    token.kind=ERROR;
                    break;
            }
            token.image=Character.toString(currentChar);
            token.endLine=currentLine;
            token.endColumn=currentColumn;
            getNextChar();
        }
        if (debug)
            outFile.printf("kd=%3d bl=%3d bc=%3d el=%3d ec=%3d im=%s%n",
                token.kind,token.beginLine,token.beginColumn,
                token.endLine,token.endColumn,token.image);
        return token;
    }
//    获取下一个字符
    private void getNextChar(){
        if (currentChar==EOF)
            return;
        if (currentChar=='\n'){
            if (inFile.hasNextLine()){
                inputLine=inFile.nextLine();
                outFile.println(";"+inputLine);
                inputLine=inputLine+"\n";
                currentColumn=0;
                currentLine++;
            }else{
                currentChar=EOF;
                return;
            }
        }
        currentChar=inputLine.charAt(currentColumn++);
    }
}

//符号管理器，一些变量的名称
class S1SymTab {
    private List<String> symbol;
    public S1SymTab(){
        symbol=new ArrayList<>();
    }
    public void enter(String s){
        int index=symbol.indexOf(s);
        if (index<0)
            symbol.add(s);
    }
    public String getSymbol(int index){
        return symbol.get(index);
    }
    public int getSize(){
        return symbol.size();
    }
}