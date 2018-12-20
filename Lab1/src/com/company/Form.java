package com.company;

import com.company.Buildings;
import com.company.buildings.dwelling.DwellingFactory;
import com.company.buildings.office.OfficeFactory;
import com.company.interfaces.Building;
import com.company.interfaces.Floor;
import com.company.interfaces.Space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class Form extends JFrame {

    private JPanel rootPanel;
    private JPanel buildingPanel;
    //private JPanel spacePanel;
    private JPanel buildingSchema;
    private JTextArea buildingArea;
    private JTextArea floorArea;
    private JTextArea spaceArea;
    private Form() {
        initializationComponent();
        ActionListener fileActionListener = event -> {
            Buildings.TypeOfBuilding type;
            switch (event.getActionCommand()) {
                case "Open dwelling":
                    type = Buildings.TypeOfBuilding.DWELLING;
                    Buildings.setBuildingFactory(new DwellingFactory());
                    break;
                case "Open office building":
                    type = Buildings.TypeOfBuilding.OFFICE;
                    Buildings.setBuildingFactory(new OfficeFactory());
                    break;
                default:
                    type = null;
                    break;
            }

            JFileChooser openedFile = new JFileChooser();
//            openedFile.setCurrentDirectory(new File("C:\\Users\\Алексей\\Desktop\\NetCracker\\Java\\LAb\\6"));
            openedFile.setCurrentDirectory(new File("C:\\Users\\gamer\\Desktop\\Учёба\\NetCracker\\NetCracker\\NetCracker\\Lab1"));
            int result = openedFile.showDialog(Form.this, "Выберите файл c зданием");
            if (result == JFileChooser.APPROVE_OPTION) {
                try (Reader fileReader = new FileReader(openedFile.getSelectedFile())) {
                    initFrames(Buildings.readBuilding(fileReader), type);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(Form.this, exception);
                }
            }
        };

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu themeMenu = new JMenu("Look & Feel");

        JMenuItem itm = new JMenuItem("Open dwelling");
        itm.addActionListener(fileActionListener);
        fileMenu.add(itm);

        fileMenu.add(new JSeparator());

        itm = new JMenuItem("Open office building");
        itm.addActionListener(fileActionListener);
        fileMenu.add(itm);

        ButtonGroup group = new ButtonGroup();

        UIManager.LookAndFeelInfo[] LaFs = UIManager.getInstalledLookAndFeels();

        ActionListener lookActionListener = event -> {
            try {
                //UIManager.LookAndFeelInfo[] LaFss = UIManager.getInstalledLookAndFeels();
                for(UIManager.LookAndFeelInfo LaF : LaFs) {
                    if(event.getActionCommand() == LaF.getName())
                        UIManager.setLookAndFeel(LaF.getClassName());
                }
                SwingUtilities.updateComponentTreeUI(Form.this.getContentPane());
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        };

        for (UIManager.LookAndFeelInfo LaF : LaFs) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(LaF.getName());
            item.addActionListener(lookActionListener);
            group.add(item);
            themeMenu.add(item);
        }

        menuBar.add(fileMenu);
        menuBar.add(themeMenu);
        setJMenuBar(menuBar);
        setTitle("NetCracker");
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setVisible(true);
    }

    private void initFrames(Building building, Buildings.TypeOfBuilding buildingType){
        buildingSchema.removeAll();
        System.out.println(building);
        buildingArea.setText(
                "Type:" + buildingType.name() + "\n"
                        + "Floors count:" + building.getNumFloors() + "\n"
                        + "Summary area: " + building.squareTotal() + "\n");
        setInfo(building, 1, 1);
        Floor[] floors = building.getFloors();

        for (int i = 0; i < floors.length; i++) {
            JPanel floorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            floorPanel.setBorder(BorderFactory.createTitledBorder("Floor " + i));
            Space[] spaces = floors[i].getSpaces();
            for (int j = 0; j < spaces.length; j++) {
                JButton button = new JButton(j + "");
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> setInfo(building, finalI, finalJ));
                floorPanel.add(button);
            }
            floorPanel.setPreferredSize(floorPanel.getPreferredSize());
            buildingSchema.add(floorPanel);
        }
        SwingUtilities.updateComponentTreeUI(Form.this.getContentPane());
    }

    private void setInfo(Building building, int i, int j) {
        floorArea.setText("Number: " + i + "\n" +
                "Spaces count: " + building.getFloorByNum(i).GetCountSize() + "\n" +
                "Summary area: " + building.getFloorByNum(i).squareTotal());
        spaceArea.setText("Number " + j + "\n" +
                "Area: " + building.getFloorByNum(i).getSpace(j).getSquare() + "\n" +
                "Rooms Quantity: " + building.getFloorByNum(i).getSpace(j).getRooms());
    }

    private void initializationComponent(){
        buildingSchema = new JPanel();
        buildingSchema.setLayout(new GridLayout(0, 1));
        JScrollPane sBuildingSchema = new JScrollPane(buildingSchema);

        buildingArea = new JTextArea();
        floorArea = new JTextArea();
        spaceArea = new JTextArea();

        buildingPanel = new JPanel();
        buildingPanel.setLayout(new GridLayout(0, 1));
        buildingPanel.add(buildingArea);
        buildingPanel.add(floorArea);
        buildingPanel.add(spaceArea);

        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout());
        rootPanel.add(sBuildingSchema);
        rootPanel.add(buildingPanel);
    }

    public static void main(String[] args) {
        new Form();
    }
}
