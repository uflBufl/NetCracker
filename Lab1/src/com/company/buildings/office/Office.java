package com.company.buildings.office;

import com.company.interfaces.Space;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.util.Objects;

public class Office implements Space, Serializable, Cloneable,Comparable<Space> {
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
    public String toString() {
        return "Office (" + roomsCount + ", " + square + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Double.compare(office.square, square) == 0 &&
                roomsCount == office.roomsCount;
    }

    @Override
    public int hashCode() {

        return Objects.hash(square, roomsCount);
    }

    @Override
    public Space clone(){
        try {
            return (Office)super.clone();
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

    @Override
    public int compareTo(Space o) {
        int temp=0;
        if(getSquare()>o.getSquare()) temp=1;
        if(getSquare()<o.getSquare()) temp=-1;
        return  temp;
    }

}
