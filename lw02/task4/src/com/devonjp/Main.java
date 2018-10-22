package com.devonjp;

public class Main {

    public static void main(String[] args) {
        PrintFormat();
        PrintLong();
        PrintInteger();
        PrintShort();
        PrintByte();
        PrintDouble();
        PrintFloat();
        PrintChar();
    }

    private static void PrintFormat() {
        System.out.printf("%s", "Type");
        System.out.printf("%25s", "Min");
        System.out.printf("%25s", "Max");
        System.out.printf("%5s%n", "Size");
    }

    private static void PrintInteger() {
        System.out.printf("%s", "Integer");
        System.out.printf("%22d", Integer.MIN_VALUE);
        System.out.printf("%25d", Integer.MAX_VALUE);
        System.out.printf("%5d%n", Integer.SIZE / 8);
    }

    private static void PrintLong() {
        System.out.printf("%s", "Long");
        System.out.printf("%25d", Long.MIN_VALUE);
        System.out.printf("%25d", Long.MAX_VALUE);
        System.out.printf("%5d%n", Long.SIZE / 8);
    }

    private static void PrintShort() {
        System.out.printf("%s", "Short");
        System.out.printf("%24d", Short.MIN_VALUE);
        System.out.printf("%25d", Short.MAX_VALUE);
        System.out.printf("%5d%n", Short.SIZE / 8);
    }

    private static void PrintByte() {
        System.out.printf("%s", "Byte");
        System.out.printf("%25d", Byte.MIN_VALUE);
        System.out.printf("%25d", Byte.MAX_VALUE);
        System.out.printf("%5d%n", Byte.SIZE / 8);
    }

    private static void PrintDouble() {
        System.out.printf("%s", "Double");
        System.out.printf("%23s", Double.toString(Double.MIN_VALUE));
        System.out.printf("%25s", Double.toString(Double.MAX_VALUE));
        System.out.printf("%5d%n", Double.SIZE / 8);
    }

    private static void PrintFloat() {
        System.out.printf("%s", "Float");
        System.out.printf("%24s", Float.toString(Float.MIN_VALUE));
        System.out.printf("%25s", Float.toString(Float.MAX_VALUE));
        System.out.printf("%5d%n", Float.SIZE / 8);
    }

    private static void PrintChar() {
        System.out.printf("%s", "Character");
        System.out.printf("%20s", (int) Character.MIN_VALUE);
        System.out.printf("%25s", (int) Character.MAX_VALUE);
        System.out.printf("%5d%n", Character.SIZE / 8);
    }


}