package com.javacourse2018.lw03.model;

import java.util.*;

public class Spreadsheet implements IncludedCells, Table {

    private TreeMap<Character, TreeMap<Integer, Cell>> table = new TreeMap<>();

    private final Map<Position, Position> cellsWithFormula = new TreeMap<>();

    private final List<Position> cellsIncludedInTheFormula = new ArrayList<>();

    @Override
    public List<Position> getCellsIncludedInTheFormula() {
        return this.cellsIncludedInTheFormula;
    }

    @Override
    public void clearSellsIncludedInTheFormula() {
        this.cellsIncludedInTheFormula.clear();
    }

    @Override
    public void addPositionToCellsIncludedInTheFormula(Position position) {
        this.cellsIncludedInTheFormula.add(position);
    }

    @Override
    public  Map<Position, Position> getCellsWithFormula() {
        return this.cellsWithFormula;
    }

    @Override
    public void setCellsIncludedInTheFormula(List<Position> list) {
        List<Position> copy = new ArrayList<>(list);
        this.cellsIncludedInTheFormula.addAll(copy);
    }

    @Override
    public void setCell(Cell cell) {
        if (this.table.containsKey(cell.getPosition().getX())) {
            TreeMap<Integer, Cell> row = this.table.get(cell.getPosition().getX());
            if (row.containsKey(cell.getPosition().getY())) {
                this.setCellMetrics(row, cell);
            } else {
                Position pos = cell.getPosition();
                if (!this.cellsWithFormula.containsValue(pos)) {
                    this.addAllCell(pos);
                }
                if (this.cellsWithFormula.containsValue(pos) && cell.getFormula().isEmpty()) {
                    this.removeCell(pos);
                }
                row.put(cell.getPosition().getY(), cell);
            }
        } else {
            TreeMap<Integer, Cell> row = new TreeMap<>();
            row.put(cell.getPosition().getY(), cell);
            this.table.put(cell.getPosition().getX(), row);
        }
        System.out.println("OK");
    }

    @Override
    public Cell getCell(Position position) throws IndexOutOfBoundsException {
        if (this.table.containsKey(position.getX())) {
            Map<Integer, Cell> row = this.table.get(position.getX());
            if (row.containsKey(position.getY())) {
                return row.get(position.getY());
            }
            throw new IndexOutOfBoundsException("Could not found cell with index: " + position.getY());
        }
        throw new IndexOutOfBoundsException("Could not found cell with index: " + position.getX());
    }

    @Override
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
                if (element.getKey().equals(rowIndex)) {
                    counter = 0;
                }
            }
            ++counter;
        }
    }

    private void printCellValue(Map.Entry<Integer, Cell> element, Integer rowIndex, Integer counter) {
        if (element.getKey().equals(rowIndex)) {
            Cell cell = element.getValue();
            String format = "%" + (20 * counter) + "s";
            if (cell.getValue().length() <= 19 ) {
                System.out.printf(format, " " + cell.getValue());
            } else {
                String cutDownValue = cell.getValue().substring(0, 14) + "<...>";
                System.out.printf(format, " " + cutDownValue);
            }
        }
    }

    private void setCellMetrics(TreeMap<Integer, Cell> row, Cell cell) {
        Cell currentCell = row.get(cell.getPosition().getY());
        currentCell.setValue(cell.getValue());
        currentCell.setFormula(cell.getFormula());
        Position pos = currentCell.getPosition();
        if (!this.cellsWithFormula.containsKey(pos)) {
            this.addAllCell(pos);
        }
        if (this.cellsWithFormula.containsValue(pos) && cell.getFormula().isEmpty()) {
            this.removeCell(pos);
        }
    }

    private void removeCell(Position mainPosition) {
        for (Position pos: this.cellsIncludedInTheFormula) {
            this.cellsWithFormula.remove(pos, mainPosition);
        }
    }

    private void addAllCell(Position mainPosition) {
        for (Position pos: this.cellsIncludedInTheFormula) {
            this.cellsWithFormula.put(pos, mainPosition);
        }
    }
}
