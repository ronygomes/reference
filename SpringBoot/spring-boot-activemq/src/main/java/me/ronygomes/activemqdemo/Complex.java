package me.ronygomes.activemqdemo;

import java.io.Serializable;

public class Complex implements Serializable {
    
    private int a;
    private int b;

    public Complex() {}

    public Complex(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String toString() {
        return String.format("(%d + %di)", a, b);
    }
}
