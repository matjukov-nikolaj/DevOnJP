package com.javacourse2018.lw03;
import com.javacourse2018.lw03.service.SpreadsheetEngine;

public class App 
{
    public static void main( String[] args )
    {
        try {
            SpreadsheetEngine engine = new SpreadsheetEngine();
            engine.control();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
