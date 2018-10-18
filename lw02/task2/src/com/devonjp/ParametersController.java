package com.devonjp;

public class ParametersController{

    private static final String INCORRECT_DATA = "Ошибка! Неверный ввод данных.";
    private static final String HELP = "java -jar task2.jar <mode> <key> <word>";

    private String[] args;

    public ParametersController(String[] args){
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() {
        try {
            if (this.args.length != 3) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException argumentEx) {
            this.printErrorInfo();
        }
    }

    public String getOperationMode() throws IllegalArgumentException {
        String mode = this.args[0];
        if (mode.equals("-e") || mode.equals("-d")) {
            return mode;
        }
        throw new IllegalArgumentException(INCORRECT_DATA);
    }

    public Integer getKey() throws NumberFormatException  {
        return Integer.parseInt(this.args[1]);
    }

    public String getWord() {
        return this.args[2];
    }

    private void printErrorInfo() {
        System.out.println(INCORRECT_DATA);
        System.out.println(HELP);
    }
}
