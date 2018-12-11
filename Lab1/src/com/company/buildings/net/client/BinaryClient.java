package com.company.buildings.net.client;

import com.company.Buildings;
import com.company.interfaces.Building;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class BinaryClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 4444);
        BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter    out = new PrintWriter(s.getOutputStream(),true);
        PrintWriter cost=new PrintWriter(new FileWriter("fileThree.txt"));

        BufferedReader inData  = new BufferedReader(new FileReader("fileOne.txt"));
        BufferedReader inType  = new BufferedReader(new FileReader("fileTwo.txt"));
        String fuser,type;
        char temp=' ';
        while ((fuser = inData.readLine())!=null) {
            type=inType.readLine();
            switch (type) {
                case "Dwelling":
                    temp='D';
                    break;
                case "Office":
                    temp='O';
                    break;
                case "Hotel":
                    temp='H';
                    break;
            }
            out.println(temp+fuser);
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
