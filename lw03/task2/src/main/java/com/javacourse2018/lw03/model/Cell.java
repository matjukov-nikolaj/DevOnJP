package com.javacourse2018.lw03.model;

public class Cell {

    private Position position;

    private String value;

    private String formula;

    public Cell() {

    }

    public Cell(Position position, String value, String formula) {
        this.position = position;
        this.value = value;
        this.formula = formula;
    }

    public Cell(Position position, String value) {
        this.position = position;
        this.value = value;
        this.formula = "";
    }

    public Cell(Position position) {
        this.position = position;
        this.value = "";
        this.formula = "";
    }

    public Position getPosition() { return this.position; }

    public String getValue() { return this.value; }

    public String getFormula() { return this.formula; }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }


}
