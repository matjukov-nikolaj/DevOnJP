package com.javacourse2018.model;

public class CommitLineImpl implements CommitLine {
    private CommitLineStatus status;
    private String payload;
    private Integer index;

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

    public Integer getIndex() {
        return this.index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }
}
