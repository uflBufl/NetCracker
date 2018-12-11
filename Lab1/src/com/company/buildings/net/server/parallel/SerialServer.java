package com.company.buildings.net.server.parallel;

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
    static class Runner implements Runnable {
        private Socket socet;

        Runner(Socket socket) {
            this.socet = socket;
        }

        @Override
        public void run() {

            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(socet.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter out = null;
            try {
                out = new PrintWriter(socet.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String input;
            Building building = null;
            int i = 0;
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader("fileOne.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                while ((input = bufferedReader.readLine()) != null) {
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (i != 0) {
                try {
                    out.println(getPrice(Buildings.deserialaizeBuilding(in)));
                } catch (BuildingUnderArrestException buildingUnderArrestExseption) {
                    buildingUnderArrestExseption.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                out.flush();
                i--;
            }
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socet.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) throws IOException, BuildingUnderArrestException {
        ServerSocket servers = new ServerSocket(4444);
        Socket socet=servers.accept();
        Thread t = new Thread(new Runner(socet));
        t.start();
        servers.close();
    }
}
