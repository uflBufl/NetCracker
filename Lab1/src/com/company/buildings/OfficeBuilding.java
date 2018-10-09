package com.company.buildings;

import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

class FloorListElement{
    FloorListElement next;
    FloorListElement previous;
    OfficeFloor data;

    FloorListElement(){}

    FloorListElement(OfficeFloor data){
        this.data = data;
    }
}



public class OfficeBuilding {
    private FloorListElement head;

    private FloorListElement getListElement(int num){
        FloorListElement sup = head;
        for(int i = 0;i<num;i++){
            sup = sup.next;
        }
        return sup;
    }

    private void addOfficeListElement(int num, FloorListElement addFloor){
        FloorListElement newFloor = addFloor;

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
                head = newFloor;
                newFloor.next = newFloor;
                newFloor.previous = newFloor;
            }
        }
        else{
            FloorListElement following = getListElement(num);
            following.previous.next = newFloor;
            newFloor.previous = following.previous;
            newFloor.next = following;
            following.previous = newFloor;

            if (num == 0) {
                head = newFloor;
            }
        }
    }

    private void deleteFloorListElement(int num){
        FloorListElement deleted = getListElement(num);
        deleted.previous.next = deleted.next;
        deleted.next.previous = deleted.previous;

        if(num == 0){
            head = deleted.next;
        }
    }

    public OfficeBuilding(int numFloor, int[] numOffice){
        for(int i = 0;i<numFloor;i++)
        {
            OfficeFloor data = new OfficeFloor(numOffice[i]);
            FloorListElement sup = new FloorListElement(data);
            addOfficeListElement(i,sup);
        }
    }

    public OfficeBuilding(OfficeFloor[] officeFloors){
        for(int i = 0;i<officeFloors.length;i++)
        {
            FloorListElement sup = new FloorListElement(officeFloors[i]);
            addOfficeListElement(i,sup);
        }
    }

    public int getNumFloors(){
        int num = 0;
        if(head == null){
            return num;
        }
        num++;
        FloorListElement sup = head;
        while(sup.next != head){
            sup = sup.next;
            num++;
        }
        return num;
    }

    public int getNumOffices(){
        int numFloors = getNumFloors();
        int numOffices = 0;
        FloorListElement sup = head;
        for(int i = 0;i<numFloors;i++){
            numOffices += sup.data.getNumOffices();
        }
        return numOffices;
    }

    public double getSquare(){
        int numFloors = getNumFloors();
        double square = 0;
        FloorListElement sup = head;
        for(int i = 0;i<numFloors;i++){
            square += sup.data.getSquareFloor();
        }
        return square;
    }

    public int getNumRooms(){
        int numFloors = getNumFloors();
        int numRooms = 0;
        FloorListElement sup = head;
        for(int i = 0;i<numFloors;i++){
            numRooms += sup.data.getNumRooms();
        }
        return numRooms;
    }

    public OfficeFloor[] getFloors(){
        int numFloors = getNumFloors();
        OfficeFloor floors[] = new OfficeFloor[numFloors];
        FloorListElement sup = head;
        for(int i = 0;i<numFloors;i++){
            floors[i] = sup.data;
            sup = sup.next;
        }
        return floors;
    }

    public OfficeFloor getFloorByNum(int numFloor){
        if(numFloor < 0 || numFloor>getNumFloors()){
            throw new FloorIndexOutOfBoundsException();
        }
        FloorListElement sup = getListElement(numFloor);
        return sup.data;
    }

    public void editFloor(int numFloor, OfficeFloor officeFloor){
        if(numFloor < 0|| numFloor>getNumFloors()){
            throw new FloorIndexOutOfBoundsException();
        }
        FloorListElement sup = getListElement(numFloor);
        sup.data = officeFloor;
    }

    public Office getOfficeByNum(int numOffice){
        if(numOffice < 0 || numOffice>getNumOffices()){
            throw new SpaceIndexOutOfBoundsException();
        }

        int numFloors = getNumFloors();
        FloorListElement sup = head;
        int numOffice1 = numOffice;
        int num = numOffice1;
        for(int i = 0;i<numFloors&&num>=0;i++){
            numOffice1 = num;
            num -= sup.data.getNumOffices();
            sup = sup.next;
        }
        sup = sup.previous;

        return sup.data.getOffice(numOffice1);
    }

    public void editOfficeByNum(int numOffice, Office office){
        if(numOffice < 0 || numOffice>getNumOffices()){
            throw new SpaceIndexOutOfBoundsException();
        }
        int numFloors = getNumFloors();
        FloorListElement sup = head;
        int numOffice1 = numOffice-1;
        int num = numOffice1;
        for(int i = 0;i<numFloors&&num>=0;i++){
            numOffice1 = num;
            num -= sup.data.getNumOffices();
            sup = sup.next;
        }
        sup = sup.previous;

        sup.data.editOffice(numOffice1,office);
    }

    public void newOfficeByNum(int numOffice, Office office){
        if(numOffice < 0 || numOffice>getNumOffices()+1){
            throw new SpaceIndexOutOfBoundsException();
        }
        int numFloors = getNumFloors();
        FloorListElement sup = head;
        int numOffice1 = numOffice-1;
        int num = numOffice1;
        for(int i = 0;i<numFloors&&num>=0;i++){
            numOffice1 = num;
            num -= sup.data.getNumOffices();
            sup = sup.next;
        }
        sup = sup.previous;

        sup.data.newOffice(numOffice1,office);
    }

    public void deleteOfficeByNum(int numOffice){
        if(numOffice < 0 || numOffice>getNumOffices()){
            throw new SpaceIndexOutOfBoundsException();
        }
        int numFloors = getNumFloors();
        FloorListElement sup = head;
        int numOffice1 = numOffice-1;
        int num = numOffice1;
        for(int i = 0;i<numFloors&&num>=0;i++){
            numOffice1 = num;
            num -= sup.data.getNumOffices();
            sup = sup.next;
        }
        sup = sup.previous;

        sup.data.deleteOffice(numOffice1);
    }

    public Office getBestSpace(){
        double maxSquare = 0;
        Office office = new Office(0);
        int numFloor = getNumFloors();
        FloorListElement sup = head;
        for(int i = 0;i<numFloor;i++){
            if(sup.data.getBestSpace().getSquare()>maxSquare){
                maxSquare = sup.data.getBestSpace().getSquare();
                office = sup.data.getBestSpace();
            }
            sup = sup.next;
        }
        return office;
    }


    public Office[] getSortOffices(){
        Office[] offices = new Office[getNumOffices()];
        int num=0;
        int numFloor = getNumFloors();
        FloorListElement sup = head;
        for(int i = 0;i<numFloor;i++){
            Office[] subOffices = sup.data.getOffices();
            for(int j = 0;j<subOffices.length;j++){
                offices[num] = subOffices[j];
                num++;
            }
            sup = sup.next;
        }

        quickSort(offices);
        return offices;
    }

    private static void quickSort(Office[] offices) {
        int startIndex = 0;
        int endIndex = offices.length - 1;
        doSort(startIndex, endIndex, offices);
    }

    private static void doSort(int start, int end, Office[] offices) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (offices[i].getSquare() >= offices[cur].getSquare())) {
                i++;
            }
            while (j > cur && (offices[cur].getSquare() >= offices[j].getSquare())) {
                j--;
            }
            if (i < j) {
                Office temp = offices[i];
                offices[i] = offices[j];
                offices[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur, offices);
        doSort(cur+1, end, offices);
    }








}
