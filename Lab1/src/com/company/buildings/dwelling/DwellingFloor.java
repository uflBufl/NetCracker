package com.company.buildings.dwelling;

import com.company.interfaces.Floor;
import com.company.interfaces.Space;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class DwellingFloor implements Floor, Serializable, Cloneable {
    private Space[] spaces;
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
        return "DwellingFloor (" + size() + ", " + Arrays.toString(getSpaces()) + ')';
    }
//Arrays.deepToString
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



    public Object clone()
    {
        try {
            Floor clon = (Floor) super.clone();
            for (int i = 0; i < size(); i++) {
                clon.setSpace(i, (Space) getSpace(i).clone());
            }
            return clon;
        }
        catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        return null;
    }





//    @Override
//    public Floor clone(){
//
//            try {
//                DwellingFloor dw = (DwellingFloor) super.clone();
//                dw.spaces = spaces.clone();
////                DwellingFloor dw = new DwellingFloor(spaces);
//                for (int i = 0; i < dw.spaces.length; i++) {
//                    Space sp = getSpace(i).clone();
//                //    sp.setRooms(67);
//
//                    dw.spaces[i] = sp;
//                }
//
//
//                return dw;
//
//            }catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//
////                Space[] spaces = getSpaces();
////                Space[] newSpaces = new Space[spaces.length];
////
////                for (int i = 0; i < this.spaces.length; i++)
////                    newSpaces[i] = (Space) spaces[i].clone();
////
////                return new DwellingFloor(newSpaces);
//
//
//    }

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

    public Iterator<Space> iterator()
    {
        return  new DwellingFloorIterator();
    }

    public class DwellingFloorIterator implements Iterator<Space>
    {
        private int position;
        public DwellingFloorIterator(int count){this.position=count;}
        public DwellingFloorIterator(){this.position=0;}
        public boolean hasNext()
        {
            return position<size();
        }

        public Space next()
        {
            position++;
            return getSpace(position);
        }

    }

    public int compareTo(Floor o) {
        int temp=0;
        if(size()>o.size()) temp=1;
        if(size()<o.size()) temp=-1;
        return  temp;
    }


}
