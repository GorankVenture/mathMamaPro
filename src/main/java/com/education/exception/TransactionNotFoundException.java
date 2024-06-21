package com.education.exception;

public class TransactionNotFoundException  extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
