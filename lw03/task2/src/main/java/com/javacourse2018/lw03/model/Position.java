package com.javacourse2018.lw03.model;

import java.util.Comparator;

public class Position implements Comparable<Position>, IPosition {

    private Character x;

    private Integer y;

    public Position() {}

    public Position(Character x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(Character x) { this.x = x; }

    @Override
    public void setY(Integer y) { this.y = y; }

    @Override
    public Character getX() { return this.x; }

    @Override
    public Integer getY() { return this.y; }

    @Override
    public int compareTo(Position object) {
        if (object == null) {
            throw new NullPointerException("Null parameter");
        } else if (!this.getClass().equals(object.getClass())) {
            throw new ClassCastException("Possible ClassLoader issue.");
        } else {
            return Comparator.comparing(Position::getX)
                    .thenComparing(Position::getY)
                    .compare(this, object);
        }

    }

}
