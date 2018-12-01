package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.model.Spreadsheet;
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
    public void

}
