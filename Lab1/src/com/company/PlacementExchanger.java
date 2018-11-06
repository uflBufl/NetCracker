package com.company;

import com.company.exceptions.InexchangeableFloorsException;
import com.company.exceptions.InexchangeableSpacesException;
import com.company.interfaces.Building;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

public class PlacementExchanger {

    public static boolean isExchange(Space space1, Space space2){
        return space1.getSquare() == space2.getSquare() &&
                space1.getRooms() == space2.getRooms();
    }

    public static boolean isExchange(Floor floor1, Floor floor2){
        return floor1.squareTotal() == floor2.squareTotal() &&
                floor1.roomsCountTotal() == floor2.roomsCountTotal();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2)
            throws InexchangeableSpacesException {
        if(isExchange(floor1.getSpace(index1),floor2.getSpace(index2))){
            Space tmp = floor1.getSpace(index1);
            floor1.setSpace(index1, floor2.getSpace(index2));
            floor2.setSpace(index2, tmp);
        }
        else throw new InexchangeableSpacesException();
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2)
            throws InexchangeableFloorsException {
        if(isExchange(building1.getFloorByNum(index1),building2.getFloorByNum(index2))){
            Floor tmp = building1.getFloorByNum(index1);
            building1.setFloor(index1, building2.getFloorByNum(index2));
            building2.setFloor(index2, tmp);
        }
        else throw new InexchangeableFloorsException();
    }

}
