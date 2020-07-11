package com.lupin.security.common;

public enum ErrorCode {
    FAIL(1),
    SUCCESS(0),
    FAIL_SEARCH("Fail to Search"),
    FAIL_UPDATE("Fail to Update"),
    FAIL_SAME_NAME("Fail to add the same name book")
    ;

    public String nameError;
    public int ID;

    ErrorCode(String nameError) {
        this.nameError = nameError;
    }

    ErrorCode(int id){
        this.ID = id;
    }

    public String getErrorCode() {
        return null;
    }
}
