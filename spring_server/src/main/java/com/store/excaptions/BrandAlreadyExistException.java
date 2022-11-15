package com.store.excaptions;

public class BrandAlreadyExistException extends Exception{
    public BrandAlreadyExistException(String message) {
        super(message);
    }
}
