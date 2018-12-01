package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.controller.SpreadsheetControllerImpl;
import com.javacourse2018.lw03.model.Command;
import com.javacourse2018.lw03.model.Spreadsheet;

import java.util.Scanner;

public class SpreadsheetEngine extends SpreadsheetControllerImpl {

    private Spreadsheet spreadsheet;

    private PrefixFormCalculator calculator;

    public SpreadsheetEngine() {
        this.spreadsheet = new Spreadsheet();
        this.calculator = new FormulaCalculatorImpl(this.spreadsheet);
    }

    @Override
    protected Spreadsheet getSpreadsheet() {
        return this.spreadsheet;
    }

    @Override
    protected PrefixFormCalculator getCalculator() {
        return this.calculator;
    }

    public void control() {
        try {
            Scanner input = new Scanner(System.in);
            input.useDelimiter("\n");
            while (true) {
                System.out.print("> ");
                this.calculator.clearResult();
                String inputStr = input.next().trim();
                inputStr = inputStr.replaceAll("( )+", " ");
                String[] splitInput = inputStr.split(" ");
                Command command = Command.getCommandFromString(splitInput[0]);

                if (command == null) {
                    System.out.println("Incorrect command.");
                    Command.printUsage();
                    continue;
                }

                if (!Command.isCorrectCommandSize(splitInput, command)) {
                    System.out.println("Incorrect command.");
                    Command.printUsage();
                    continue;
                }

                if (command == Command.EXIT) {
                    break;
                }

                this.commandControl(command, inputStr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}