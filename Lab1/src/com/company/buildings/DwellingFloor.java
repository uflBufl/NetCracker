package com.company.buildings;

import com.company.interfaces.Floor;
import com.company.interfaces.Space;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class DwellingFloor implements Floor, Serializable {
    private Space spaces[];
    private int size;

    public DwellingFloor(int num) {
        spaces = new Space[num];
        size = 0;
    }

    public DwellingFloor(Space spaces[]) {
        this.spaces = spaces;
        size = 0;
        for(Space f:spaces){
            if(f != null){
                size++;
            }
        }

    }

    public String toString() {
        return "OfficeFloor (" + size() + ", " + Arrays.toString(getSpaces()) + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwellingFloor that = (DwellingFloor) o;
        return size == that.size &&
                Arrays.equals(spaces, that.spaces);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(spaces);
        return result;
    }

    @Override
    public Object clone(){
        Space[] spaces = getSpaces();
        Space[] newSpaces = new Space[spaces.length];

        for(int i = 0; i < spaces.length; i++)
            newSpaces[i] = (Space) spaces[i].clone();

        return new DwellingFloor(newSpaces);
    }

    @Override
    public int size() {
        //return spaces.length;
        return size;
    }

    @Override
    public double squareTotal() {
        double square = 0;
        for (int i = 0; i < size; i++) {
            square += spaces[i].getSquare();
        }
        return square;
    }

    @Override
    public int roomsCountTotal() {
        int rooms = 0;
        for (int i = 0; i < size; i++) {
            rooms += spaces[i].getRooms();
        }
        return rooms;
    }

    @Override
    public Space[] getSpaces() {
        Space[] sup = spaces;
        return sup;
    }
    @Override
    public Space getSpace(int num) {
        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }
        return spaces[num];
    }

    @Override
    public void setSpace(int num, Space space) {
        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }
        spaces[num] = space;
    }

    @Override
    public void addSpace(int num, Space space) {
        if(num < 0 || num> size()+1){
            throw new SpaceIndexOutOfBoundsException();
        }


        if(spaces.length==size) {
            int newSize = this.size * 2;

            Space newSpaces[] = new Space[newSize];
            for (int i = 0; i < num; i++) {
                newSpaces[i] = this.spaces[i];
            }

            size++;
            newSpaces[num] = space;

            for (int i = (num + 1); i < size; i++) {
                newSpaces[i] = this.spaces[i - 1];
            }

            this.spaces = newSpaces;

        }
        else {
            //сделано так, что бы не путать size и номер элемента
            int i = 0;
            for(i = size-1;i>=num;i--){
                spaces[i+1] = spaces[i];
            }
            size++;
            i++;
            spaces[i] = space;
        }
    }


    @Override
    public void deleteSpace(int num) {
        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }


       //сделано так, что бы не путать size и номер элемента
        size--;
        int i = 0;
        for(i = num;i<size;i++){
            spaces[i] = spaces[i+1];
        }
        spaces[i] = null;


//
//
//        Space newSpaces[] = new Space[size - 1];
//        for (int i = 0; i < num; i++) {
//            newSpaces[i] = this.spaces[i];
//        }
//        size--;
//        for (int i = (num); i < size; i++) {
//            newSpaces[i] = this.spaces[i + 1];
//        }
//
//        this.spaces = newSpaces;
    }
    @Override
    public Space getBestSpace() {
        Space space = new Flat(0);
        for (int i = 0; i < size; i++) {
            if (this.spaces[i].getSquare() > space.getSquare()) {
                space = this.spaces[i];
            }
        }
        return space;
    }

//
//
//    //todo getBestSpaceNumber()
//    public int getBestSpaceNumber(){
//        Space space = new Space(0);
//        int numSpace = 0;
//        for(int i = 0;i<this.spaces.length;i++){
//            if(this.spaces[i].squareTotal()>space.squareTotal()){
//                space = this.spaces[i];
//                numSpace = i;
//            }
//        }
//        return numSpace;
//    }


}
