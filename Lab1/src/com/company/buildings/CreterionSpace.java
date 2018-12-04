package com.company.buildings;

import com.company.interfaces.Space;

import java.util.Comparator;

public class CreterionSpace implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2) {
        int temp = 0;
        if (o1.getRooms() > o2.getRooms()) temp = -1;
        if (o1.getRooms() < o2.getRooms()) temp = 1;
        return temp;
    }
}
