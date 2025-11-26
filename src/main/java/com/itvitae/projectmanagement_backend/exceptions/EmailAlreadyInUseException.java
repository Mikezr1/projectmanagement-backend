package com.itvitae.projectmanagement_backend.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException(String message) { super(message); }
}
