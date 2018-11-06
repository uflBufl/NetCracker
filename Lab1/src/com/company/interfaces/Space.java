package com.company.interfaces;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

public interface Space {
    Object clone();

    int getRooms();
    void setRooms(int roomsCount)  throws InvalidRoomsCountException;
    double getSquare();
    void setSquare(double square) throws InvalidSpaceAreaException;
}
