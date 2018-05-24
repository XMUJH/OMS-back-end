package com.wedo.OMS.exception;

class BaseException extends Exception {
    private String errorCode;

    BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
