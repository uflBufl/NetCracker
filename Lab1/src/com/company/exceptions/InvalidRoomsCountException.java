package com.company.exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException{
    public InvalidRoomsCountException() {
        super("Некорректное колличество комнат в помещении");
    }
}
