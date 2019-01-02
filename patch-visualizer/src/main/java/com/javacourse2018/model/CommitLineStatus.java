package com.javacourse2018.model;

import java.util.HashMap;
import java.util.Map;

public enum CommitLineStatus {
    REMOVED("-"),
    INSERTED("+"),
    DEFAULT(" ");

    private final String status;

    CommitLineStatus(final String status) {
        this.status = status;
    }

    private static Map<String, CommitLineStatus> getStatuses() {
        HashMap<String, CommitLineStatus> statuses = new HashMap<>();
        statuses.put(REMOVED.status, REMOVED);
        statuses.put(INSERTED.status, INSERTED);
        statuses.put(DEFAULT.status, DEFAULT);
        return statuses;
    }

    @Override
    public String toString() {
        return this.status;
    }

    public static CommitLineStatus createFromString(String mode) {
        return getStatuses().get(mode);
    }

}
