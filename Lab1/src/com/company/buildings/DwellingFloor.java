package com.company.buildings;

import com.company.interfaces.Floor;
import com.company.interfaces.Space;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

public class DwellingFloor implements Floor {
    private Space spaces[];
    private int size;

    public DwellingFloor(int num) {
        spaces = new Space[num];
        size = 0;
//        for(int i = 0;i<num;i++){
//            spaces[i] = new Flat();
//        }
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
        Space newSpaces[] = new Space[size + 1];
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


    @Override
    public void deleteSpace(int num) {
        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }
        Space newSpaces[] = new Space[size - 1];
        for (int i = 0; i < num; i++) {
            newSpaces[i] = this.spaces[i];
        }
        size--;
        for (int i = (num); i < size; i++) {
            newSpaces[i] = this.spaces[i + 1];
        }

        this.spaces = newSpaces;
    }
    @Override
    public Space getBestSpace() {
        Space space = new Flat(0);
        for (int i = 0; i < this.spaces.length; i++) {
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
