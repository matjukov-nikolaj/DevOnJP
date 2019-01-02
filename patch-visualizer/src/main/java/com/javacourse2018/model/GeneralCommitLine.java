package com.javacourse2018.model;

public class GeneralCommitLine extends CommitLineImpl {
    private Integer prevIndex;

    public void setPrevIndex(Integer prevIndex) {
        this.prevIndex = prevIndex;
    }

    public Integer getPrevIndex() {
        return this.prevIndex;
    }
}
