package com.devonjp;

import com.devonjp.controller.ParametersController;
import com.devonjp.service.WordRating;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ParametersController parametersController = new ParametersController(args);
        WordRating wordRating = new WordRating(parametersController.getFilePath(), parametersController.getWordsCount());
        wordRating.calculateTheNumberOfWords();
        wordRating.printWords();
    }


}