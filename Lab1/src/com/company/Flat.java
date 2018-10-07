package com.company;

public class Flat {
    private double square;
    private int rooms;

    public Flat(){
        this(50);
    }

    public Flat(double square){
        this(square,2);
    }

    public Flat(double square, int rooms){
        this.square = square;
        this.rooms = rooms;
    }

    public int getRooms(){
        return rooms;
    }

    public void setRooms(int rooms){
        this.rooms = rooms;
    }

    public double getSquare(){
        return square;
    }

    public void setSquare(double square){
        this.square = square;
    }

}
