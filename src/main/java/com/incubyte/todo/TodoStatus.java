package com.incubyte.todo;

import java.util.Map;

import static java.util.Map.entry;

public enum TodoStatus {
    TO_DO,
    IN_PROGRESS,
    DONE,
    ARCHIVE;

    private static final Map<TodoStatus, String> todoStatus;

    static {
        todoStatus =
                Map.ofEntries(
                        entry(TO_DO, "to_do"),
                        entry(IN_PROGRESS, "in_progress"),
                        entry(DONE, "done"),
                        entry(ARCHIVE, "archive"));
    }


    public String getValue() {
        return todoStatus.get(this);
    }
}
