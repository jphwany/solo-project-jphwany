package jphwany.soloproject.api.v1.exception;

import lombok.Getter;

public enum ExceptionCode {
    COMPANY_NAME_EXISTS(409, "Company Name exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
