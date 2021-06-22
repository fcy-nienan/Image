package DFA;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FABook {
    private List<FARule> book;
    public FABook(List<FARule> list){
        this.book = list;
    }
    public Object nextState(Object state,Character character){
        return ruleFor(state,character).map(FARule::follow).orElse(null);
    }
    public Optional<FARule> ruleFor(Object state,Character character){
        return book.stream().filter(faRule -> faRule.appliesTo(state,character))
                .findFirst();
    }
}
