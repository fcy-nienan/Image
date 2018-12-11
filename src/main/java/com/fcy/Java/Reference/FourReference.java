package com.fcy.Java.Reference;

import scala.swing.StrongReference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-06  14:07
 *
 * 四种引用
 *
 * 强引用
 * 软引用
 * 若引用
 * 虚引用
 *
 *
 *
 */
public class FourReference {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue queue=new ReferenceQueue();
        WeakReference weakReference =new WeakReference(new Object(),queue);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());
        System.gc();
        Thread.sleep(5000);
        System.out.println(weakReference.get());
        Thread.sleep(3000);
        System.out.println(queue.poll().get());
    }
}
