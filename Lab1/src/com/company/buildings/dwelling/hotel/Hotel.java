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
        //todo нахер ты в foreach getFloors() засунул? Ты для кого реализовывал паттерн иитератор? Нужно this туда пихать
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

    public String toString()
    {
        //todo используй реализацию из DwellingFloor
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("Hotel("+getStars()+","+size()+",");
        for(int i=0;i<size();i++){ stringBuffer.append(getFloorByNum(i).toString());if(i!=size()-1) stringBuffer.append(",");}
        stringBuffer.append(")");
        return  stringBuffer.toString();
    }

    public boolean equals(Object object)
    {
        //todo используй реализацию из DwellingFloor
        boolean bool=true;
        if(object.getClass()!=Hotel.class)bool=false;
        else
        {
            Hotel newHotel=(Hotel) object;
            Floor[] floors1 = getFloors();
            Floor[] floors2 =newHotel.getFloors();
            if(newHotel.size()!=size())bool=false;
            else
            {
                for(int i=0;i<size();i++)
                {
                    if (!floors1[i].equals(floors2[i]))bool=false;
                }
            }
        }
        return bool;
    }

    public int hashCode()
    {
        //todo используй реализацию из DwellingFloor
        int temp=size();
        for(int i=0;i<size();i++)
        {
            temp^=getFloorByNum(i).hashCode();
        }
        return temp;
    }

}
