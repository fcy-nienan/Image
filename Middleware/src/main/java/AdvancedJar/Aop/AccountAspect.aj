package AdvancedJar.Aop;

import AdvancedJar.Aop.Account;

public aspect AccountAspect {
    final int MIN_BALANCE = 10;

    pointcut callpay(int amount, Account acc) :
            call(boolean Account.pay(int)) && args(amount) && target(acc);

    before(int amount, Account acc) : callpay(amount, acc) {
        System.out.println("before");
    }

    boolean around(int amount, Account acc) :
            callpay(amount, acc) {
        System.out.println("around");
        if (acc.balance < amount) {
            return false;
        }
        return proceed(amount, acc);
    }

    after(int amount, Account balance) : callpay(amount, balance) {
        System.out.println("after");
    }
}