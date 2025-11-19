package com.itvitae.projectmanagement_backend.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
