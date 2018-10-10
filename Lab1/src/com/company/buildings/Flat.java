package com.company.buildings;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

public class Flat {
    private double square;
    private int rooms; //todo roomsQuantity или roomsCount

    //todo литералы в коде - плохой вариант. Вынеси их в константы класса
    public Flat(){
        this(50);
    }

    public Flat(double square){
        this(square,2);
    }

    public Flat(double square, int rooms){
        if (rooms <0){
            throw new InvalidRoomsCountException();
        }
        if(square <0){
            throw new InvalidSpaceAreaException();
        }
        this.square = square;
        this.rooms = rooms;
    }

    public int getRooms(){
        return rooms;
    }

    public void setRooms(int rooms){
        if (rooms <0){
            throw new InvalidRoomsCountException();
        }
        this.rooms = rooms;
    }

    public double getSquare(){
        return square;
    }

    public void setSquare(double square){
        if(square <0){
            throw new InvalidSpaceAreaException();
        }
        this.square = square;
    }

}
