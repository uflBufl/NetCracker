package com.company.buildings.dwelling.hotel;

import com.company.buildings.dwelling.Flat;
import com.company.interfaces.Building;
import com.company.interfaces.BuildingFactory;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

public class HotelFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        Flat flat = new Flat(area);
        return  flat;
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        Flat flat = new Flat(area,roomsCount);
        return flat;
    }

    @Override
    public Floor createFloor(int spacesCount) {
        HotelFloor hotelFloor=new HotelFloor(spacesCount);
        return hotelFloor;
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        HotelFloor hotelFloor=new HotelFloor(spaces);
        return hotelFloor;
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        Hotel hotel=new Hotel(floorsCount, spacesCounts);
        return hotel;
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        Hotel hotel=new Hotel(floors);
        return hotel;
    }
}
