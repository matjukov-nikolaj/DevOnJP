package com.javacourse2018.lw03.model;

import java.util.*;

public class Spreadsheet {

    private TreeMap<Character, TreeMap<Integer, Cell>> table = new TreeMap<>();

    public void setCell(Cell cell) {
        if (this.table.containsKey(cell.getPosition().getX())) {
            TreeMap<Integer, Cell> row = this.table.get(cell.getPosition().getX());
            if (row.containsKey(cell.getPosition().getY())) {
                this.setCellMetrics(row, cell);
            } else {
                row.put(cell.getPosition().getY(), cell);
            }
        } else {
            TreeMap<Integer, Cell> row = new TreeMap<>();
            row.put(cell.getPosition().getY(), cell);
            this.table.put(cell.getPosition().getX(), row);
        }
        System.out.println("OK");
    }

    public Cell getCell(Position position) throws Exception {
        if (this.table.containsKey(position.getX())) {
            Map<Integer, Cell> row = this.table.get(position.getX());
            if (row.containsKey(position.getY())) {
                return row.get(position.getY());
            }
            throw new Exception("Could not found cell with index: " + position.getY());
        }
        throw new Exception("Could not found cell with index: " + position.getX());
    }

    public void display() {
        System.out.print("  ");
        for (Map.Entry entry : this.table.entrySet()) {
            System.out.printf("%20s", entry.getKey());
        }
        System.out.println();
        Set<Integer> rowIndexes = new HashSet<>();
        for (Map.Entry<Character, TreeMap<Integer, Cell>> entry : this.table.entrySet()) {
            TreeMap<Integer, Cell> row = entry.getValue();
            this.printRow(rowIndexes, row);
        }
    }

    private void printRow(Set<Integer> rowIndexes, TreeMap<Integer, Cell> row ) {
        for (Map.Entry<Integer, Cell> element : row.entrySet()) {
            Integer key = element.getKey();
            if (!rowIndexes.contains(key)) {
                System.out.print(element.getKey() + " ");
                printCell(key);
                rowIndexes.add(key);
                System.out.println();
            }
        }
    }

    private void printCell(Integer rowIndex) {
        Integer counter = 1;
        for (Map.Entry<Character, TreeMap<Integer, Cell>> entry : this.table.entrySet()) {
            TreeMap<Integer, Cell> row = entry.getValue();
            for (Map.Entry<Integer, Cell> element : row.entrySet()) {
                this.printCellValue(element, rowIndex, counter);
                if (element.getKey() == rowIndex) {
                    counter = 0;
                }
            }
            ++counter;
        }
    }

    private void printCellValue(Map.Entry<Integer, Cell> element, Integer rowIndex, Integer counter) {
        if (element.getKey() == rowIndex) {
            Cell cell = element.getValue();
            String format = "%" + (20 * counter) + "s";
            if (cell.getValue().length() <= 20 ) {
                System.out.printf(format, cell.getValue());
            } else {
                String cutDownValue = cell.getValue().substring(0, 15) + "<...>";
                System.out.printf(format, cutDownValue);
            }
        }
    }

    private void setCellMetrics(TreeMap<Integer, Cell> row, Cell cell) {
        Cell currentCell = row.get(cell.getPosition().getY());
        currentCell.setValue(cell.getValue());
        if (currentCell.getFormula().isEmpty() && !cell.getFormula().isEmpty()) {
            currentCell.setFormula(cell.getFormula());
        }
    }

}
