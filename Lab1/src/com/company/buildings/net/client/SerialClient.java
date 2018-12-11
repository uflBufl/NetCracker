package com.company.buildings.net.client;

import com.company.Buildings;
import com.company.buildings.dwelling.DwellingFactory;
import com.company.buildings.dwelling.hotel.HotelFactory;
import com.company.buildings.office.OfficeFactory;
import com.company.interfaces.Building;

import java.io.*;
import java.net.Socket;

public class SerialClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 4444);
        BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        PrintWriter cost=new PrintWriter(new FileWriter("fileThree.txt"));

        BufferedReader inData  = new BufferedReader(new FileReader("fileOne.txt"));
        BufferedReader inType  = new BufferedReader(new FileReader("fileTwo.txt"));
        String fuser,type;
        char temp=' ';
        while ((fuser = inData.readLine())!=null) {
            type=inType.readLine();
            switch (type) {
                case "Dwelling":
                    Buildings.setBuildingFactory(new DwellingFactory());
                    break;
                case "Office":
                    Buildings.setBuildingFactory(new OfficeFactory());
                    break;
                case "Hotel":
                    Buildings.setBuildingFactory(new HotelFactory());
                    break;
            }
            FileWriter fileWriter=new FileWriter("file.txt");
            fileWriter.write(fuser);
            fileWriter.close();
            FileReader fileReader=new FileReader("file.txt");
            Building building= Buildings.readBuilding(fileReader);
            fileReader.close();

            Buildings.serializeBuilding(building, out);
            out.flush();
            //  System.out.println(bulding.toString());
//            out.writeObject(bulding);
            fileReader.close();
            cost.println(in.readLine());
        }
        cost.close();
        inType.close();
        out.close();
        in.close();
        inData.close();
        s.close();
    }
}
