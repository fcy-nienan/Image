package com.fcy.Java.DesignPattern.Builder.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Computer {
    private int gpu;
    private String cpu;
    private int hardware;
    private int memory;
    public static void main(String args[]) {
        Computer computer=new ComputerBuilder().cpu("i5")
                .gpu(3)
                .memory(5)
                .hardware(500)
                .build();
        System.out.println(computer);
    }
}
