package LEX.S1;

import java.io.PrintWriter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-06  12:40
 */
public class S1Parser implements S1Constants {
    private S1SymTab st;
    private S1TokenMgr tm;
    private S1CodeGen cg;
    private Token current;
    private Token previous;
    public S1Parser(S1SymTab st,S1TokenMgr tm,S1CodeGen cg){
        this.st=st;
        this.tm=tm;
        this.cg=cg;
        current=tm.getNextToken();
        previous=null;
    }
    private RuntimeException genEx(String error){
        return new RuntimeException("Encountered \""+
                current.image+"\" on line "+
                current.beginLine+" column" +
                current.beginColumn+
                System.getProperty("line.separator")+
                error);
    }
//    将current指向下一个字符token，并且通过token的next指针链接起来
    private void advance(){
        previous=current;
        if (current.next!=null){
            current=current.next;
        }else{
            current=current.next=tm.getNextToken();
        }
    }
//    返回指定的token,i=0 返回previous i=1 返回current i=2 返回next
    private Token getToken(int i){
        if (i<=0)
            return previous;
        Token t=current;
        for(int j=1;j<i;j++){
            if (t.next!=null)
                t=t.next;
            else
                t=t.next=tm.getNextToken();
        }
        return t;
    }
//    消耗当前字符(判断是否正确)并指向下一个字符
    private void consume(int expected){
        if (current.kind==expected){
            advance();
        }else{
            throw genEx("Exception "+tokenImage[expected]);
        }
    }
    public void parse(){
        program();
    }
    private void program(){
        statementList();
        cg.endCode();
        if (current.kind!=EOF)
            throw genEx("Expection <EOF>");
    }
    private void statement(){
        switch (current.kind){
            case ID:
                assignmentStatement();
                break;
            case PRINTLN:
                printlnStatement();
                break;
                default:
                    throw genEx("Expection statement");
        }
    }
    private void assignmentStatement(){
        Token t;
        t=current;
        consume(ID);
        st.enter(t.image);
        cg.emitInstruction("pc",t.image);
        consume(ASSIGN);
        expr();
        cg.emitInstruction("stav");
        consume(SEMICOLON);
    }
    private void printlnStatement(){
        consume(PRINTLN);
        consume(LEFTPAREN);
        expr();
        cg.emitInstruction("dout");
        cg.emitInstruction("pc","'\\n'");
        cg.emitInstruction("aout");
        consume(RIGHTPAREN);
        consume(SEMICOLON);
    }
    private void expr(){
        term();
        termList();
    }
    private void termList(){
        switch (current.kind){
            case PLUS:
                consume(PLUS);
                term();
                cg.emitInstruction("add");
                termList();
                break;
            case RIGHTPAREN:
            case SEMICOLON:
                ;
                break;
                default:
                    throw genEx("Expecting \"+\",\")\", or \";\"");
        }
    }
    private void term(){
        factor();
        factorList();
    }
    private void factorList(){
        switch (current.kind){
            case TIMES:
                consume(TIMES);
                factor();
                cg.emitInstruction("mult");
                factorList();
                break;
            case PLUS:
            case RIGHTPAREN:
            case SEMICOLON:
                break;
                default:
                    throw genEx("Exception op,\")\", or \";\"");
        }
    }
    private void factor(){
        Token t;
        switch (current.kind){
            case UNSIGNED:
                t=current;
                consume(UNSIGNED);
                cg.emitInstruction("pwc",t.image);
                break;
            case PLUS:
                consume(PLUS);
                t=current;
                consume(UNSIGNED);
                cg.emitInstruction("pwc",t.image);
                break;
            case MINUS:
                consume(MINUS);
                t=current;
                consume(UNSIGNED);
                cg.emitInstruction("pwc","-"+t.image);
                break;
            case ID:
                t=current;
                consume(ID);
                st.enter(t.image);
                cg.emitInstruction("p",t.image);
                break;
            case LEFTPAREN:
                consume(LEFTPAREN);
                expr();
                consume(RIGHTPAREN);
                break;
                default:
                    throw genEx("Expection factor");
        }
    }
    private void statementList(){
        switch(current.kind){
            case ID:
            case PRINTLN:
                statement();
                statementList();
                break;
            case EOF:
                break;
                default:throw genEx("Expecting statement or <EOF>");

        }
    }

}
//目标代码生成器
class S1CodeGen{
    private PrintWriter outFile;
    private S1SymTab st;
    public S1CodeGen(PrintWriter outFile,S1SymTab st){
        this.outFile=outFile;
        this.st=st;
    }
    public void emitInstruction(String op){
        outFile.printf("     %-4s%n",op);
    }
    public void emitInstruction(String op,String opnd){
        outFile.printf("     %-4s      %s%n",op,opnd);
    }
    private void emitdw(String label,String value){
        outFile.printf("%-9s dw      %s%n",label+":",value);
    }
    public void endCode(){
        outFile.println();
        emitInstruction("halt");
        int size=st.getSize();
        for(int i=0;i<size;i++){
            emitdw(st.getSymbol(i),"0");
        }
    }
}
