package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.model.Cell;
import com.javacourse2018.lw03.model.Command;
import com.javacourse2018.lw03.model.Position;
import com.javacourse2018.lw03.model.Spreadsheet;

import java.util.Scanner;
import java.util.Stack;

public class SpreadsheetEngine {

    private Spreadsheet spreadsheet;

    public SpreadsheetEngine(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public void control() {
        try {
            Scanner input = new Scanner(System.in);
            input.useDelimiter("\n");
            while (true) {
                System.out.print("> ");
                String inputStr = input.next().trim();
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
                if (command == Command.SET) {
                    String coordinate = splitInput[1];
                    if (!Command.isValidCoordinate(coordinate)) {
                        System.out.println("Invalid coordinate: " + coordinate);
                        Command.printUsage();
                        continue;
                    }
                    String number = coordinate.substring(1, coordinate.length());
                    Position position = new Position(coordinate.toUpperCase().charAt(0), Integer.parseInt(number));
                    Cell cell = new Cell(position, splitInput[2]);
                    this.spreadsheet.setCell(cell);
                }
                if (command == Command.SET_FORMULA) {
                    String coordinate = splitInput[1];
                    if (!Command.isValidCoordinate(coordinate)) {
                        System.out.println("Invalid coordinate: " + coordinate);
                        Command.printUsage();
                        continue;
                    }
                    String number = coordinate.substring(1, coordinate.length());
                    Position position = new Position(coordinate.toUpperCase().charAt(0), Integer.parseInt(number));
                    String formula = inputStr.substring(inputStr.lastIndexOf(coordinate), inputStr.length());
                    Cell cell = new Cell(position, "", formula);
                    this.spreadsheet.setCell(cell);
                }
                if (command == Command.DISPLAY) {
                    this.spreadsheet.display();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}