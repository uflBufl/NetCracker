package com.company.buildings;

import com.company.interfaces.Floor;

import java.util.Comparator;

public class CreterionFloor implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        int temp = 0;
        if (o1.size() > o2.size()) temp = -1;
        if (o1.size() < o2.size()) temp = 1;
        return temp;
    }
}
