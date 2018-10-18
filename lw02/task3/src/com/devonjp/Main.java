package com.devonjp;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            ParametersController parametersController = new ParametersController(args);
            String str = parametersController.getString();
            Integer passwordLength = parametersController.getPasswordLength();
            System.out.println(getPassword(passwordLength, str));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getPassword(int passwordLength, String symbolsForPassword) {
        String pass = "";
        Random myRandom = new Random(System.currentTimeMillis());
        for(int i = 0; i < passwordLength; i++) {
            pass += symbolsForPassword.charAt(myRandom.nextInt(symbolsForPassword.length()));
        }
        return pass;
    }

}