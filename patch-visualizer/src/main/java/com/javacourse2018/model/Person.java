package com.javacourse2018.model;

public class Person {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!o.getClass().equals(Person.class)) {
            return false;
        }
        Person person = (Person) o;
        return person.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }

}