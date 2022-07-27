package parser.NFA;

import parser.FARule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NFATest {
    public static void main(String[] args) {
        List<FARule> rules = new ArrayList<>();
        rules.add(new FARule(1, null, 2));
        rules.add(new FARule(1, null, 4));
        rules.add(new FARule(2, 'a', 3));
        rules.add(new FARule(3, 'a', 2));
        rules.add(new FARule(4, 'a', 5));
        rules.add(new FARule(5, 'a', 6));
        rules.add(new FARule(6, 'a', 4));
        NFARuleBook ruleBook = new NFARuleBook(rules);
        Set<Object> x=new HashSet();
        x.add(2);
        x.add(4);
        NFADesign nfaDesign = new NFADesign(1, x, ruleBook);
        System.out.println(nfaDesign.accepts("aaaaa"));
        System.out.println(nfaDesign.accepts("aaaaaa"));
    }
}
