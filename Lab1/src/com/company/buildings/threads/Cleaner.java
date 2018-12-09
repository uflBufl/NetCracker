package com.company.buildings.threads;

import com.company.interfaces.Floor;

public class Cleaner extends Thread {
    Floor floor;
    public Cleaner(Floor floor)
    {
        this.floor=floor;
    }
    public  void run()
    {
        int j;
        for(int i=0;i<floor.size();i++)
        {
            j=i+1;
            System.out.println("Cleaning space number "+j+" with total area " +floor.getSpace(i).getSquare()+" square meters");
        }

    }
}
