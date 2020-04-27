package Grammer.Base;

import java.util.logging.Logger;

public enum EnumDemoType {
    ON{
        public void display(){
            System.out.println("ON");
        }
    },
    OFF{
        public void display(){
            System.out.println("OFF");
        }
    };
    public abstract void display();

    public static void main(String[] args) {
        EnumDemoType on = ON;
        on.display();
    }

}
