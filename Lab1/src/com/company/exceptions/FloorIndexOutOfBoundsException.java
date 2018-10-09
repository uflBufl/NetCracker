package com.company.exceptions;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public FloorIndexOutOfBoundsException() {
        super("Нет этажа с таким номером.");
    }
}
