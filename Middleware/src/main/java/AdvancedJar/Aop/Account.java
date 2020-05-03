package AdvancedJar.Aop;

public class Account {
    int balance = 20;

    public boolean pay(int amount) {
        if (balance < amount) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}
