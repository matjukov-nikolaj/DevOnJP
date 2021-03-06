package com.devonjp;

public class ParametersController {

    private static final String INCORRECT_DATA = "Ошибка! Неверный ввод данных.";
    private static final String HELP = "java -jar task3.jar <password length> <chars array>";

    private String[] args;

    public ParametersController(String[] args) {
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() {
        if (this.args.length != 2) {
            throw new IllegalArgumentException(INCORRECT_DATA + "\n" + HELP);
        }
    }

    public String getString() {
        return this.args[1];
    }

    public Integer getPasswordLength() throws NumberFormatException {
        return Integer.parseInt(this.args[0]);
    }

}
