package com.devonjp;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ParametersController parametersController = new ParametersController(args);
        WordRating wordRating = new WordRating(parametersController.getFilePath(), parametersController.getWordsCount());
        wordRating.calculateTheNumberOfWords();
        wordRating.printWords();
    }


}