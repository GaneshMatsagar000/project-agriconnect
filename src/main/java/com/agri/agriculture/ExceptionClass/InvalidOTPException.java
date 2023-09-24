package com.agri.agriculture.ExceptionClass;


public class InvalidOTPException extends RuntimeException {
    public InvalidOTPException(String message) {
        super(message);
    }
}
