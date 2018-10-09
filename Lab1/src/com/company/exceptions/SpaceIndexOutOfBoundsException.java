package com.company.exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{
    public SpaceIndexOutOfBoundsException() {
        super("Нет помещения с таким номером.");
    }
}
