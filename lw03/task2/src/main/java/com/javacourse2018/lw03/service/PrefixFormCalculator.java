package com.javacourse2018.lw03.service;

public interface PrefixFormCalculator {

    public void calculate(String formula) throws IllegalArgumentException, IndexOutOfBoundsException;

    void clearResult();

    public String getCalculationResult();

}
