package com.company.buildings.dwelling;

import com.company.interfaces.Building;
import com.company.interfaces.BuildingFactory;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

public class DwellingFactory implements BuildingFactory {
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
        DwellingFloor dwellingFloor=new DwellingFloor(spacesCount);
        return dwellingFloor;
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        DwellingFloor dwellingFloor=new DwellingFloor(spaces);
        return dwellingFloor;
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        Dwelling dwelling=new Dwelling(floorsCount,spacesCounts);
        return dwelling;
    }
    @Override
    public Building createBuilding(Floor[] floors) {
        Dwelling dwelling=new Dwelling(floors);
        return dwelling;
    }
}
