package com.company;

import com.company.buildings.*;

public class Main {

    public static void main(String[] args) {

        Floor[] officeFloor = new Floor[3];
        for(int i = 0;i<3;i++)
        {
            Space[] offices = new Space[3];
            for(int j = 0;j<3;j++){
                offices[j] = new Office(5*j,i);
            }
            officeFloor[i] = new OfficeFloor(offices);
        }
        Building building = new OfficeBuilding(officeFloor);

        Space office123 = new Office();

        System.out.println(office123.getSquare());

        building.addSpaceByNum(3,office123);

        for(int i = 0;i<building.getNumFloors();i++){
            Floor of = building.getFloorByNum(i);
            for(int j = 0; j<of.size(); j++){
                Space office = of.getSpace(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(office.getSquare());
                System.out.println(office.getRooms());
                System.out.println();
            }
        }

        building.deleteSpaceByNum(3);
        building.setSpaceByNum(1,new Office(666,666));
        //Space f = building.getSpaceByNum(1);
        //System.out.println(f.getSquare());
        //System.out.println(f.getRooms());


        for(int i = 0;i<building.getNumFloors();i++){
            Floor of = building.getFloorByNum(i);
            for(int j = 0; j<of.size(); j++){
                Space office = of.getSpace(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(office.getSquare());
                System.out.println(office.getRooms());
                System.out.println();
            }
        }

        Space[] offices = building.getSortSpaces();

        for(int i = 0;i<offices.length;i++){
            System.out.println(offices[i].getSquare());
            System.out.println(offices[i].getRooms());

        }

        //Space of = new Office(-123,-45);



/*
        DwellingFloor[] dwellingFloor = new DwellingFloor[3];
        for(int i = 0;i<3;i++)
        {
            Flat[] flats = new Flat[3];
            for(int j = 0;j<3;j++){
                flats[j] = new Flat(5*j,i);
            }
            dwellingFloor[i] = new DwellingFloor(flats);
        }
        Dwelling dwelling = new Dwelling(dwellingFloor);

        Flat flat123 = new Flat();

        System.out.println(flat123.squareTotal());

        dwelling.addSpaceByNum(3,flat123);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.size();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.squareTotal());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        dwelling.deleteSpaceByNum(3);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.size();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.squareTotal());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        Flat[] flats = dwelling.getSortSpaces();

        for(int i = 0;i<flats.length;i++){
            System.out.println(flats[i].squareTotal());
            System.out.println(flats[i].getRooms());

        }

*/










/*
        System.out.println(dwelling.getNumFloors());

        for(int i = 0;i<3;i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<3;j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.squareTotal());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }




        Flat addFlat = new Flat();
        Flat[] newFlats = new Flat[1];
        newFlats[0] = addFlat;
        DwellingFloor newDw = new DwellingFloor(newFlats);

        dwelling.setFloor(1,newDw);


        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.size();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.squareTotal());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        Flat flat123 = dwelling.getBestSpace();

        System.out.println(flat123.squareTotal());

        dwelling.deleteSpaceByNum(3);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.size();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.squareTotal());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        dwelling.addSpaceByNum(3,flat123);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.size();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.squareTotal());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }
        */

    }
}
