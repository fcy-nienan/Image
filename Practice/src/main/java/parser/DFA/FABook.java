package parser.DFA;

import parser.FARule;

import java.util.List;

public class FABook {
    private List<FARule> book;
    public FABook(List<FARule> list){
        this.book = list;
    }
//    public Object nextState(Object state,Character character){
//        return ruleFor(state,character).map(FARule::follow).orElse(null);
//    }
//    public Optional<FARule> ruleFor(Object state,Character character){
//        return book.stream().filter(faRule -> faRule.appliesTo(state,character))
//                .findFirst();
//    }
    public Object getNextState(Object currentState,Character character){
        for (FARule faRule : book) {
            if (faRule.appliesTo(currentState,character)){
                return faRule.follow();
            }
        }
        return null;
    }
}
