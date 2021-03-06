package com.company.interfaces;

import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public interface Building extends Serializable,Cloneable,Iterable<Floor> {
    Object clone() throws CloneNotSupportedException;

    int getNumFloors();

    int size();

    double squareTotal();

    int roomsCountTotal();

    Floor[] getFloors();

    Floor getFloorByNum(int numFloor)throws FloorIndexOutOfBoundsException;

    void setFloor(int numFloor, Floor dwellingFloor)throws FloorIndexOutOfBoundsException;

    Space getSpaceByNum(int numFlat)throws SpaceIndexOutOfBoundsException;

    void setSpaceByNum(int numFlat, Space flat)throws SpaceIndexOutOfBoundsException;

    void addSpaceByNum(int numFlat, Space flat)throws SpaceIndexOutOfBoundsException;

    void deleteSpaceByNum(int numFlat)throws SpaceIndexOutOfBoundsException;

    Space getBestSpace();
    Space[] getSortSpaces();
}
