package com.devonjp;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ParametersController parametersController = new ParametersController(args);
            String mode = parametersController.getOperationMode();
            Integer key = parametersController.getKey();
            String word = parametersController.getWord();
            CaesarСipher caesarСipher = new CaesarСipher(word, key, mode);
            caesarСipher.start();
            System.out.println(caesarСipher.getResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}