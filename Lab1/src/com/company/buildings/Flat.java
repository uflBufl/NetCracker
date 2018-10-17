package com.company.buildings;

import com.company.interfaces.Space;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

public class Flat implements Space {
    private double square;
    private int roomsCount;
    final static private int defaultRooms = 2; //todo константы класса в Java пишутся в формате ROOMS_COUNT_DEFAULT - примени такой стиль ко всем константам класса
    final static private double defaultSquares = 50;

    public Flat(){
        this(defaultSquares);
    }

    public Flat(double square){
        this(square,defaultRooms);
    }

    public Flat(double square, int roomsCount)   throws InvalidRoomsCountException, InvalidSpaceAreaException{
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
    public void setRooms(int roomsCount){
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
