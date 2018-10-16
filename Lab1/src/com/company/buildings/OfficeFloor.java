package com.company.buildings;

import com.company.Floor;
import com.company.Space;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

class OfficeListElement{
    OfficeListElement next;
    Space data;

    OfficeListElement(){}

    OfficeListElement(Space data){
        this.data = data;
    }
}



public class OfficeFloor implements Floor {
    private OfficeListElement head;

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
            OfficeListElement previous = getPreviousElement(following);
            previous.next = newOffice;
            newOffice.next = following;

            if (num == 0) {
                head = newOffice;
            }
        }
    }

    private void deleteOfficeListElement(int num){
        OfficeListElement deleted = getListElement(num);
        OfficeListElement previous = getPreviousElement(deleted);
        previous.next = deleted.next;

        if(num == 0){
            head = deleted.next;
        }
    }

    public OfficeFloor(int num){
        for(int i = 0;i<num;i++){
            OfficeListElement sup = new OfficeListElement();
            //sup.data = new Office();
            addOfficeListElement(i, sup);
        }
    }

    public OfficeFloor(Space offices[]){
        for(int i = 0;i<offices.length;i++){
            OfficeListElement sup = new OfficeListElement(offices[i]);
            addOfficeListElement(i,sup);
        }
    }

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
    //todo squareTotal()
    public double squareTotal(){
        double square = 0;
        if(head != null) {
            OfficeListElement sup = head;
            do {
                square += head.data.getSquare();
            }
            while (sup.next != head);
        }
        return square;
    }
    //todo roomsCountTotal()
    public int roomsCountTotal(){
        int rooms = 0;
        if(head != null) {
            OfficeListElement sup = head;
            do {
                rooms += head.data.getRooms();
            }
            while (sup.next != head);
        }
        return rooms;
    }

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

    public Space getSpace(int num){

        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }

        OfficeListElement sup = getListElement(num);
        return sup.data;
    }
    //todo setFlat()
    public void setSpace(int num, Space office){
        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeListElement newOffice = new OfficeListElement(office);
        deleteOfficeListElement(num);
        addOfficeListElement(num, newOffice);
    }
    //todo addFlat()
    public void addSpace(int num, Space office){
        if(num < 0 || num> size()+1){
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeListElement newOffice = new OfficeListElement(office);
        addOfficeListElement(num, newOffice);
    }

    public void deleteSpace(int num){
        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }
        deleteOfficeListElement(num);
    }

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







}
