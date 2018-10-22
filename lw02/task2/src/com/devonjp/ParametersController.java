package com.devonjp;

public class ParametersController{

    private static final String INCORRECT_DATA = "Ошибка! Неверный ввод данных.";
    private static final String HELP = "java -jar task2.jar <mode -d or -e> <key> <string which contains letters and spaces>";

    private String[] args;

    public ParametersController(String[] args){
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() {
        if (this.args.length != 3) {
            throw new IllegalArgumentException(INCORRECT_DATA + "\n" + HELP);
        }
    }

    public String getOperationMode() throws IllegalArgumentException {
        String mode = this.args[0];
        if (mode.equals("-e") || mode.equals("-d")) {
            return mode;
        }
        throw new IllegalArgumentException(INCORRECT_DATA + "\n" + HELP);
    }

    public Integer getKey() throws NumberFormatException  {
        return Integer.parseInt(this.args[1]);
    }

    public String getLine() throws IllegalArgumentException {
        String line = this.args[2];
        if (line.matches("^[ a-zA-Z]*$") && !line.isEmpty()) {
            return line;
        }
        throw new IllegalArgumentException(INCORRECT_DATA + "\n" + HELP);
    }

}
