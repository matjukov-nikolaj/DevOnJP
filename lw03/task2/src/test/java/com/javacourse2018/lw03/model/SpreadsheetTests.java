package com.javacourse2018.lw03.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SpreadsheetTests {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void can_set_cell() {
        Spreadsheet spreadsheet = new Spreadsheet();
        Position pos = new Position('A', 1);
        Cell cell = new Cell(pos, "check");
        spreadsheet.setCell(cell);
        Assert.assertEquals("check", spreadsheet.getCell(pos).getValue());
        Assert.assertEquals(cell, spreadsheet.getCell(pos));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throw_exception_if_trying_get_nonexisting_cell() {
        Spreadsheet spreadsheet = new Spreadsheet();
        Position pos = new Position('A', 1);
        spreadsheet.getCell(pos);
    }

    @Test
    public void can_display_table() {
        Spreadsheet spreadsheet = new Spreadsheet();
        Position pos = new Position('A', 1);
        Cell cell = new Cell(pos, "1");
        spreadsheet.setCell(cell);
        spreadsheet.display();

        Assert.assertEquals("Test #1", "OK\r\n" +
                "                     A\r\n" +
                "1                    1\r\n", output.toString());
    }

}
