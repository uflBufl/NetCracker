package com.company;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

public interface Space {
    int getRooms();
    void setRooms(int roomsCount)  throws InvalidRoomsCountException;
    double getSquare();
    void setSquare(double square) throws InvalidSpaceAreaException;
}
