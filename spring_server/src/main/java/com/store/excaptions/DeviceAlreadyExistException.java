package com.store.excaptions;

public class DeviceAlreadyExistException extends Exception {
    public DeviceAlreadyExistException(String message) {
        super(message);
    }
}
