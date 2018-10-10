package com.company.buildings;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

//todo аналогично Flat
public class Office {
    private double square;
    private int rooms;

    public Office(){
        this(250);
    }

    public Office(double square){
        this(square,1);
    }

    public Office(double square, int rooms){
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
