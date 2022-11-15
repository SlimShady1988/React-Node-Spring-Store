package com.store.excaptions;

public class TypeAlreadyExistException extends Exception{
    public TypeAlreadyExistException(String message) {
        super(message);
    }
}
