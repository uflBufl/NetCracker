package com.company.buildings.dwelling.hotel;

import com.company.buildings.dwelling.DwellingFloor;
import com.company.interfaces.Space;

import java.util.Objects;

public class HotelFloor extends DwellingFloor{
    int stars;
    final static private int DEFAULT_STARS = 1;

    public HotelFloor(int num){
        super(num);
        stars = DEFAULT_STARS;
    }

    public HotelFloor(Space spaces[]){
        super(spaces);
        stars = DEFAULT_STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    //todo используй реализацию из DwellingFloor
    public String toString()
    {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("HotelFloor("+getStars()+","+size()+",");
        for(int i=0;i<size();i++){ stringBuffer.append(getSpace(i).toString());if(i!=size()-1) stringBuffer.append(",");}
        stringBuffer.append(")");
        return  stringBuffer.toString();
    }

    //todo используй реализацию из DwellingFloor
    public boolean equals(Object object)
    {
        boolean bool=true;
        if(object.getClass()!=HotelFloor.class)bool=false;
        else
        {
            HotelFloor newHotelFloor=(HotelFloor) object;
            Space[] offices1 = getSpaces();
            Space[] office2 =newHotelFloor.getSpaces();
            if(newHotelFloor.size()!=size())bool=false;
            if(newHotelFloor.getStars()!=getStars())bool=false;
            else
            {
                for(int i=0;i<size();i++)
                {
                    if (offices1[i].getSquare()!=office2[i].getSquare())bool=false;
                    if (offices1[i].getRooms()!=office2[i].getRooms())bool=false;
                }
            }
        }
        return bool;
    }

    //todo используй реализацию из DwellingFloor
    public int hashCode()
    {
        int temp=size()^getStars();
        for(int i=0;i<size();i++)
        {
            temp^= getSpace(i).hashCode();
        }
        return temp;
    }

}
