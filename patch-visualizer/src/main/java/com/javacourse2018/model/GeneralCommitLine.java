package com.javacourse2018.model;

public class GeneralCommitLine extends CommitLineImpl {
    private int prevIndex;

    public void setPrevIndex(int prevIndex) {
        this.prevIndex = prevIndex;
    }

    public int getPrevIndex() {
        return this.prevIndex;
    }
}
