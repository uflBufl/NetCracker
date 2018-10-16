package com.company;

import com.company.buildings.Flat;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

public interface Floor {
    int size();
    double squareTotal();
    int roomsCountTotal();
    Space[] getSpaces();
    Space getSpace(int num)throws SpaceIndexOutOfBoundsException;
    void setSpace(int num, Space space)throws SpaceIndexOutOfBoundsException;
    void addSpace(int num, Space space)throws SpaceIndexOutOfBoundsException;
    void deleteSpace(int num)throws SpaceIndexOutOfBoundsException;
    Space getBestSpace();
}
