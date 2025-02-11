package com.codexmind.establishment.exceptions;

public class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Exception ex) {
        super(message, ex);
    }
}
