package com.company.exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException{
    public InvalidSpaceAreaException() {
        super("Некорректная площадь помещения");
    }
}
