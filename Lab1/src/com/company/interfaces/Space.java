package com.company.interfaces;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;

public interface Space extends Serializable,Cloneable {
    Object clone() throws CloneNotSupportedException;

    int getRooms();
    void setRooms(int roomsCount)  throws InvalidRoomsCountException;
    double getSquare();
    void setSquare(double square) throws InvalidSpaceAreaException;
}
