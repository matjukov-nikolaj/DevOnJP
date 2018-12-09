package com.javacourse2018.lw03;

import com.javacourse2018.lw03.service.Supermarket;

public class App
{
    public static void main( String[] args )
    {
        try {
            Supermarket supermarket = new Supermarket();
            supermarket.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
