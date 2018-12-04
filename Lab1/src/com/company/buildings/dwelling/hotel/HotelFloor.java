package com.company.buildings.dwelling.hotel;

import com.company.buildings.dwelling.DwellingFloor;
import com.company.interfaces.Space;

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


}
