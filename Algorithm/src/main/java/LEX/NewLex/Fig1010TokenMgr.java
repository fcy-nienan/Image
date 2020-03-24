package LEX.NewLex;


import java.util.Scanner;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-06  11:47
 */
public class Fig1010TokenMgr implements Fig1010Constants {
    private Scanner inFile;
    private char currentChar;
    private int currentColumn;
    private int currentLine;
    private int lineLength;
    private String inputLine;
    private Token token;
    private StringBuffer buffer;
    public Fig1010TokenMgr(Scanner file){
        this.inFile=file;
        currentChar='\n';
        currentColumn=0;
        currentLine=0;
        buffer=new StringBuffer();
    }
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
        }else{
            switch(currentChar){
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
        System.out.printf("kd=%3d bl=%3d bc=%3d el=%3d ec=%3d im=%s%n",
                token.kind,token.beginLine,token.beginColumn,
                token.endLine,token.endColumn,token.image);
        return token;
    }
    private void getNextChar(){
        if (currentChar==EOF)
            return;
        if (currentChar=='\n'){
            if (inFile.hasNextLine()){
                inputLine=inFile.nextLine();
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
