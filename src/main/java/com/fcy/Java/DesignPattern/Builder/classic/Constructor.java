package com.fcy.Java.DesignPattern.Builder.classic;

public class Constructor {
    private Builder builder;
    public Constructor(Builder builder){
        this.builder=builder;
    }
    public Product construct(){
        Product product=new Product();
        builder.buildA();
        builder.buildB();
        builder.buildC();
        return product;
    }
    public static void main(String args[]) {

    }
}
