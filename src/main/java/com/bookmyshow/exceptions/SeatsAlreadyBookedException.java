package com.bookmyshow.exceptions;

public class SeatsAlreadyBookedException extends Exception {
    public SeatsAlreadyBookedException(String message) {
        super(message);
    }
}
