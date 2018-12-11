package com.company.buildings.net.server.sequental;

import com.company.Buildings;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.hotel.Hotel;
import com.company.buildings.office.OfficeBuilding;
import com.company.exceptions.BuildingUnderArrestException;
import com.company.interfaces.Building;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SerialServer {
    static Random r = new Random();

    public static double getPrice(Building building) throws BuildingUnderArrestException {
        if (r.nextInt(9) == 0)
            throw new BuildingUnderArrestException();
        double price = building.squareTotal();
        if (building instanceof Hotel) {
            price *= 2000;
        } else if (building instanceof OfficeBuilding) {
            price *= 1500;
        } else if (building instanceof Dwelling) {
            price *= 1000;
        }
        return price;
    }
    public static void main(String[] args) throws IOException, BuildingUnderArrestException, ClassNotFoundException {

        ServerSocket servers = new ServerSocket(4444);
        Socket socet=servers.accept();

        ObjectInputStream in  = new ObjectInputStream(socet.getInputStream());
        PrintWriter out = new PrintWriter(socet.getOutputStream(),true);
        String         input;
        Building building=null;
        int i=0;
        BufferedReader bufferedReader  = new BufferedReader(new FileReader("fileOne.txt"));
        while((input=bufferedReader.readLine())!=null){i++;}
        bufferedReader.close();
        while (i!=0) {
            out.println(getPrice(Buildings.deserialaizeBuilding(in)));
            out.flush();
            i--;
        }
        out.close();
        in.close();
        socet.close();
        servers.close();
    }
}
