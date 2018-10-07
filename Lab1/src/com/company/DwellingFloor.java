package com.company;

public class DwellingFloor {
    private Flat flats[];

    public DwellingFloor(int num){
        flats = new Flat[num];
    }

    public DwellingFloor(Flat flats[]){
        this.flats = flats;
    }

    public int getNumFlats(){
        return flats.length;
    }

    public double getSquareFloor(){
        double square = 0;
        for(int i =0;i<flats.length;i++){
            if(flats[i] !=null){
                square += flats[i].getSquare();
            }
        }
        return square;
    }

    public int getNumRooms(){
        int rooms = 0;
        for(int i =0;i<flats.length;i++){
            if(flats[i] !=null){
                rooms += flats[i].getRooms();
            }
        }
        return rooms;
    }

    public Flat[] getFlats(){
        return flats;
    }

    public Flat getFlat(int num){
        return flats[num];
    }

    public void editFlat(int num, Flat flat){
        flats[num] = flat;
    }

    public void newFlat(int num, Flat flat){
        Flat newFlats[] = new Flat[this.flats.length+1];
        for(int i = 0;i<num;i++){
            if(this.flats[i]!=null) {
                newFlats[i] = this.flats[i];
            }
        }

        newFlats[num] = flat;

        for(int i = (num+1);i<newFlats.length;i++){
            if(this.flats[i-1]!=null) {
                newFlats[i] = this.flats[i - 1];
            }
        }

        this.flats = newFlats;
    }

    public void deleteFlat(int num){
        Flat newFlats[] = new Flat[this.flats.length-1];
        for(int i = 0;i<num;i++){
            if(this.flats[i]!=null) {
                newFlats[i] = this.flats[i];
            }
        }
        for(int i = (num);i<newFlats.length;i++){
            if(this.flats[i+1]!=null) {
                newFlats[i] = this.flats[i + 1];
            }
        }

        this.flats = newFlats;
    }

    public Flat getBestSpace(){
        Flat flat = new Flat(0);
        for(int i = 0;i<this.flats.length;i++){
            if(this.flats[i].getSquare()>flat.getSquare()){
                flat = this.flats[i];
            }
        }
        return flat;
    }

    public int getBestSpaceHelp(){
        Flat flat = new Flat(0);
        int numFlat = 0;
        for(int i = 0;i<this.flats.length;i++){
            if(this.flats[i].getSquare()>flat.getSquare()){
                flat = this.flats[i];
                numFlat = i;
            }
        }
        return numFlat;
    }







}
