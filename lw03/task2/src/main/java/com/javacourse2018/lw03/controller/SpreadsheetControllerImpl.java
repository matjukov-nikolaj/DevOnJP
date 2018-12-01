package com.javacourse2018.lw03.controller;

import com.javacourse2018.lw03.model.Cell;
import com.javacourse2018.lw03.model.Command;
import com.javacourse2018.lw03.model.Position;
import com.javacourse2018.lw03.model.Spreadsheet;
import com.javacourse2018.lw03.service.PrefixFormCalculator;

public abstract class SpreadsheetControllerImpl implements SpreadsheetController {

    public void commandControl(Command command, String inputStr) {
        String[] splitInput = inputStr.split(" ");
        switch (command) {
            case DISPLAY:
                getSpreadsheet().display();
                break;
            case SET:
                this.cellValueHandler(splitInput);
                break;
            case SET_FORMULA:
                this.cellFormulaHandler(splitInput, inputStr);
                break;
        }
    }


    protected abstract Spreadsheet getSpreadsheet();

    protected abstract PrefixFormCalculator getCalculator();

    private void cellFormulaHandler(String[] splitInput, String inputStr) {
        getSpreadsheet().clearSellsIncludedInTheFormula();
        String coordinate = splitInput[1];
        if (!Command.isValidCoordinate(coordinate)) {
            System.out.println("Invalid coordinate: " + coordinate);
            Command.printUsage();
            return;
        }
        String number = coordinate.substring(1, coordinate.length());
        Position position = new Position(coordinate.toUpperCase().charAt(0), Integer.parseInt(number));
        if (getSpreadsheet().getCellsWithFormula().containsKey(position)) {
            System.out.println("Error. Transitive closure.");
            return;
        }

        try {
            String formula = inputStr.substring(11 + coordinate.length(), inputStr.length());
            if (!formula.isEmpty()) {
                getCalculator().calculate(formula);
            }
            Cell cell = new Cell(position, getCalculator().getCalculationResult(), formula);
            getSpreadsheet().setCell(cell);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cellValueHandler(String[] splitInput) {
        String coordinate = splitInput[1];
        if (!Command.isValidCoordinate(coordinate)) {
            System.out.println("Invalid coordinate: " + coordinate);
            Command.printUsage();
            return;
        }
        String number = coordinate.substring(1, coordinate.length());
        Position position = new Position(coordinate.toUpperCase().charAt(0), Integer.parseInt(number));
        Cell cell = new Cell(position);
        if (splitInput.length == 3) {
            cell.setValue(splitInput[2]);
        }

        try {
            getSpreadsheet().setCell(cell);
            if (getSpreadsheet().getCellsWithFormula().containsKey(position)) {
                Position posWithFormula = getSpreadsheet().getCellsWithFormula().get(position);
                Cell cellWithFormula = getSpreadsheet().getCell(posWithFormula);
                getCalculator().calculate(cellWithFormula.getFormula());
                cellWithFormula.setValue(getCalculator().getCalculationResult());
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
