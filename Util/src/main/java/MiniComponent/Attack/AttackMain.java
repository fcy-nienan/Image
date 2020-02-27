package MiniComponent.Attack;
public class AttackMain {
    public static void main (String args[]) {
        CommandAttack commandAttack = new CommandAttack();
        Thread t=new Thread(commandAttack);
        t.start();
    }
}
