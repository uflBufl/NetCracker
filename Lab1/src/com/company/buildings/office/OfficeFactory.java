package com.company.buildings.office;

import com.company.interfaces.Building;
import com.company.interfaces.BuildingFactory;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        Office office=new Office(area);
        return  office;
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        Office office=new Office(area,roomsCount);
        return  office;
    }

    @Override
    public Floor createFloor(int spacesCount) {
        OfficeFloor officeFloor=new OfficeFloor(spacesCount);
        return officeFloor;
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        OfficeFloor officeFloor=new OfficeFloor(spaces);
        return officeFloor;
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        OfficeBuilding officeBuilding=new OfficeBuilding(floorsCount,spacesCounts);
        return officeBuilding;
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        OfficeBuilding officeBuilding=new OfficeBuilding(floors);
        return  officeBuilding;
    }
}
