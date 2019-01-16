package com.javacourse2018.model;

public class Person {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Person person) {
        if (person == null) {
            return false;
        }

        return person.getName().equals(this.getName());
    }
}