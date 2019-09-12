package com.vektor.salestaxes.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(String.format("Invalid input: %s.", message));
    }
}