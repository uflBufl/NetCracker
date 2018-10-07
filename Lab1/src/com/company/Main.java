package com.company;

public class Main {

    public static void main(String[] args) {

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
