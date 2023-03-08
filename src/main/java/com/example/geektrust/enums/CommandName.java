package com.example.geektrust.enums;

public enum CommandName {
    ADD_COURSE_OFFERING(5),
    REGISTER(2),
    ALLOT(1),
    CANCEL(1);

    private final int argsLength;

    CommandName(int argsLength) {
        this.argsLength = argsLength;
    }

    public int getArgsLength() {
        return this.argsLength;
    }
}
