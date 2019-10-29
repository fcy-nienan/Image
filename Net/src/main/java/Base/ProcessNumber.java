package com.fcy.Net.Base;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-17  23:11
 */
public class ProcessNumber {
    private long part;
    private long sum;

    public ProcessNumber(long part, long sum) {
        this.part = part;
        this.sum = sum;
    }

    public long getPart() {
        return part;
    }

    public void setPart(long part) {
        this.part = part;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
