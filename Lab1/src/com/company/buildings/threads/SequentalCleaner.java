package com.company.buildings.threads;

import com.company.interfaces.Floor;

public class SequentalCleaner  extends Thread implements Runnable {
    private Floor floor;
    private  Semaphore semaphore;
    public SequentalCleaner(Floor floor,Semaphore semaphore)
    {
        this.floor=floor;this.semaphore=semaphore;
    }
    @Override
    public void run() {
        for(int i = 1; i <= floor.size(); i++) {
            try {
                semaphore.beginClean();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Cleaning room number " + i +
                    " with total area " + floor.getSpace(i).getSquare() +
                    " square meters");

            semaphore.endClean();
        }


    }
}
