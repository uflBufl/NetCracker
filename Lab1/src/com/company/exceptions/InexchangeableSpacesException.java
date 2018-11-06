package com.company.exceptions;

//Ошибка: несоответствия обменивающихся помещений
public class InexchangeableSpacesException extends Exception {
    public InexchangeableSpacesException() {
        super("Комнаты не могут поменяться местами.");
    }
}
