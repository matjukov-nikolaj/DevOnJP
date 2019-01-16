package com.javacourse2018.model;

public interface CommitLine {

    CommitLineStatus getStatus();
    void setStatus(CommitLineStatus status);

    String getPayload();
    void setPayload(String payload);

    int getIndex();
    void setIndex(int index);

}
