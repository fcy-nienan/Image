package Imitate.LEX.NewLex;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-06  12:00
 */
public class Fig1010Parser implements Fig1010Constants {
    private Fig1010TokenMgr tm;
    private Token current;
    private Token previous;
    public Fig1010Parser(Fig1010TokenMgr mgr){
        this.tm=mgr;
        current=tm.getNextToken();
        previous=null;
    }
    public void parse(){
        S();
        if (current.kind!=EOF)
            throw genEx("Exception <EOF>");
    }
    private RuntimeException genEx(String error){
        return new RuntimeException("Encountered \""+
        current.image+"\" on line "+
        current.beginLine+" column" +
        current.beginColumn+
        System.getProperty("line.separator")+
        error);
    }
    private void advance(){
        previous=current;
        if (current.next!=null){
            current=current.next;
        }else{
            current=current.next=tm.getNextToken();
        }
    }
    private Token getToken(int i){
        if (i<=0)
            return previous;
        Token t=current;
        for (int j=1;j<i;j++){
            if (t.next!=null){
                t=t.next;
            }else{
                t=t.next=tm.getNextToken();
            }
        }
        return t;
    }
    private void consume(int expected){
        if (current.kind==expected){
            advance();
        }else{
            throw genEx("Exception "+tokenImage[expected]);
        }
    }
    private void S(){
        int p;
        p=expr();
        System.out.println(p);
    }
    private int expr(){
        int p,q;
        Token t;
        switch(current.kind){
            case PLUS:
                consume(PLUS);
                p=expr();
                q=expr();
                return p+q;
            case MINUS:
                consume(MINUS);
                p=expr();
                q=expr();
                return p-q;
            case TIMES:
                consume(TIMES);
                p=expr();
                q=expr();
                return p*q;
            case DIVIDE:
                consume(DIVIDE);
                p=expr();
                q=expr();
                return p/q;
            case UNSIGNED:
                t=current;
                consume(UNSIGNED);
                p=Integer.parseInt(t.image);
                return p;
                default:
                    throw genEx("Exception operator or "+ tokenImage[UNSIGNED]);
        }
    }

}
