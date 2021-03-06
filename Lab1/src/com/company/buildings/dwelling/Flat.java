package com.company.buildings.dwelling;

import com.company.interfaces.Space;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.util.Objects;

public class Flat implements Space, Serializable, Cloneable {
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
    public String toString()
    {
        //todo String.format()
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("Flat(");
        stringBuffer.append(getRooms()+",");
        stringBuffer.append(getSquare()+")");
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object object)
    {
        //todo equals при передаче ему null должен возвращать false. у тебя будет выброс NullPointerException
        boolean bool=true;
        // if(!object.toString().contains("Flat"))bool=false;
        /* todo не надо ветвлений и логических переменных надо так:
         if (Objects.isNull(object)) return false;
         if (object.getClass()!=Office.class) return false;
        */
        if(object.getClass()!=Flat.class)bool=false;
        else {
            Flat temp1=(Flat) object;
            //todo return Double.compare(temp1.square, square) == 0 & temp1.roomsCount == roomsCount ;
            if(temp1.getSquare()!=getSquare())bool=false;
            if(temp1.getRooms()!=getRooms())bool=false;
        }
        return  bool;
    }

    @Override
    public int hashCode() {

        return Objects.hash(square, roomsCount);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return  super.clone();
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

    @Override
    public int compareTo(Space o) {
        // todo return Double.compare(square, o.getSquare()); проще да?
        int temp=0;
        if(getSquare()>o.getSquare()) temp=1;
        if(getSquare()<o.getSquare()) temp=-1;
        return  temp;
    }

}
