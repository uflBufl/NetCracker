package com.company;

import com.company.buildings.Office;
import com.company.buildings.OfficeBuilding;
import com.company.buildings.OfficeFloor;
import com.company.interfaces.Building;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Buildings {

    public static void outputBuilding (Building building, OutputStream out) throws IOException {
        out.write(ByteBuffer.allocate(4).putInt(building.getNumFloors()).array());
        for(Floor floor: building.getFloors()) {
            out.write(ByteBuffer.allocate(4).putInt(floor.size()).array());
            for(Space space : floor.getSpaces()) {
                out.write(ByteBuffer.allocate(4).putInt(space.getRooms()).array());
                out.write(ByteBuffer.allocate(8).putDouble(space.getSquare()).array());
            }
        }
    }

    public static Building inputBuilding (InputStream in) throws IOException {
        byte[] bytes = new byte[4];
        byte[] bytesD = new byte[8];
        in.read(bytes);
        Floor[] floors = new Floor[ByteBuffer.wrap(bytes).getInt()];

        for(int i = 0; i < floors.length; i++){
            in.read(bytes);
            Space[] spaces = new Space[ByteBuffer.wrap(bytes).getInt()];

            for(int j = 0; j < spaces.length; j++) {
                in.read(bytes);
                int amountRooms = ByteBuffer.wrap(bytes).getInt();

                in.read(bytesD);
                double square = ByteBuffer.wrap(bytesD).getDouble();

                spaces[j] = new Office(square,amountRooms);
            }

            floors[i] = new OfficeFloor(spaces);
        }
        return new OfficeBuilding(floors);
    }

    public static void writeBuilding (Building building, Writer out) throws IOException {
        out.write(building.getNumFloors() + " ");

        for(Floor floor: building.getFloors()) {
            out.write(floor.size() + " ");

            for(Space space : floor.getSpaces()) {
                out.write(space.getRooms() + " ");
                out.write(space.getSquare() + " ");
            }
        }
        out.write("\n");
    }


    public static Building readBuilding (Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);

        st.nextToken();
        Floor[] floors = new Floor[(int)st.nval];

        for(int i = 0; i < floors.length; i++){
            st.nextToken();
            Space[] spaces = new Space[(int)st.nval];

            for(int j = 0; j < spaces.length; j++) {
                st.nextToken();
                int amountRooms = (int)st.nval;

                st.nextToken();
                double square = st.nval;

                spaces[j] = new Office(square, amountRooms);
            }

            floors[i] = new OfficeFloor(spaces);
        }
        return new OfficeBuilding(floors);
    }


    public static void serializeBuilding (Building building, OutputStream out) throws IOException {
        (new ObjectOutputStream(out)).writeObject(building);
    }

    public static Building deserialaizeBuilding (InputStream in) throws IOException, ClassNotFoundException {
        return (Building) (new ObjectInputStream(in)).readObject();
    }



    public static void writeBuildingFormat (Building building, Writer out) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("Здание (Кол-во этажей = %d)\n", building.getNumFloors());

        int i = 1;
        int j = 1;
        for(Floor floor: building.getFloors()) {
            writer.printf("\tЭтаж №%d (Кол-во помещений = %d)\n", i, floor.size());

            for(Space space : floor.getSpaces()) {
                writer.printf("\t\tПомещение №%d: Кол-во комнат = %d; Площадь = %f\n",
                        j, space.getRooms(), space.getSquare());
                j++;
            }
            i++;
        }
    }

    public static Building readBuilding(Scanner in){
        in.useDelimiter("[); \n]");

        Floor[] floors = new Floor[0];

        while(in.hasNext())
            if(in.hasNextInt()) {
                floors = new Floor[in.nextInt()];
                break;
            }
            else in.next();

        for(int i = 0; i < floors.length; i++){
            Space[] spaces = new Space[0];

            while(in.hasNext())
                if(in.hasNextInt()) {
                    spaces = new Space[in.nextInt()];
                    break;
                }
                else in.next();

            for(int j = 0; j < spaces.length; j++) {
                int amountRooms = 0;

                while(in.hasNext())
                    if(in.hasNextInt()) {
                        amountRooms = in.nextInt();
                        break;
                    }
                    else in.next();

                double square = 0;

                while(in.hasNext())
                    if(in.hasNextDouble()) {
                        square = in.nextDouble();
                        break;
                    }
                    else in.next();

                spaces[j] = new Office(square, amountRooms);
            }
            floors[i] = new OfficeFloor(spaces);
        }
        return new OfficeBuilding(floors);
    }
}
