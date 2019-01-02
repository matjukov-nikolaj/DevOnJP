package com.javacourse2018.model;

import java.util.List;

public class DifferenceBlock {

    private CommitChunkPosition prevCommit;
    private CommitChunkPosition currCommit;
    private String functionName;
    private List<CommitLine> commitLines;

    public CommitChunkPosition getPrevCommit() {
        return this.prevCommit;
    }

    public void setPrevCommit(CommitChunkPosition prevCommit) {
        this.prevCommit = prevCommit;
    }

    public CommitChunkPosition getCurrCommit() {
        return this.currCommit;
    }

    public void setCurrCommit(CommitChunkPosition currCommit) {
        this.currCommit = currCommit;
    }

    public List<CommitLine> getCommitLines() {
        return commitLines;
    }

    public void setCommitLines(List<CommitLine> commitLines) {
        this.commitLines = commitLines;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }
}
