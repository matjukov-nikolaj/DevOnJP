package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.model.Cell;
import com.javacourse2018.lw03.model.Position;
import com.javacourse2018.lw03.model.Spreadsheet;
import org.junit.Assert;
import org.junit.Test;

public class FormulaCalculatorImplTests {

    @Test
    public void can_calculate_1() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + / a1 a2 * a1 a5");
        Assert.assertEquals("5.5", calculator.getCalculationResult());
    }

    @Test
    public void can_calculate_2() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + + * ( a4 a3 ) a2 / a1 a5");
        Assert.assertEquals("14.2", calculator.getCalculationResult());
    }

    @Test
    public void can_calculate_3() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + / * - a2 a3 a4 a1 a5");
        Assert.assertEquals("1.0", calculator.getCalculationResult());
    }

    @Test
    public void can_add_numbers() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + a1 a2");
        Assert.assertEquals("3.0", calculator.getCalculationResult());
    }

    @Test
    public void can_sub_numbers() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" - a5 a2");
        Assert.assertEquals("3.0", calculator.getCalculationResult());
    }

    @Test
    public void can_multiply_numbers() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" * a5 a2");
        Assert.assertEquals("10.0", calculator.getCalculationResult());
    }

    @Test
    public void can_division_numbers() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" / a5 a2");
        Assert.assertEquals("2.5", calculator.getCalculationResult());
    }

    @Test
    public void can_concatenate_strings_in_cells() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + a6 a7");
        Assert.assertEquals("abccba", calculator.getCalculationResult());

    }

    @Test
    public void can_concatenate_string_with_number() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + a1 a7");
        Assert.assertEquals("1.0cba", calculator.getCalculationResult());

    }

    @Test
    public void can_concatenate_string() {
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + a b");
        Assert.assertEquals("ab", calculator.getCalculationResult());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throw_exception_if_formula_contains_nonexisting_cell(){
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + a1 a8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_trying_multiply_string_in_first_argument(){
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" * a6 a1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_trying_division_string_in_second_argument(){
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" / a1 a7");
    }

    @Test(expected = IllegalArgumentException.class)
    public void if_formula_contains_incorrect_operation_throw_exception(){
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" +a a1 a7");
    }

    @Test(expected = IllegalArgumentException.class)
    public void if_formula_incorrect_throw_exception(){
        Spreadsheet spreadsheet = new Spreadsheet();
        this.createSpreadsheet(spreadsheet);
        FormulaCalculatorImpl calculator = new FormulaCalculatorImpl(spreadsheet);
        calculator.calculate(" + a3 a1 a7");
    }

    private void createSpreadsheet(Spreadsheet spreadsheet) {
        Position pos1 = new Position('A', 1);
        Position pos2 = new Position('A', 2);
        Position pos3 = new Position('A', 3);
        Position pos4 = new Position('A', 4);
        Position pos5 = new Position('A', 5);
        Position pos6 = new Position('A', 6);
        Position pos7 = new Position('A', 7);
        Cell cell1 = new Cell(pos1, "1");
        Cell cell2 = new Cell(pos2, "2");
        Cell cell3 = new Cell(pos3, "3");
        Cell cell4 = new Cell(pos4, "4");
        Cell cell5 = new Cell(pos5, "5");
        Cell cell6 = new Cell(pos6, "abc");
        Cell cell7 = new Cell(pos7, "cba");
        spreadsheet.setCell(cell1);
        spreadsheet.setCell(cell2);
        spreadsheet.setCell(cell3);
        spreadsheet.setCell(cell4);
        spreadsheet.setCell(cell5);
        spreadsheet.setCell(cell6);
        spreadsheet.setCell(cell7);
    }

}
