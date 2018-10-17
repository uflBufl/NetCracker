package com.company.buildings;

import com.company.interfaces.Floor;
import com.company.interfaces.Space;
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
    //todo здесь size тоже не помешало бы хранить, чтоб не пересчитывать его в методе size()

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
            //todo чтоб не ходить в поисках элемента с начала списка getListElement getPreviousElement, почему бы сразу не получить getListElement(num-1) - это и будет твой previous
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
        //todo чтоб не ходить в поисках элемента с начала списка getListElement getPreviousElement, почему бы сразу не получить getListElement(num-1) - это и будет твой previous
        OfficeListElement deleted = getListElement(num);
        OfficeListElement previous = getPreviousElement(deleted);
        previous.next = deleted.next;

        if(num == 0){
            head = deleted.next;
        }
    }

    public OfficeFloor(int num){
        //todo здесь лучше вообще ничего не делать, только создать head, чтоб не создавать officeList Элементы с data = null
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
                //todo а где переход к следующему элементу?
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
                //todo а где переход к следующему элементу?

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

        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }

        OfficeListElement sup = getListElement(num);
        return sup.data;
    }
    @Override
    public void setSpace(int num, Space office){
        if(num < 0 || num> size()){
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeListElement newOffice = new OfficeListElement(office);
        //todo нафиг здесь то сам узел удалять, а потом добавлять новый? Можно просто изменить data у найденного нода
        deleteOfficeListElement(num);
        addOfficeListElement(num, newOffice);
    }
    @Override
    public void addSpace(int num, Space office){
        if(num < 0 || num> size()+1){
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeListElement newOffice = new OfficeListElement(office);
        addOfficeListElement(num, newOffice);
    }
    @Override
    public void deleteSpace(int num){
        if(num < 0 || num> size()){
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







}
