package com.gingermadfire.javaeesimplesite.exception;

public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String message, Throwable exception) {
        super(message, exception);
    }

}
