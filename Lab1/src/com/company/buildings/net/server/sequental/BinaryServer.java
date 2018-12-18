package com.company.buildings.net.server.sequental;

import com.company.Buildings;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFactory;
import com.company.buildings.dwelling.hotel.Hotel;
import com.company.buildings.dwelling.hotel.HotelFactory;
import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFactory;
import com.company.interfaces.Building;
import com.company.exceptions.BuildingUnderArrestException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public  class BinaryServer {
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
    public static void main(String[] args) throws IOException, BuildingUnderArrestException {
        ServerSocket servers = new ServerSocket(4444);
        Socket socet=servers.accept();

        BufferedReader in  = new BufferedReader(new InputStreamReader(socet.getInputStream()));
        PrintWriter out = new PrintWriter(socet.getOutputStream(),true);
        String         input,output;
        while ((input = in.readLine()) != null) {
            if (input.equalsIgnoreCase("exit")) break;
            char temp=' ';
            switch (input.charAt(0)) {
                case 'D':
                    Buildings.setBuildingFactory(new DwellingFactory());
                    break;
                case 'O':
                    Buildings.setBuildingFactory(new OfficeFactory());
                    break;
                case 'H':
                    Buildings.setBuildingFactory(new HotelFactory());
                    break;
            }
            input=input.replace(input.charAt(0),' ');
            FileWriter fileWriter=new FileWriter("file1.txt");
            fileWriter.write(input);
            fileWriter.close();
            FileReader fileReader=new FileReader("file1.txt");
            Building building=Buildings.readBuilding(fileReader);
            fileReader.close();
            out.println( getPrice(building));
        }
        out.close();
        in.close();
        socet.close();
        servers.close();
    }
}
