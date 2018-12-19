package com.company.buildings;

import com.company.interfaces.Floor;
import com.company.interfaces.Space;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {
    public Floor flat; //todo Floor flat?!?!?!?! flat КАРЛ!!!!!! не floor, а flat?!?!?!
    public int countFlat; //todo нафиг это поле
    public SynchronizedFloor(Floor b) {
        this.countFlat = b.GetCountSize();
        this.flat = b;
    }

    @Override
    public synchronized int GetCountSize() {
        return countFlat; //todo flat.GetCountSize() по-идее так
    }

    @Override
    public synchronized int size() {
        return flat.size();
    }

    @Override
    public synchronized double squareTotal(){
        return flat.squareTotal();
    }

    @Override
    public synchronized int roomsCountTotal(){
        return flat.roomsCountTotal();
    }
    @Override
    public synchronized Space[] getSpaces(){

        return flat.getSpaces();
    }
    @Override
    public synchronized Space getSpace(int num){
        return flat.getSpace(num);
    }
    @Override
    public synchronized void setSpace(int num, Space space){
        flat.setSpace(num,space);
    }
    @Override
    public synchronized void addSpace(int num, Space space){
        flat.addSpace(num,space);
    }
    @Override
    public synchronized void deleteSpace(int num){
        flat.deleteSpace(num);
    }
    @Override
    public synchronized Space getBestSpace(){
        return flat.getBestSpace();
    }

    public synchronized boolean equals(Object object)
    {

        return flat.equals(object);
    }

    public synchronized String toString()
    {

        return  flat.toString();
    }

    public synchronized  Object clone() throws CloneNotSupportedException
    {
        return  flat.clone();
    }

    public synchronized int hashCode()
    {

        return flat.hashCode();
    }

    @Override
    public int compareTo(Floor o) {
        return 0; //todo где реализация
    }

    @Override
    public Iterator<Space> iterator() {
        return null; //todo ШТА? floor.iterator() не?!?!

    }

}
