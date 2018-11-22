package com.javacourse2018.lw03.model;

public class Position {

    private Character x;

    private Integer y;

    public Position() {}

    public Position(Character x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Character x) { this.x = x; }

    public void setY(Integer y) { this.y = y; }

    public Character getX() { return this.x; }

    public Integer getY() { return this.y; }

}
