package com.company.buildings.dwelling;

import com.company.interfaces.Building;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;
import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

//DTO
class NumberFloorAndNumberFlat{
    int numFloor;
    int numFlat;

    public NumberFloorAndNumberFlat(int numFloor, int numFlat){
        this.numFloor = numFloor;
        this.numFlat = numFlat;
    }
}

public class Dwelling implements Building, Serializable {
    private Floor dwellingFloor[];
    private int size;

    public Dwelling(int numFloor, int... numFlat) {
        this.dwellingFloor = new Floor[numFloor];
        for (int i = 0; i < this.dwellingFloor.length; i++) {
            this.dwellingFloor[i] = new DwellingFloor(numFlat[i]);
        }
        size = 0;
    }

    public Dwelling(Floor... dwellingFloors) {
        this.dwellingFloor = dwellingFloors;
        size = 0;
        for(Floor dw:dwellingFloors){
            if(dw != null){
                size++;
            }
        }
    }

    @Override
    public String toString()
    {
        //todo StringBbuilder
        Floor[] temp = getFloors();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("Dwelling("+size()+",");
        //todo итератор
        for(int i=0;i<size;i++){ stringBuffer.append(temp[i].toString());if(i!=size-1) stringBuffer.append(",");}
        stringBuffer.append(")");
        return  stringBuffer.toString();
    }

    @Override
    public boolean equals(Object object)
    {
        boolean bool=true;
        if(object.getClass()!=Dwelling.class)bool=false;
        else
        {
            //todo нафиг тебе массивы getFloorByNum() не??
            Dwelling newDwelling=(Dwelling) object;
            Floor[] floors1 = getFloors();
            Floor[] floors2 =newDwelling.getFloors();
            if(newDwelling.getFloors()!=getFloors())bool=false;
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

    @Override
    public int hashCode()
    {
        int temp=size();
        //todo итератор
        for(int i=0;i<size();i++)
        {
            temp^=getFloorByNum(i).hashCode();
        }
        return temp;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Building clon=(Building) super.clone();
        for(int i=0;i<size();i++)
        {
            clon.setFloor(i,(Floor)getFloorByNum(i).clone());
        }
        return  clon;
    }

    @Override
    public int getNumFloors() {
        //return dwellingFloor.length;
        return size;
    }
    @Override
    public int size() {
        //todo вот логичнее было чтоб size() возвращал число этажей, а отдельный метод - общее число квартир
        int numFlats = 0;
        //todo итератор
        for (int i = 0; i < size; i++) {
            numFlats += this.dwellingFloor[i].size();
        }
        return numFlats;
    }
    @Override
    public double squareTotal() {
        double square = 0;
        //todo итератор
        for (int i = 0; i < size; i++) {
            square += this.dwellingFloor[i].squareTotal();
        }
        return square;
    }
    @Override
    public int roomsCountTotal() {
        int numRooms = 0;
        //todo итератор
        for (int i = 0; i < size; i++) {
            numRooms += this.dwellingFloor[i].roomsCountTotal();
        }
        return numRooms;
    }
    @Override
    public Floor[] getFloors() {
        Floor[] dw = dwellingFloor;
        return dw;
    }
    @Override
    public Floor getFloorByNum(int numFloor) {
        if (numFloor < 0 || numFloor > size) { //todo проверку в отдельный метод
            throw new FloorIndexOutOfBoundsException();
        }
        return dwellingFloor[numFloor];
    }
    @Override
    public void setFloor(int numFloor, Floor dwellingFloor) {
        if (numFloor < 0 || numFloor > size) {//todo проверку в отдельный метод
            throw new FloorIndexOutOfBoundsException();
        }
        this.dwellingFloor[numFloor] = dwellingFloor;
    }

    private NumberFloorAndNumberFlat getNummberFloorAndNumberFlat(int numFlat){


        int i = 0;
        int numFlat1 = numFlat-1;
        int num = numFlat1;
        for (i = 0; i < size && num >= 0; i++) {
            numFlat1 = num;
            num -= this.dwellingFloor[i].size();
        }
        i--;

        NumberFloorAndNumberFlat dto = new NumberFloorAndNumberFlat(i,numFlat1);
        return dto;
    }


    @Override
    public Space getSpaceByNum(int numFlat) {
        if (numFlat <= 0 || numFlat > size()) {//todo проверку в отдельный метод
            throw new SpaceIndexOutOfBoundsException();
        }

        NumberFloorAndNumberFlat dto = getNummberFloorAndNumberFlat(numFlat);
        int i = dto.numFloor;
        int numFlat1 = dto.numFlat;

        return this.dwellingFloor[i].getSpace(numFlat1);
    }

    @Override
    public void setSpaceByNum(int numFlat, Space flat) {
        if (numFlat <= 0 || numFlat > size()) {//todo проверку в отдельный метод
            throw new SpaceIndexOutOfBoundsException();
        }

        NumberFloorAndNumberFlat dto = getNummberFloorAndNumberFlat(numFlat);
        int i = dto.numFloor;
        int numFlat1 = dto.numFlat;

        this.dwellingFloor[i].setSpace(numFlat1, flat);
    }

    @Override
    public void addSpaceByNum(int numFlat, Space flat) {
        if (numFlat <= 0 || numFlat > size() + 1) {
            throw new SpaceIndexOutOfBoundsException();
        }

        NumberFloorAndNumberFlat dto = getNummberFloorAndNumberFlat(numFlat);
        int i = dto.numFloor;
        int numFlat1 = dto.numFlat;

        this.dwellingFloor[i].addSpace(numFlat1, flat);
    }

    @Override
    public void deleteSpaceByNum(int numFlat) {
        if (numFlat <= 0 || numFlat > size()) {//todo проверку в отдельный метод
            throw new SpaceIndexOutOfBoundsException();
        }

        NumberFloorAndNumberFlat dto = getNummberFloorAndNumberFlat(numFlat);
        int i = dto.numFloor;
        int numFlat1 = dto.numFlat;

        this.dwellingFloor[i].deleteSpace(numFlat1);
    }
    @Override
    public Space getBestSpace() {
        double maxSquare = 0;
        Space flat = new Flat(0);
        for (int i = 0; i < size; i++) { //todo итератор
            Space f = dwellingFloor[i].getBestSpace();
            if (f.getSquare() > maxSquare) {
                maxSquare = f.getSquare();
                flat = f;
            }
        }
        return flat;
    }
/*
    public int getBestSpaceNumber(){
        double maxSquare = 0;
        int numFloor = 0;
        Flat flat = new Flat(0);
        for(int i = 0;i<dwellingFloor.length;i++){
            if(dwellingFloor[i].getBestSpace().squareTotal()>maxSquare){
                maxSquare = dwellingFloor[i].getBestSpace().squareTotal();
                flat = dwellingFloor[i].getBestSpace();
                numFloor = i;
            }
        }
        int numFlat = dwellingFloor[numFloor].getBestSpaceNumber();
        int sumFlat = 0;
        for(int i = 0;i<numFloor;i++){
            sumFlat += dwellingFloor[i].size();
        }
        sumFlat += numFlat;


        return sumFlat;
    }

    public Flat[] getSortFlat(){

        Dwelling dwelling = new Dwelling(this.dwellingFloor);

        Flat flats[] = new Flat[this.size()];

        Arrays.sort();

        for(int i = 0;i<flats.length;i++){

            flats[i] = dwelling.getBestSpace();
            int num = dwelling.getBestSpaceNumber();

            dwelling.deleteSpaceByNum(num+1);
        }

        return flats;


    }

*/
@Override
    public Space[] getSortSpaces() {
        Space[] flats = new Space[size()];
        int i = 0;
        for (Floor floor : dwellingFloor)
            for (Space flat : floor.getSpaces()) {
                flats[i] = flat;
                i++;
            }
        quickSort(flats);
        return flats;
    }

    private static void quickSort(Space[] flats) {
        int startIndex = 0;
        int endIndex = flats.length - 1;
        doSort(startIndex, endIndex, flats);
    }

    private static void doSort(int start, int end, Space[] flats) {
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
                Space temp = flats[i];
                flats[i] = flats[j];
                flats[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur, flats);
        doSort(cur + 1, end, flats);
    }


    public Iterator<Floor> iterator()
    {
        return  new DwellingIterator();
    }

    public class DwellingIterator implements Iterator<Floor>
    {
        private int position;
        public DwellingIterator(int count){this.position=count;}
        public DwellingIterator(){this.position=0;}
        public boolean hasNext()
        {
            return position<size(); //todo и вот тут ты сам попал в свою же ловушку. У тебя здесь метод size() возвращает число квартир, а не этажей...
        }

        public Floor next()
        {
            position++;
            return getFloorByNum(position);
        }
    }


}
