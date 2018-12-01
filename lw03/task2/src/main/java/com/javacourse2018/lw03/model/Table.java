package com.javacourse2018.lw03.model;

public interface Table {

    void setCell(Cell cell);

    Cell getCell(Position position) throws IndexOutOfBoundsException;

    void display();

}
