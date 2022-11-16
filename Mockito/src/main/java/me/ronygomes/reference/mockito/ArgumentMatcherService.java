package me.ronygomes.reference.mockito;

public class ArgumentMatcherService {

    public boolean negateBoolean(boolean bool) {
        return !bool;
    }

    public Object returnRuntimeIgnoringGivenParam(Object object) {
        return Runtime.getRuntime();
    }

    public int squareInt(int value) {
        return value * value;
    }

    public long squareLong(long value) {
        return value * value;
    }

    public float squareFloat(float value) {
        return value * value;
    }

    public double squareDouble(double value) {
        return value * value;
    }

    public short squareShort(short value) {
        return (short) (value * value);
    }

    public byte squareByte(byte value) {
        return (byte) (value * value);
    }

    public char upperCaseChar(char lowerCaseChar) {
        return Character.toUpperCase(lowerCaseChar);
    }

    public String upperCaseString(String lowerCaseString) {
        return lowerCaseString.toUpperCase();
    }

    public int dualParamMethod(String key, int data) {
        return key.length() * data;
    }
}
