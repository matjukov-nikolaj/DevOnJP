package com.devonjp;

public class Main {

  public static void main(String[] args) {
    ParametersController parametersController = new ParametersController(args);
    String mode = parametersController.getOperationMode();
    Integer key = parametersController.getKey();
    String word = parametersController.getWord();
    CaesarСipher caesarСipher = new CaesarСipher();
    caesarСipher.start(mode, word, key);
    System.out.println(caesarСipher.getResult());
  }

}