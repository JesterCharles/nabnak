package com.revature.nabnak.util.exceptions;

public class InvalidUserInputException extends RuntimeException{ // unchecked exception
    public InvalidUserInputException(String message) {
        super(message);
    }
}
