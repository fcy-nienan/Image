package com.fcy.Java.DesignPattern1.Visit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  16:21
 */
public interface Visitor {
    void visitCPU(CPU cpu);
    void visitDisplay(Display display);
    void visitHardpan(Hardpan hardpan);
    void visitMemory(Memory memory);
}
