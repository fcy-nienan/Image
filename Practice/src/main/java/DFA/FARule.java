package DFA;

public class FARule {
    private Object currentState;
    private Character character;
    private Object nextState;
    public FARule(Object currentState,Character character,Object nextState){
        this.currentState=currentState;
        this.character=character;
        this.nextState=nextState;
    }
    public boolean appliesTo(Object currentState,Character character){
        return this.currentState.equals(currentState) && this.character.equals(character);
    }
    public Object follow(){
        return this.nextState;
    }
    public Character getCharacter(){
        return this.character;
    }

}
