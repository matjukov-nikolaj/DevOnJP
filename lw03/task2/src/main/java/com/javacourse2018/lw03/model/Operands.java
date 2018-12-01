package com.javacourse2018.lw03.model;

public class Operands {

    private static final Double ZERO = 0.0d;

    private Double first = ZERO;

    private Double second = ZERO;

    private String firstStr = "";

    private String secondStr = "";

    private String result = "";

    public void clear() {
        this.first = ZERO;
        this.second = ZERO;
        this.firstStr = "";
        this.secondStr = "";
        this.result = "";
    }

    public Double zero() {
        return ZERO;
    }

    public String getResult() {
        return result;
    }

    public Double getFirst() {
        return first;
    }

    public Double getSecond() {
        return second;
    }

    public static Double getZERO() {
        return ZERO;
    }

    public String getFirstStr() {
        return firstStr;
    }

    public String getSecondStr() {
        return secondStr;
    }

    public void setFirst(Double firstOperand) {
        this.first = firstOperand;
    }

    public void setFirstStr(String firstOperandStr) {
        this.firstStr = firstOperandStr;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setSecond(Double secondOperand) {
        this.second = secondOperand;
    }

    public void setSecondStr(String secondOperandStr) {
        this.secondStr = secondOperandStr;
    }

}