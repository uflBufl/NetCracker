package com.company.buildings;

import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

public class Dwelling {
    private DwellingFloor dwellingFloor[];

    public Dwelling(int numFloor, int[] numFlat){
        this.dwellingFloor = new DwellingFloor[numFloor];
        for(int i = 0;i<this.dwellingFloor.length;i++){
            this.dwellingFloor[i] = new DwellingFloor(numFlat[i]);
        }
    }

    public Dwelling(DwellingFloor[] dwellingFloors){
        this.dwellingFloor = dwellingFloors;
    }

    public int getNumFloors(){
        return dwellingFloor.length;
    }

    public int getNumFlats(){
        int numFlats = 0;
        for(int i = 0;i<this.dwellingFloor.length;i++){
            numFlats += this.dwellingFloor[i].getNumFlats();
        }
        return numFlats;
    }

    public double getSquare(){
        double square = 0;
        for(int i =0;i<this.dwellingFloor.length;i++){
            square += this.dwellingFloor[i].getSquareFloor();
        }
        return square;
    }

    public int getNumRooms(){
        int numRooms = 0;
        for(int i =0;i<this.dwellingFloor.length;i++){
            numRooms += this.dwellingFloor[i].getNumRooms();
        }
        return numRooms;
    }

    public DwellingFloor[] getFloors(){
        return dwellingFloor;
    }

    public DwellingFloor getFloorByNum(int numFloor){
        if(numFloor < 0|| numFloor > dwellingFloor.length){
            throw new FloorIndexOutOfBoundsException();
        }
        return dwellingFloor[numFloor];
    }

    public void editFloor(int numFloor, DwellingFloor dwellingFloor){
        if(numFloor < 0|| numFloor > this.dwellingFloor.length){
            throw new FloorIndexOutOfBoundsException();
        }
        this.dwellingFloor[numFloor] = dwellingFloor;
    }

    public Flat getFlatByNum(int numFlat){
        if(numFlat < 0 || numFlat>getNumFlats()){
            throw new SpaceIndexOutOfBoundsException();
        }
        int i = 0;
        int numFlat1 = numFlat;
        int num = numFlat1;
        for(i = 0;i<this.dwellingFloor.length&&num>=0;i++){
            numFlat1 = num;
            num -= this.dwellingFloor[i].getNumFlats();
        }
        i--;
        return this.dwellingFloor[i].getFlat(numFlat1);
    }

    public void editFlatByNum(int numFlat, Flat flat){
        if(numFlat < 0 || numFlat>getNumFlats()){
            throw new SpaceIndexOutOfBoundsException();
        }
        int i = 0;
        int numFlat1 = numFlat-1;
        int num = numFlat1;
        for(i = 0;i<this.dwellingFloor.length&&num>=0;i++){
            numFlat1 = num;
            num -= this.dwellingFloor[i].getNumFlats();
        }
        i--;
        this.dwellingFloor[i].editFlat(numFlat1,flat);
    }

    public void newFlatByNum(int numFlat, Flat flat){
        if(numFlat < 0 || numFlat>getNumFlats()+1){
            throw new SpaceIndexOutOfBoundsException();
        }
        int i = 0;
        int numFlat1 = numFlat-1;
        int num = numFlat1;
        for(i = 0;i<this.dwellingFloor.length&&num>=0;i++){
            numFlat1 = num;
            num -= this.dwellingFloor[i].getNumFlats();
        }
        i--;
        this.dwellingFloor[i].newFlat(numFlat1,flat);
    }

    public void deleteFlatByNum(int numFlat){
        if(numFlat < 0 || numFlat>getNumFlats()){
            throw new SpaceIndexOutOfBoundsException();
        }
        int i = 0;
        int numFlat1 = numFlat-1;
        int num = numFlat1;
        for(i = 0;i<this.dwellingFloor.length&&num>=0;i++){
            numFlat1 = num;
            num -= this.dwellingFloor[i].getNumFlats();
        }
        i--;
        this.dwellingFloor[i].deleteFlat(numFlat1);
    }

    public Flat getBestSpace(){
        double maxSquare = 0;
        Flat flat = new Flat(0);
        for(int i = 0;i<dwellingFloor.length;i++){
            if(dwellingFloor[i].getBestSpace().getSquare()>maxSquare){
                maxSquare = dwellingFloor[i].getBestSpace().getSquare();
                flat = dwellingFloor[i].getBestSpace();
            }
        }
        return flat;
    }
/*
    public int getBestSpaceHelp(){
        double maxSquare = 0;
        int numFloor = 0;
        Flat flat = new Flat(0);
        for(int i = 0;i<dwellingFloor.length;i++){
            if(dwellingFloor[i].getBestSpace().getSquare()>maxSquare){
                maxSquare = dwellingFloor[i].getBestSpace().getSquare();
                flat = dwellingFloor[i].getBestSpace();
                numFloor = i;
            }
        }
        int numFlat = dwellingFloor[numFloor].getBestSpaceHelp();
        int sumFlat = 0;
        for(int i = 0;i<numFloor;i++){
            sumFlat += dwellingFloor[i].getNumFlats();
        }
        sumFlat += numFlat;


        return sumFlat;
    }

    public Flat[] getSortFlat(){

        Dwelling dwelling = new Dwelling(this.dwellingFloor);

        Flat flats[] = new Flat[this.getNumFlats()];

        Arrays.sort();

        for(int i = 0;i<flats.length;i++){

            flats[i] = dwelling.getBestSpace();
            int num = dwelling.getBestSpaceHelp();

            dwelling.deleteFlatByNum(num+1);
        }

        return flats;


    }

*/

    public Flat[] getSortFlats(){
        Flat[] flats = new Flat[getNumFlats()];
        int i = 0;
        for(DwellingFloor floor : dwellingFloor)
            for(Flat flat : floor.getFlats()) {
                flats[i] = flat;
                i++;
            }
        quickSort(flats);
        return flats;
    }

    private static void quickSort(Flat[] flats) {
        int startIndex = 0;
        int endIndex = flats.length - 1;
        doSort(startIndex, endIndex, flats);
    }

    private static void doSort(int start, int end, Flat[] flats) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (flats[i].getSquare() >= flats[cur].getSquare())) {
                i++;
            }
            while (j > cur && (flats[cur].getSquare() >= flats[j].getSquare())) {
                j--;
            }
            if (i < j) {
                Flat temp = flats[i];
                flats[i] = flats[j];
                flats[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur, flats);
        doSort(cur+1, end, flats);
    }


}
