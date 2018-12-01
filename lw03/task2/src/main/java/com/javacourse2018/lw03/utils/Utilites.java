package com.javacourse2018.lw03.utils;

public class Utilites {

    public static boolean isNumber(String strValue) {
        try {
            strValue = strValue.replace(",", ".");
            Double value = Double.parseDouble(strValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
