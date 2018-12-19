package com.company.buildings.office;

import com.company.interfaces.Building;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;
import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

class FloorAndNumberOffice{
    FloorListElement floor;
    int numOffice;

    public FloorAndNumberOffice(FloorListElement floor, int numOffice){
        this.floor = floor;
        this.numOffice = numOffice;
    }
}

class FloorListElement implements Serializable{
    FloorListElement next;
    FloorListElement previous;
    Floor data;

    FloorListElement(){}

    FloorListElement(Floor data){
        this.data = data;
    }
}



public class OfficeBuilding implements Building, Serializable {
    private FloorListElement head;
    int size;

    private FloorListElement getListElement(int num){
        FloorListElement sup = head;
        for(int i = 0;i<num;i++){
            sup = sup.next;
        }
        return sup;
    }

    private void addOfficeListElement(int num, FloorListElement addFloor){
        size++;
        FloorListElement newFloor = addFloor;

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
        size--;

        if(num == 0){
            head = deleted.next;
        }
    }

    public OfficeBuilding(int numFloor, int... numOffice){
        for(int i = 0;i<numFloor;i++)
        {
            Floor data = new OfficeFloor(numOffice[i]);
            FloorListElement sup = new FloorListElement(data);
            addOfficeListElement(i,sup);
        }
    }

    public OfficeBuilding(Floor... officeFloors){
        for(int i = 0;i<officeFloors.length;i++)
        {
            FloorListElement sup = new FloorListElement(officeFloors[i]);
            addOfficeListElement(i,sup);
        }
    }

    @Override
    public String toString()
    {
        //todo StringBbuilder
        Floor[] temp = getFloors(); //todo не надо массивы юзать
        StringBuffer  stringBuffer=new StringBuffer();
        stringBuffer.append("OfficeBuilding("+size()+",");
        //todo итератор
        for(int i=0;i<size;i++){ stringBuffer.append(temp[i].toString());if(i!=size-1) stringBuffer.append(",");}
        stringBuffer.append(")");
        return  stringBuffer.toString();
    }

    @Override
    public boolean equals(Object object)
    {
        boolean bool=true;
        if(object.getClass()!=OfficeBuilding.class)bool=false;
        else
        {
            //todo нафиг тебе массивы getFloorByNum() не??
            OfficeBuilding newOfficeBulding=(OfficeBuilding) object;
            Floor[] floors1 = getFloors();
            Floor[] floors2 =newOfficeBulding.getFloors();
            if(newOfficeBulding.getFloors()!=getFloors())bool=false;
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
        //todo ноды тоже надо клонировать, а то твой клон будет использувать те же ноды, что и исходный объект, а не новые ноды
        Building clon=(Building) super.clone();
        for(int i=0;i<size();i++)
        {
            clon.setFloor(i,(Floor)getFloorByNum(i).clone());
        }
        return  clon;
    }


    @Override
    public int getNumFloors(){
        //todo что за дичь? у тебя число элементов лежит в  size, нахер ты его пересчитываешь, если при добавлении и удалении элементов ты его изменяешь?
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
    @Override
    public int size(){
        //todo вот логичнее было чтоб size() возвращал число этажей, а отдельный метод - общее число квартир
        int numFloors = size;
        int numOffices = 0;
        FloorListElement sup = head;
        for(int i = 0;i<numFloors;i++){
            numOffices += sup.data.size();
        }
        return numOffices;
    }
    @Override
    public double squareTotal(){
        int numFloors = size;
        double square = 0;
        FloorListElement sup = head;
        //todo итератор
        for(int i = 0;i<numFloors;i++){
            square += sup.data.squareTotal();
        }
        return square;
    }
    @Override
    public int roomsCountTotal(){
        int numFloors = size;
        int numRooms = 0;
        //todo итератор
        FloorListElement sup = head;
        for(int i = 0;i<numFloors;i++){
            numRooms += sup.data.roomsCountTotal();
        }
        return numRooms;
    }
    @Override
    public Floor[] getFloors(){
        int numFloors = size;
        Floor floors[] = new OfficeFloor[numFloors];
        FloorListElement sup = head;
        for(int i = 0;i<numFloors;i++){
            floors[i] = sup.data;
            sup = sup.next;
        }
        return floors;
    }
    @Override
    public Floor getFloorByNum(int numFloor){
        if(numFloor < 0 || numFloor>size){ //todo check в отдельный метод
            throw new FloorIndexOutOfBoundsException();
        }
        FloorListElement sup = getListElement(numFloor);
        return sup.data;
    }
    @Override
    public void setFloor(int numFloor, Floor officeFloor){
        if(numFloor < 0|| numFloor>size){//todo check в отдельный метод
            throw new FloorIndexOutOfBoundsException();
        }
        FloorListElement sup = getListElement(numFloor);
        sup.data = officeFloor;
    }

    private FloorAndNumberOffice getNumberFloorAndNumberOffice(int numOffice){
        int numFloors = size;
        FloorListElement sup = head;
        int numOffice1 = numOffice-1;
        int num = numOffice1;
        for(int i = 0;i<numFloors&&num>=0;i++){
            numOffice1 = num;
            num -= sup.data.size();
            sup = sup.next;
        }
        sup = sup.previous;

        FloorAndNumberOffice dto = new FloorAndNumberOffice(sup,numOffice1);

        return dto;
    }





    @Override
    public Space getSpaceByNum(int numOffice){
        if(numOffice <= 0 || numOffice> size()){//todo check в отдельный метод
            throw new SpaceIndexOutOfBoundsException();
        }

        FloorAndNumberOffice dto = getNumberFloorAndNumberOffice(numOffice);
        FloorListElement sup = dto.floor;
        int numOffice1 = dto.numOffice;

        return sup.data.getSpace(numOffice1);
    }

    @Override
    public void setSpaceByNum(int numOffice, Space office){
        if(numOffice <= 0 || numOffice> size()){//todo check в отдельный метод
            throw new SpaceIndexOutOfBoundsException();
        }

        FloorAndNumberOffice dto = getNumberFloorAndNumberOffice(numOffice);
        FloorListElement sup = dto.floor;
        int numOffice1 = dto.numOffice;

        sup.data.setSpace(numOffice1,office);
    }

    @Override
    public void addSpaceByNum(int numOffice, Space office){
        if(numOffice <= 0 || numOffice> size()+1){
            throw new SpaceIndexOutOfBoundsException();
        }

        FloorAndNumberOffice dto = getNumberFloorAndNumberOffice(numOffice);
        FloorListElement sup = dto.floor;
        int numOffice1 = dto.numOffice;

        sup.data.addSpace(numOffice1,office);
    }

    @Override
    public void deleteSpaceByNum(int numOffice){
        if(numOffice <= 0 || numOffice> size()){//todo check в отдельный метод
            throw new SpaceIndexOutOfBoundsException();
        }

        FloorAndNumberOffice dto = getNumberFloorAndNumberOffice(numOffice);
        FloorListElement sup = dto.floor;
        int numOffice1 = dto.numOffice;

        sup.data.deleteSpace(numOffice1);
    }
    @Override
    public Space getBestSpace(){
        double maxSquare = 0;
        Space office = new Office(0);
        int numFloor = size;
        FloorListElement sup = head;
        //todo итератор
        for(int i = 0;i<numFloor;i++){
            Space o = sup.data.getBestSpace();
            if(o.getSquare()>maxSquare){
                maxSquare = o.getSquare();
                office = o;
            }
            sup = sup.next;
        }
        return office;
    }

    @Override
    public Space[] getSortSpaces(){
        Space[] offices = new Space[size()];
        int num=0;
        int numFloor = size;
        FloorListElement sup = head;
        for(int i = 0;i<numFloor;i++){
            Space[] subOffices = sup.data.getSpaces();
            for(int j = 0;j<subOffices.length;j++){
                offices[num] = subOffices[j];
                num++;
            }
            sup = sup.next;
        }

        quickSort(offices);
        return offices;
    }

    private static void quickSort(Space[] offices) {
        int startIndex = 0;
        int endIndex = offices.length - 1;
        doSort(startIndex, endIndex, offices);
    }

    private static void doSort(int start, int end, Space[] offices) {
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
                Space temp = offices[i];
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


    public Iterator iterator()
    {
        return  new OfficeBuldingIterator();
    }

    public class OfficeBuldingIterator implements Iterator<Floor>
    {
        /*todo жесть! не надо копипастить реализацию итератора из здания.
НАДО НАПИСАТЬ НОРМАЛЬНЫЙ ИТЕРАТОР, который ходит по нодам и хранит ссылку на текущий нод
 */
        private int position;
        public OfficeBuldingIterator(int count){this.position=count;}
        public OfficeBuldingIterator(){this.position=0;}
        public boolean hasNext()
        {
            return position<size();//todo и вот тут ты сам попал в свою же ловушку. У тебя здесь метод size() возвращает число квартир, а не этажей...

        }

        public Floor next()
        {
            position++;
            return getFloorByNum(position);
        }
    }





}
