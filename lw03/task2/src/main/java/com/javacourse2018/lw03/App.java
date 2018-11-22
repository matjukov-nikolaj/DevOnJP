package com.javacourse2018.lw03;

import com.javacourse2018.lw03.model.Spreadsheet;
import com.javacourse2018.lw03.service.SpreadsheetEngine;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Spreadsheet spreadsheet = new Spreadsheet();
            SpreadsheetEngine engine = new SpreadsheetEngine(spreadsheet);
            engine.control();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
