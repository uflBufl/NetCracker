package com.company;

import com.company.buildings.Office;
import com.company.buildings.OfficeBuilding;
import com.company.buildings.OfficeFloor;

public class Main {

    public static void main(String[] args) {

        OfficeFloor[] officeFloor = new OfficeFloor[3];
        for(int i = 0;i<3;i++)
        {
            Office[] offices = new Office[3];
            for(int j = 0;j<3;j++){
                offices[j] = new Office(5*j,i);
            }
            officeFloor[i] = new OfficeFloor(offices);
        }
        OfficeBuilding building = new OfficeBuilding(officeFloor);

        Office office123 = new Office();

        System.out.println(office123.getSquare());

        building.newOfficeByNum(3,office123);

        for(int i = 0;i<building.getNumFloors();i++){
            OfficeFloor of = building.getFloorByNum(i);
            for(int j = 0;j<of.getNumOffices();j++){
                Office office = of.getOffice(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(office.getSquare());
                System.out.println(office.getRooms());
                System.out.println();
            }
        }

        building.deleteOfficeByNum(3);

        for(int i = 0;i<building.getNumFloors();i++){
            OfficeFloor of = building.getFloorByNum(i);
            for(int j = 0;j<of.getNumOffices();j++){
                Office office = of.getOffice(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(office.getSquare());
                System.out.println(office.getRooms());
                System.out.println();
            }
        }

        Office[] offices = building.getSortOffices();

        for(int i = 0;i<offices.length;i++){
            System.out.println(offices[i].getSquare());
            System.out.println(offices[i].getRooms());

        }

        Office of = new Office(-123,-45);


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

        System.out.println(flat123.getSquare());

        dwelling.newFlatByNum(3,flat123);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.getNumFlats();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.getSquare());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        dwelling.deleteFlatByNum(3);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.getNumFlats();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.getSquare());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        Flat[] flats = dwelling.getSortFlats();

        for(int i = 0;i<flats.length;i++){
            System.out.println(flats[i].getSquare());
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
                System.out.println(flat.getSquare());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }




        Flat newFlat = new Flat();
        Flat[] newFlats = new Flat[1];
        newFlats[0] = newFlat;
        DwellingFloor newDw = new DwellingFloor(newFlats);

        dwelling.editFloor(1,newDw);


        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.getNumFlats();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.getSquare());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        Flat flat123 = dwelling.getBestSpace();

        System.out.println(flat123.getSquare());

        dwelling.deleteFlatByNum(3);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.getNumFlats();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.getSquare());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }

        dwelling.newFlatByNum(3,flat123);

        for(int i = 0;i<dwelling.getNumFloors();i++){
            DwellingFloor dw = dwelling.getFloorByNum(i);
            for(int j = 0;j<dw.getNumFlats();j++){
                Flat flat = dw.getFlat(j);
                System.out.println(i+ " этаж "+j+" Квартира");
                System.out.println(flat.getSquare());
                System.out.println(flat.getRooms());
                System.out.println();
            }
        }
        */

    }
}
