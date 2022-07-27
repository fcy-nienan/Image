package parser.NFA;
import parser.FARule;

import java.util.*;
import java.util.stream.Collectors;

public class NFARuleBook {
    private List<FARule> rules;

    public NFARuleBook(List<FARule> rules) {
        this.rules = rules;
    }

    public Set<Object> nextStates(Collection<Object> states, Character character) {
        return states.stream()
                .map(state -> followRulesFor(state, character))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Set<Object> followRulesFor(Object state, Character character) {
        return rulesFor(state, character).stream().map(FARule::follow).collect(Collectors.toSet());
    }

    public Set<FARule> rulesFor(Object state, Character character) {
        return rules.stream()
                .filter(faRule -> faRule.appliesTo(state, character))
                .collect(Collectors.toSet());
    }

    public Set<Object> followFreeMoves(Collection<Object> states) {
        Set<Object> moreStates = nextStates(states, null);
        if (states.containsAll(moreStates)) {
            return new HashSet<>(states);
        } else {
            states.addAll(moreStates);
            return followFreeMoves(states);
        }
    }

    public List<Character> alphabet() {
        return rules.stream()
                .map(FARule::getCharacter)
                .distinct()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<FARule> getRules() {
        return rules;
    }
}