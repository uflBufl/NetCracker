package com.company.exceptions;

public class BuildingUnderArrestException extends Throwable {
    public BuildingUnderArrestException(){
        super("Здание арестованно.");
    }
}
