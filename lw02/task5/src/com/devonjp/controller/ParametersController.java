package com.devonjp.controller;

public class ParametersController {

    private static final String INCORRECT_DATA = "Ошибка! Неверный ввод данных.";
    private static final String HELP = "java -jar task5.jar <filePath> <number of words>";

    private String[] args;

    public ParametersController(String[] args) {
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() throws IllegalArgumentException {
        if (this.args.length != 2) {
            throw new IllegalArgumentException(INCORRECT_DATA);
        }
    }

    public String getFilePath() {
        return this.args[0];
    }

    public Integer getWordsCount() throws NumberFormatException {
        String wordsCountStr = this.args[1];
        return Integer.parseInt(wordsCountStr);
    }
}
