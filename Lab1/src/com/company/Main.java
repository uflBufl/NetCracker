package com.company;

import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.Office;
import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFloor;
import com.company.buildings.threads.SequentalCleaner;
import com.company.buildings.threads.SequentalRepairer;
import com.company.buildings.threads.Semaphore;
import com.company.interfaces.Building;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Office[] aOffices = new Office[5];
        aOffices[0] = new Office(900);
        aOffices[1] = new Office(115);
        aOffices[2] = new Office(600, 2);
        aOffices[3] = new Office(650);
        aOffices[4] = new Office(950, 4);

        Office[] bOffices = new Office[4];
        bOffices[0] = new Office(555);
        bOffices[1] = new Office(300);
        bOffices[2] = new Office(800, 2);
        bOffices[3] = new Office(700, 1);

        Office[] cOffices = new Office[3];
        cOffices[0] = new Office(500);
        cOffices[1] = new Office(550, 4);
        cOffices[2] = new Office(450, 2);

        Office[] eOffices = new Office[2];
        eOffices[0] = new Office(720, 3);
        eOffices[1] = new Office(350, 3);

        OfficeFloor[] floors = new OfficeFloor[5];
        floors[0] = new OfficeFloor(aOffices);
        floors[1] = new OfficeFloor(bOffices);
        floors[2] = new OfficeFloor(cOffices);
        floors[3] = new OfficeFloor(4);
        floors[4] = new OfficeFloor(eOffices);

        OfficeBuilding building = new OfficeBuilding(floors);
/*
        System.out.print("Исходное здание:       " + building.toString());






        System.out.print("\nБайтовый поток:        ");

        //Запись в байтовый поток
        try (OutputStream out = new FileOutputStream("File.bin")) {

            Buildings.outputBuilding(building, out);
            out.close();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        //Чтение из байтового потока
        try (InputStream in = new FileInputStream("File.bin")) {

            Building building1 = Buildings.inputBuilding(in);
            in.close();

            System.out.print(building1.toString());
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }




        System.out.print("\nСимвольный поток:      ");

        //Запись в символьный поток
        try (Writer out = new FileWriter("File.txt")) {

            Buildings.writeBuilding(building, out);
            out.close();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        //Чтение из символьного потока
        try (Reader in = new FileReader("File.txt")) {

            Building building1 = Buildings.readBuilding(in);
            in.close();

            System.out.print(building1.toString());
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }





        System.out.print("\nСериализация:          ");

        //Сериализация
        try (OutputStream out = new FileOutputStream("SerializeFile.bin")) {
            Buildings.serializeBuilding(building, out);
            out.close();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        //Десериализация
        try (InputStream in = new FileInputStream("SerializeFile.bin")) {

            Building building2 = Buildings.deserialaizeBuilding(in);
            in.close();

            System.out.print(building2.toString());
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }




        Floor[] b = building.getFloors();
        System.out.print("\nФорматированный вывод: ");

        //Форматированная запись в символьный поток
        try (Writer out = new FileWriter("FormattedFile.txt")) {

            Buildings.writeBuildingFormat(building, out);
            out.close();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        //Чтение из форматированного символьного потока
        try (Scanner in = new Scanner(new FileReader("FormattedFile.txt")))  {

            Building building1 = Buildings.readBuilding(in);
            in.close();

            System.out.print(building1.toString());
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        */

        /*

//клонирование
try {
    Object secondbuilding = new Office(5, 3);
    System.out.print("\n" + secondbuilding.toString());
    Object secondbuilding1 = ((Office) secondbuilding).clone();
    System.out.print("\n" + secondbuilding1.toString());
    ((Office) secondbuilding1).setRooms(66);
    System.out.print("\n" + secondbuilding.toString());
    System.out.print("\n" + secondbuilding1.toString());
    System.out.print("\n" + secondbuilding.toString());


    Object secondbuildingf = new Flat(5, 3);
    System.out.print("\n" + secondbuildingf.toString());
    Object secondbuildingf1 = ((Flat) secondbuildingf).clone();
    System.out.print("\n" + secondbuildingf1.toString());
    ((Flat) secondbuildingf1).setRooms(66);
    System.out.print("\n" + secondbuildingf.toString());
    System.out.print("\n" + secondbuildingf1.toString());
    System.out.print("\n" + secondbuildingf.toString());


    Space flats[] = new Flat[2];
    flats[0] = new Flat(4, 5);
    flats[1] = new Flat(8, 10);
    DwellingFloor dw = new DwellingFloor(flats);
    System.out.print("\n" + dw.toString());
    Floor dw1 = (DwellingFloor) dw.clone();
    System.out.print("\n" + dw1.toString());
    System.out.print("\n" + dw.toString());

    dw1.getSpace(0).setRooms(66);
    System.out.print("\n" + dw.toString());
    System.out.print("\n" + dw1.toString());

    Space offices[] = new Office[2];
    offices[0] = new Office(5, 10);
    offices[1] = new Office(10, 20);
    OfficeFloor of = new OfficeFloor(offices);
    System.out.print("\n" + of.toString());
    Floor of1 = (OfficeFloor) of.clone();
    System.out.print("\n" + of1.toString());
    System.out.print("\n" + of.toString());

    of1.getSpace(0).setRooms(66);
    System.out.print("\n" + of.toString());
    System.out.print("\n" + of1.toString());
}
catch (Exception e){

}

*/



        Semaphore semaphore = new Semaphore();
        SequentalCleaner cleaner = new SequentalCleaner(floors[0], semaphore);
        SequentalRepairer repairer = new SequentalRepairer(floors[0], semaphore);

        new Thread(cleaner).start();
        new Thread(repairer).start();







//        Object secondbuilding1 = new Office(5,3);
//        System.out.print("\n"+secondbuilding1.equals(secondbuilding));

/*
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
*/
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
