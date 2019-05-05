package com.fcy.Java.Grammer.Inherit;
/**
*@descripiton
 * 覆盖:子类覆盖了父类的变量和方法,那么子类不能访问父类被覆盖的变量和方法,如果将子类转向为父类,子类还是不能访问父类的变量和方法
 * 隐藏:子类隐藏了父类的变量和方法,那么子类不能访问父类被覆盖的变量和方法,如果将子类转向为父类,子类可以访问父类的变量和方法
 * JAVA中方法和变量在继承时的覆盖和隐藏规则
 * 1	父类的实例变量和静态变量能被子类的同名变量隐藏
 * 2	父类的静态方法被子类的同名静态方法隐藏
 * 3	父类的实例方法被子类的同名实例方法覆盖
 *
 * 1.不能用子类的静态方法隐藏 父类中同样标示（也就是返回值 名字 参数都一样）的实例方法
 * 2.不能用子类的实例方法覆盖 父类中同样标示的静态方法
 * 3.这点儿请注意，就是变量只会被隐藏不会被覆盖，无论他是实例变量还是静态变量(staticfinal也可以),
 * 而且,子类的静态变量可以隐藏 父类的实例变量，子类的实例变量可以隐藏 父类的静态变量
 *
 * 实例方法才是覆盖
 * 其他都是隐藏
*@author fcy
*@date: 2019-04-23 0:30
*@params
*@return
*/
public class VariableMulti {
    public static void main(String[] args) {
        VariableMulti variableMulti =new VariableMulti();
        Fu f= variableMulti.new Zi();
        System.out.println(f.num);//这里定义的是父类，而成员变量没有多态，所以即使你new的子类，依然指向父类的成员变量。
        f.fun1();//不解释了，就是多态。
        f.show();
        System.out.println(variableMulti.equals(new VariableMulti()));
    }
    class Fu {
        public String num = "父类成员变量";
        public void show() {
            System.out.println(this.num);//因为成员变量没有多态，所以this指向当前类对象的成员变量。
            this.fun1();//因为方法有多态，所以this指向new对象的方法。
        }
        public void fun1() {
            System.out.println("父类调用"+this.num);//因为成员变量没有多态，所以this指向当前类对象的成员变量。
        }
    }
    class Zi extends Fu {
        public String num = "子类成员变量";
        public void fun1() {
            System.out.println("子类调用"+this.num);//因为成员变量没有多态，所以this指向当前类对象的成员变量。
        }
    }
}

