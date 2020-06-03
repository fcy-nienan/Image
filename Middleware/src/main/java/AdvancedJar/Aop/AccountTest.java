package AdvancedJar.Aop;

import org.junit.Before;
import org.junit.Test;


public class AccountTest {

    private Account account;
    public static void main(String[] args) {

    }
    @Test
    public  void testTemplate() {
        account.pay(555);
    }
    @Before
    public void tet(){
        System.out.println("before");
        account=new Account();
    }
    
}