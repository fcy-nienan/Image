package parser.DFA;

import parser.FARule;

import java.util.ArrayList;
import java.util.List;

public class DFATest {
    private Object startState;
    private Object[] accepts;
    private FABook faBook;
    public DFATest(Object startState,Object[] accepts,FABook faBook){
        this.startState=startState;
        this.accepts=accepts;
        this.faBook=faBook;
    }
    public DFA toDFA(){
        return new DFA(startState,accepts,faBook);
    }
    public boolean accepts(String str){
        DFA dfa=toDFA();
        dfa.readString(str);
        return dfa.accepting();
    }

    public static void main(String[] args) {
        List<FARule> rules = new ArrayList<FARule>();
        rules.add(new FARule(1, 'a', 2));
        rules.add(new FARule(1, 'b', 1));
        rules.add(new FARule(2, 'a', 2));
        rules.add(new FARule(2, 'b', 3));
        rules.add(new FARule(3, 'a', 3));
        rules.add(new FARule(3, 'b', 3));
        FABook faBook = new FABook(rules);
        DFATest test = new DFATest(1,new Object[]{3},faBook);
        System.out.println(test.accepts("baaab"));
    }
}
