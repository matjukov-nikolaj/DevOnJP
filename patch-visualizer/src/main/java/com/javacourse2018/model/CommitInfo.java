package com.javacourse2018.model;

public class CommitInfo {
    private Person author;
    private String date;
    private String subject;

    public Person getAuthor() {
        return this.author;
    }
    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

}
