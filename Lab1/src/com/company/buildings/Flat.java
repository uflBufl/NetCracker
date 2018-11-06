package com.company.buildings;

import com.company.interfaces.Space;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.util.Objects;

public class Flat implements Space, Serializable {
    private double square;
    private int roomsCount;
    final static private int DEFAULT_ROOMS = 2;
    final static private double DEFAULT_SQUARES = 50;

    public Flat(){
        this(DEFAULT_SQUARES);
    }

    public Flat(double square){
        this(square, DEFAULT_ROOMS);
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
    public String toString() {
        return "Flat (" + roomsCount + ", " + square + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Double.compare(flat.square, square) == 0 &&
                roomsCount == flat.roomsCount;
    }

    @Override
    public int hashCode() {

        return Objects.hash(square, roomsCount);
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
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
