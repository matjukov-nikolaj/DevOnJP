package com.javacourse2018.lw03.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class SpreadsheetEngineTests {

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
    public void can_read_commands_from_input() {
        String input = "set a1 1" + "\r\n" + "display";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SpreadsheetEngine engine = new SpreadsheetEngine();
        engine.control();

        Assert.assertEquals("Test #1", "> OK\r\n" +
                ">                      A\r\n" +
                "1                    1\r\n" +
                "> null\r\n", output.toString());
    }

    @Test
    public void if_incorrect_command_show_help_message() {
        String input = "incorrect\r\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SpreadsheetEngine engine = new SpreadsheetEngine();
        engine.control();

        Assert.assertEquals("Test #1", "> Incorrect command.\r\n" +
                "---------------------------------\r\n" +
                "           USED COMMANDS         \r\n" +
                "---------------------------------\r\n" +
                "exit\r\n" +
                "set <coordinate> <value>\r\n" +
                "setformula <coordinate> <formula>\r\n" +
                "Formula example: B1 *(+ A1 5) A1\r\n" +
                "display\r\n" +
                "> ", output.toString());
    }

    @Test
    public void if_incorrect_command_size_show_help_message() {
        String input = "set a1 a2 b2 b3 b4\r\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SpreadsheetEngine engine = new SpreadsheetEngine();
        engine.control();

        Assert.assertEquals("Test #1", "> Incorrect command.\r\n" +
                "---------------------------------\r\n" +
                "           USED COMMANDS         \r\n" +
                "---------------------------------\r\n" +
                "exit\r\n" +
                "set <coordinate> <value>\r\n" +
                "setformula <coordinate> <formula>\r\n" +
                "Formula example: B1 *(+ A1 5) A1\r\n" +
                "display\r\n" +
                "> ", output.toString());
    }

    @Test
    public void if_command_have_invalid_coordinate_the_show_message() {
        String input = "set abc2\r\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SpreadsheetEngine engine = new SpreadsheetEngine();
        engine.control();

        Assert.assertEquals("Test #1", "> Invalid coordinate: abc2\r\n" +
                "---------------------------------\r\n" +
                "           USED COMMANDS         \r\n" +
                "---------------------------------\r\n" +
                "exit\r\n" +
                "set <coordinate> <value>\r\n" +
                "setformula <coordinate> <formula>\r\n" +
                "Formula example: B1 *(+ A1 5) A1\r\n" +
                "display\r\n" +
                "> ", output.toString());
    }

    @Test
    public void if_formula_have_transitive_closure_show_message() {
        String input = "set a1 1\r\nset b2\r\nsetformula b1 + a1 b2\r\nsetformula a1 + b1 b2\r\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SpreadsheetEngine engine = new SpreadsheetEngine();
        engine.control();

        Assert.assertEquals("Test #1", "> OK\r\n" +
                "> OK\r\n" +
                "> OK\r\n" +
                "> Error. Transitive closure.\r\n"
                +"> ", output.toString());
    }

    @Test
    public void if_formula_have_mistake_show_message() {
        String input = "set a1 1\r\nsetformula a2 * a1 abc\r\nexit\r\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SpreadsheetEngine engine = new SpreadsheetEngine();
        engine.control();

        Assert.assertEquals("Test #1", "> OK\r\n" +
                "> Error in the formula.\r\n" +
                "> ", output.toString());
    }

}
