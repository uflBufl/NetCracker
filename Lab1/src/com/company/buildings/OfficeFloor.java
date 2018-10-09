package com.company.buildings;

class OfficeListElement{
    OfficeListElement next;
    Office data;

    OfficeListElement(){}

    OfficeListElement(Office data){
        this.data = data;
    }
}



public class OfficeFloor {
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
//            last.next = newOffice;
//            newOffice.next = head;
//            head = newOffice;
//        }
//        else {
//            OfficeListElement previous = getListElement(num - 1);
//            OfficeListElement following = previous.next;
//            previous.next = newOffice;
//            newOffice.next = following;
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

    public OfficeFloor(Office offices[]){
        for(int i = 0;i<offices.length;i++){
            OfficeListElement sup = new OfficeListElement(offices[i]);
            addOfficeListElement(i,sup);
        }
    }

    public int getNumOffices(){
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

    public double getSquareFloor(){
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

    public int getNumRooms(){
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

    public Office[] getOffices(){
        int num = getNumOffices();
        if(num != 0) {
            OfficeListElement sup = head;
            Office offices[] = new Office[num];
            for (int i = 0; i < offices.length; i++) {
                offices[i] = sup.data;
                sup = sup.next;
            }
            return offices;
        }
        else{
            return new Office[0];
        }
    }

    public Office getOffice(int num){
        OfficeListElement sup = getListElement(num);
        return sup.data;
    }

    public void editOffice(int num, Office office){
        OfficeListElement newOffice = new OfficeListElement(office);
        deleteOfficeListElement(num);
        addOfficeListElement(num, newOffice);
    }

    public void newOffice(int num, Office office){
        OfficeListElement newOffice = new OfficeListElement(office);
        addOfficeListElement(num, newOffice);
    }

    public void deleteOffice(int num){
        deleteOfficeListElement(num);
    }

    public Office getBestSpace(){
        Office office = new Office(0);

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
