package com.company.buildings.office;

import com.company.interfaces.Floor;
import com.company.interfaces.Space;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

class OfficeListElement implements Serializable, Cloneable{
    OfficeListElement next;
    Space data;

    OfficeListElement(){}

    OfficeListElement(Space data){
        this.data = data;
    }
}



public class OfficeFloor implements Floor, Serializable, Cloneable {
    private OfficeListElement head;
    int size;


    private OfficeListElement getPreviousElement(OfficeListElement following){
        OfficeListElement sup = head;
        while(sup.next != following){
            sup = sup.next;
        }
        return sup;
    }

    private OfficeListElement getListElement(int num){
        OfficeListElement sup = head;
        for(int i = 0;i<num;i++){
            sup = sup.next;
        }
        return sup;
    }

    private void addOfficeListElement(int num, OfficeListElement addOffice){
        size++;
        OfficeListElement newOffice = addOffice;

//        if(num == 0){
//            OfficeListElement last = getPreviousElement();
//            last.next = addSpace;
//            addSpace.next = head;
//            head = addSpace;
//        }
//        else {
//            OfficeListElement previous = getListElement(num - 1);
//            OfficeListElement following = previous.next;
//            previous.next = addSpace;
//            addSpace.next = following;
//        }

        if(head == null) {
            if (num == 0) {
                head = newOffice;
                newOffice.next = newOffice;
            }
        }
        else{
            OfficeListElement following = getListElement(num);
            //OfficeListElement previous = getPreviousElement(following);
            OfficeListElement previous = getListElement(num-1);
            previous.next = newOffice;
            newOffice.next = following;

            if (num == 0) {
                head = newOffice;
            }
        }
    }

    private void deleteOfficeListElement(int num){
        OfficeListElement deleted = getListElement(num);
        //OfficeListElement previous = getPreviousElement(deleted);
        OfficeListElement previous = getListElement(num-1);
        previous.next = deleted.next;
        size--;

        if(num == 0){
            head = deleted.next;
        }
    }

    public OfficeFloor(int num){
        for(int i = 0;i<num;i++){
            OfficeListElement sup = new OfficeListElement();
            sup.data = new Office();
            addOfficeListElement(i, sup);
        }
    }

    public OfficeFloor(Space offices[]){
        for(int i = 0;i<offices.length;i++){
            OfficeListElement sup = new OfficeListElement(offices[i]);
            addOfficeListElement(i,sup);
        }
    }

    @Override
    public String toString() {
        return "OfficeFloor (" + size() + ", " + Arrays.toString(getSpaces()) + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeFloor that = (OfficeFloor) o;
        return size == that.size &&
                Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {

        return Objects.hash(head, size);
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
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }




//    @Override
//    public Floor clone(){
////        OfficeListElement head = new OfficeListElement(this.head.data.clone());
////        OfficeFloor second = new OfficeFloor();
//
//
//
//
//
////        public Object clone() throws CloneNotSupportedException
////        {
////            Floor clon=(Floor) super.clone();
////            for(int i=0;i<getCountOfficeFloor();i++)
////            {
////                clon.setOfficeFloor(i,(Space)getOfficeFloor(i).clone());
////            }
////            return  clon;
////        }
//
//try{
//        Floor clon=(OfficeFloor) super.clone();
//    setSpace(0,new Office(1,4));
//        System.out.println(head.data);
//    System.out.println(clon.getSpace(0));
//        for(int i=0;i<size;i++)
//        {
//            clon.setSpace(i,getSpace(i).clone());
//        }
//        return  clon;
//    } catch (CloneNotSupportedException e) {
//        e.printStackTrace();
//    }
//    return null;
//
//
//
//
//
//
//
//
//
////        Space[] spaces = getSpaces();
////        Space[] newSpaces = new Space[spaces.length];
////
////        for(int i = 0; i < spaces.length; i++)
////            newSpaces[i] = (Space) spaces[i].clone();
////
////        return new DwellingFloor(newSpaces);
//    }

    @Override
    public int size(){
        int num = 0;
        if(head == null){
            return num;
        }
        else {
            num++;
        }
        OfficeListElement sup = head;
        while(sup.next != head){
            sup = sup.next;
            num++;
        }
        return num;
    }
    @Override
    public double squareTotal(){
        double square = 0;
        if(head != null) {
            OfficeListElement sup = head;
            do {
                square += head.data.getSquare();

                sup = sup.next;
            }
            while (sup.next != head);
        }
        return square;
    }
    @Override
    public int roomsCountTotal(){
        int rooms = 0;
        if(head != null) {
            OfficeListElement sup = head;
            do {
                rooms += head.data.getRooms();

                sup = sup.next;

            }
            while (sup.next != head);
        }
        return rooms;
    }
    @Override
    public Space[] getSpaces(){
        int num = size();
        if(num != 0) {
            OfficeListElement sup = head;
            Space offices[] = new Space[num];
            for (int i = 0; i < offices.length; i++) {
                offices[i] = sup.data;
                sup = sup.next;
            }
            return offices;
        }
        else{
            return new Space[0];
        }
    }
    @Override
    public Space getSpace(int num){

        if(num < 0 || num> size){
            throw new SpaceIndexOutOfBoundsException();
        }

        OfficeListElement sup = getListElement(num);
        return sup.data;
    }
    @Override
    public void setSpace(int num, Space office){
        if(num < 0 || num> size){
            throw new SpaceIndexOutOfBoundsException();
        }
        //OfficeListElement newOffice = new OfficeListElement(office);
        getListElement(num).data = office;
//        deleteOfficeListElement(num);
//        addOfficeListElement(num, newOffice);
    }
    @Override
    public void addSpace(int num, Space office){
        if(num < 0 || num> size+1){
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeListElement newOffice = new OfficeListElement(office);
        addOfficeListElement(num, newOffice);
    }
    @Override
    public void deleteSpace(int num){
        if(num < 0 || num> size){
            throw new SpaceIndexOutOfBoundsException();
        }
        deleteOfficeListElement(num);
    }
    @Override
    public Space getBestSpace(){
        Space office = new Office(0);

        if(head != null) {
            OfficeListElement sup = head;
            do {
                if (sup.data.getSquare() > office.getSquare()) {
                    office = sup.data;
                }
                sup = sup.next;
            } while (sup.next != head);
        }
        else{
            return null;
        }
        return office;
    }

    public Iterator<Space> iterator()
    {
        return  new OfficeFloorIterator();
    }

    private class OfficeFloorIterator implements Iterator<Space>
    {
        private int position;
        public OfficeFloorIterator(int count){this.position=count;}
        public OfficeFloorIterator(){this.position=0;}
        public boolean hasNext()
        {
            return position<size();
        }

        public Space next(){
            position++;
            return getSpace(position);
        }
    }

    @Override
    public int compareTo(Floor o) {
        int temp=0;
        if(size()>o.size()) temp=1;
        if(size()<o.size()) temp=-1;
        return  temp;
    }





}
