package com.company.exceptions;

public class InexchangeableFloorsException extends Exception {
    public InexchangeableFloorsException() {
        super("Этажи не могут поменяться местами.");
    }
}
