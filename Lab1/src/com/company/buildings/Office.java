package com.company.buildings;

import com.company.interfaces.Space;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

public class Office implements Space {
    private double square;
    private int roomsCount;
    final static private int DEFAULT_ROOMS = 1;
    final static private double DEFAULT_SQUARES = 250;

    public Office(){
        this(DEFAULT_SQUARES);
    }

    public Office(double square){
        this(square, DEFAULT_ROOMS);
    }

    public Office(double square, int roomsCount)  throws InvalidRoomsCountException, InvalidSpaceAreaException{
        if (roomsCount <0){
            throw new InvalidRoomsCountException();
        }
        if(square <0){
            throw new InvalidSpaceAreaException();
        }
        this.square = square;
        this.roomsCount = roomsCount;
    }

    @Override
    public int getRooms(){
        return roomsCount;
    }

    @Override
    public void setRooms(int roomsCount) {
        if (roomsCount <0){
            throw new InvalidRoomsCountException();
        }
        this.roomsCount = roomsCount;
    }

    @Override
    public double getSquare(){
        return square;
    }

    @Override
    public void setSquare(double square){
        if(square <0){
            throw new InvalidSpaceAreaException();
        }
        this.square = square;
    }

}
