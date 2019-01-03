package com.javacourse2018.model;

public class CommitInfo {
    private Person author;
    private String date;
    private String subject;
    private String fileName;

    public String getFileName() { return this.fileName; }
    public void setFileName(String newName) { this.fileName = newName; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommitInfo that = (CommitInfo) o;
        return getAuthor().getName().equals(that.getAuthor().getName())
                && getDate().equals(that.getDate())
                && getSubject().equals(that.getSubject())
                && getFileName().equals(that.getFileName());
    }

    @Override
    public int hashCode() {
        int result = getDate().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }


}
