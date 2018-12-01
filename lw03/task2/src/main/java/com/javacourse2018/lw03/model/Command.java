package com.javacourse2018.lw03.model;

import com.javacourse2018.lw03.utils.Utilites;

public enum Command {

    EXIT("exit"),
    SET("set"),
    SET_FORMULA("setformula"),
    DISPLAY("display");

    private final String text;

    Command(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static void printUsage() {
        System.out.println("---------------------------------");
        System.out.println("           USED COMMANDS         ");
        System.out.println("---------------------------------");
        System.out.println(EXIT.toString());
        System.out.println(SET.toString() + " <coordinate> <value>");
        System.out.println(SET_FORMULA.toString() + " <coordinate> <formula>");
        System.out.println("Formula example: B1 *(+ A1 5) A1");
        System.out.println(DISPLAY.toString());
    }

    public static boolean isCorrectCommandSize(String[] splitInput, Command command) {
        if (command == null) {
            return false;
        }
        if (command.equals(SET) && splitInput.length > 1 && splitInput.length < 4) {
            return true;
        }
        if (command.equals(SET_FORMULA) && splitInput.length >= 2) {
            return true;
        }
        if ((command.equals(EXIT) || command.equals(DISPLAY)) && splitInput.length == 1) {
            return true;
        }
        return false;
    }

    public static Command getCommandFromString(String commandStr){
        commandStr = commandStr.toLowerCase();
        if (commandStr.equals(EXIT.toString())) {
            return EXIT;
        }
        if (commandStr.equals(SET.toString())) {
            return SET;
        }
        if (commandStr.equals(SET_FORMULA.toString())) {
            return SET_FORMULA;
        }
        if (commandStr.equals(DISPLAY.toString())) {
            return DISPLAY;
        }
        return null;
    }

    public static boolean isValidCoordinate(String coordinate) {
        return !coordinate.isEmpty() && coordinate.length() >= 2 && Character.isLetter(coordinate.charAt(0))
                && Utilites.isNumber(coordinate.substring(1, coordinate.length()));
    }

}
