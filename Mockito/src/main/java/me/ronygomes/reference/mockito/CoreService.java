package me.ronygomes.reference.mockito;

import java.io.IOException;
import java.math.BigInteger;

public class CoreService {

    private static final int INT_VALUE_TEN = BigInteger.TEN.intValue();

    public String convertToString(int value) {
        return String.valueOf(value);
    }

    public int addTen(int value) {
        return value + INT_VALUE_TEN;
    }

    public void doNothing() {
        System.out.println("Doing Nothing!");
    }

    public int throwException(boolean shouldThrow) throws IOException {
        if (shouldThrow) {
            throw new IOException();
        }

        return 99;
    }
}
