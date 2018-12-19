package com.company.buildings.threads;

import com.company.interfaces.Floor;


public class SequentalRepairer extends Thread implements Runnable {
    private Floor floor;
    private Semaphore semaphore;
    public SequentalRepairer(Floor floor,Semaphore semaphore)
    {
        this.floor=floor;
        this.semaphore=semaphore;
    }
    @Override
    public void run() {
        for(int i = 1; i <= floor.size(); i++) {
            try {
                semaphore.beginRepair();
            } catch (InterruptedException e) {
                e.printStackTrace(); //todo ЫЫЫЫ сегодня в лекции расскажу как обрабатывать исключения =))))
            }

            System.out.println("Repairing space number " + i +
                    " with total area " + floor.getSpace(i).getSquare() +
                    " square meters");

            semaphore.endRepair();
        }
    }
}
