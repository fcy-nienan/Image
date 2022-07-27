package parser.NFA;

import java.util.Set;

public class NFA {
    private Set<Object> currentStates;
    private Set<Object> acceptStates;
    private NFARuleBook ruleBook;

    public NFA(Set<Object> currentStates, Set<Object> acceptStates, NFARuleBook ruleBook) {
        this.currentStates = currentStates;
        this.acceptStates = acceptStates;
        this.ruleBook = ruleBook;
    }

    public boolean accepting() {
        for (Object currentState : getCurrentStates()) {
            if (acceptStates.contains(currentState)) {
                return true;
            }
        }

        return false;
    }

    public void readCharacter(Character character) {
        currentStates = ruleBook.nextStates(getCurrentStates(), character);
    }

    public void readString(String str) {
        for (int i = 0; i < str.length(); i++) {
            readCharacter(str.charAt(i));
        }
    }

    public Set<Object> getCurrentStates() {
        return ruleBook.followFreeMoves(currentStates);
    }
}