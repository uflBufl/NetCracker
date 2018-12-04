package com.company.buildings.dwelling.hotel;

import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

public class Hotel extends Dwelling {
    public Hotel(int numFloor, int... numFlat) {
        super(numFloor,numFlat);
    }

    public Hotel(Floor... dwellingFloors) {
        super(dwellingFloors);
    }

    public int getStars(){
    int stars = 0;
        for (Floor floor:getFloors()
             ) {
            if(floor.getClass() == HotelFloor.class && ((HotelFloor)floor).getStars() > stars)
                stars = ((HotelFloor)floor).getStars();
        }
    return stars;
    }

    @Override
    public Space getBestSpace() {
        Space bestSpace = null;
        float lastCoeff = 0;

        for(Floor floor : getFloors()) {
            if(floor.getClass() == HotelFloor.class) {
                float coeff = ((HotelFloor) floor).getStars() * 0.25f;
                Space localBestSpace = floor.getBestSpace();

                if(bestSpace == null ||
                        bestSpace.getSquare() * lastCoeff < localBestSpace.getSquare() * coeff) {

                    bestSpace = localBestSpace;
                    lastCoeff = coeff;
                }
            }
        }

        if(bestSpace == null)
            bestSpace = super.getBestSpace();

        return bestSpace;
    }

}
