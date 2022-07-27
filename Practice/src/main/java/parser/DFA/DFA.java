package parser.DFA;

public class DFA {
    private Object currentState;
    private Object[] acceptStates;
    private FABook faBook;
    public DFA(Object currentState,Object[] acceptStates,FABook faBook){
        this.currentState=currentState;
        this.acceptStates = acceptStates;
        this.faBook = faBook;
    }
    public boolean accepting(){
        for (Object acceptState : acceptStates){
            if (acceptState.equals(this.currentState)){
                return true;
            }
        }
        return false;
    }
    public void readCharacter(char character){
        Object current = currentState;
        currentState = faBook.getNextState(currentState,character);
        System.out.println("current:"+current+"character:"+character+",next:"+currentState);
    }
    public void readString(String str){
        for (int i=0;i<str.length();i++){
            readCharacter(str.charAt(i));
        }
    }

}
