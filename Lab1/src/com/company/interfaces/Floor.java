package com.company.interfaces;

import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public interface Floor extends Serializable,Cloneable,Comparable<Floor>,Iterable<Space> {
    Object clone() throws CloneNotSupportedException;

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
