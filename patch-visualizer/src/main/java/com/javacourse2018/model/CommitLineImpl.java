package com.javacourse2018.model;

public class CommitLineImpl implements CommitLine {
    private CommitLineStatus status;
    private String payload;
    private int index;

    public CommitLineStatus getStatus() {
        return this.status;
    }
    public void setStatus(CommitLineStatus status) {
        this.status = status;
    }

    public String getPayload() {
        return this.payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }

    public int getIndex() {
        return this.index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
