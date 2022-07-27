package parser.NFA;


import java.util.*;

public class NFADesign {
    private Object startState;
    private Set<Object> acceptStates;
    private NFARuleBook ruleBook;

    public NFADesign(Object startState, Set<Object> acceptStates, NFARuleBook ruleBook) {
        this.startState = startState;
        this.acceptStates = acceptStates;
        this.ruleBook = ruleBook;
    }

    public NFA toNfa() {
        Set<Object> x = new HashSet<>();
        x.add(startState);
        return new NFA(x, acceptStates, ruleBook);
    }

    public NFA toNfa(Set<Object> currentStates) {
        return new NFA(currentStates, acceptStates, ruleBook);
    }

    public boolean accepts(String str) {
        NFA nfa = toNfa();
        nfa.readString(str);
        return nfa.accepting();
    }

    public Object getStartState() {
        return startState;
    }

    public Set<Object> getAcceptStates() {
        return acceptStates;
    }

    public NFARuleBook getRuleBook() {
        return ruleBook;
    }
}