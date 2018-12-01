package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.model.Cell;
import com.javacourse2018.lw03.model.Position;
import com.javacourse2018.lw03.model.Spreadsheet;
import org.junit.Before;

import java.io.PrintStream;

public class FormulaCalculatorImplTests {

  private Spreadsheet spreadsheet = new Spreadsheet();

  @Before
  public void setUp() {
    Position pos1 = new Position('A', 1);
    Position pos2 = new Position('A', 2);
    Position pos3 = new Position('A', 3);
    Position pos4 = new Position('A', 4);
    Position pos5 = new Position('A', 5);
    Cell cell1 = new Cell(pos1, "1");
    Cell cell2 = new Cell(pos2, "2");
    Cell cell3 = new Cell(pos3, "3");
    Cell cell4 = new Cell(pos4, "4");
    Cell cell5 = new Cell(pos5, "5");
    spreadsheet.setCell(cell1);
  }

}
