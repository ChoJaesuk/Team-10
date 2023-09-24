/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package UserInterface.Users.PortManager;

import Super.Vehicle;
import UserInterface.Users.User;
import Super.Container;
import Super.Ship;
import Super.Truck;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.*;
import java.util.*;

public class PortManager extends User {
    public PortManager(String userName, String password, String portNum) throws IOException {
        super("PortManager", userName, password, portNum);
    }

    private Scanner scanner;

    public PortManager() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        // Using a HashMap to store port information

        // Input for Departure Port
        System.out.println("Enter the Departure: ");
        String departurePortName = scanner.nextLine();

        // Input for Arrival Port
        System.out.println("Enter the Arrival: ");
        String arrivalPortName = scanner.nextLine();

        // Retrieve the corresponding port objects based on the entered port names.
//        Port departurePort = PortList.get(departurePortName);
//        Port arrivalPort = PortList.get(arrivalPortName);

//        if (departurePort != null && arrivalPort != null) {
        // Create an instance of the operation processor
////            operationProcessor processor = new distanceCalculator();
//            double distance = processor.performOperation(departurePort, arrivalPort);
//            System.out.printf("The distance from %s to %s is %.3f km%n", departurePort.getName(), arrivalPort.getName(), distance);
//        } else {
//            System.out.println("The entered port name is not valid.");
//        }
    }

    public LocalDate StringToDate(String string1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(string1, formatter);
        return date;
    }

    public void ListTripsin7Days(String PortID) throws IOException {
        deleteMorethan7days(PortID);
        LocalDate now = LocalDate.now();
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/Data/" + PortID + "/History.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    String DepartureDate = lines[2];
                    if (!(now.isBefore(StringToDate(DepartureDate).minusDays(8)))) {
                        System.out.println(line);
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ListTripsinDays(String PortID, String departureDate, String arrivalDate) throws IOException {
        deleteMorethan7days(PortID);
        LocalDate departure = StringToDate(departureDate);
        LocalDate arrival = StringToDate(arrivalDate);
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/Data/" + PortID + "/History.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    String DepartureDate = lines[2];
                    LocalDate D1 = StringToDate(DepartureDate);
                    String ArrivalDate = lines[3];
                    LocalDate A1 = StringToDate(ArrivalDate);
                    if ((departure.isBefore(D1) || departure.isEqual(D1)) && (arrival.isAfter(A1) || arrival.isEqual(A1))) {
                        System.out.println(line);
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ListTripsinSpecificDays(String PortID, String Date) throws IOException {
        deleteMorethan7days(PortID);
        LocalDate specificDate = StringToDate(Date);
        LocalDate now = LocalDate.now();
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/Data/" + PortID + "/History.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    String DepartureDate = lines[2];
                    LocalDate D1 = StringToDate(DepartureDate);
                    String ArrivalDate = lines[3];
                    LocalDate A1 = StringToDate(ArrivalDate);
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void listAllVehicle(String portNumberID) {
        listAllTruck(portNumberID);
        listAllShip(portNumberID);
    }

    public void listAllTruck(String portNumberID) {
        printAllFilesStartWith("Tr-", portNumberID);
    }

    public void listAllShip(String portNumberID) {
        printAllFilesStartWith("Sh-", portNumberID);
    }

    public Vehicle searchVehicle(String PortNum, String type, String vehicleIDNumber) {
        String searchType = (type.equals("Ship")) ? "Sh-" : "Tr-";
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + PortNum + "/";
        File folder = new File(path);
        File[] fileList = folder.listFiles();

        if (fileList != null) {
            for (File f : fileList) {
                String name = f.getName();
                if (name.equals(searchType + vehicleIDNumber + ".txt")) {
                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                        return (Vehicle) input.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Directory does not exist or is empty: " + path);
        }

        return null; // return null when file does not exist
    }

    public void deleteMorethan7days(String portNum) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + portNum + "/History.txt";
        File tempFile = new File(projectRoot + "/src/Data/" + portNum + "/tmp.txt");
        try {
            FileReader fr = new FileReader(path);
            FileWriter tempFileWriter = new FileWriter(tempFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            BufferedWriter bufferedWriter = new BufferedWriter(tempFileWriter);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split(":");
                if (lines.length >= 4) { // work only while field is enough to work on
                    LocalDate arrivalDate = StringToDate(lines[3]);
                    LocalDate now = LocalDate.now();
                    if (!(arrivalDate.isBefore(now.minusDays(8)))) {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            File originalFile = new File(path);
            if (originalFile.delete()) {
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Failed to rename temporary file");
                }
            } else {
                System.out.println("Failed to delete original file");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } finally {
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }


    public void ListTripsToday(String PortID) throws IOException {
        deleteMorethan7days(PortID);
        LocalDate now = LocalDate.now();
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/Data/" + PortID + "/History.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                if ((line = reader.readLine()) == null) {
                    System.out.println("It is empty");
                }
                while (line != null) {
                    String[] lines = line.split(":");
                    String DepartureDate = lines[2];
                    LocalDate D1 = StringToDate(DepartureDate);
                    String ArrivalDate = lines[3];
                    LocalDate A1 = StringToDate(ArrivalDate);
                    if (D1.isEqual(now) || A1.isEqual(now))
                        System.out.println(line);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    // daily used fuel calculation feature - future work
    public double calculateDailyFuelConsumption(String portID) throws IOException {
        double totalFuelConsumption = 0.0;

        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + portID + "/History.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String tripType = parts[1];
                String departureDateStr = parts[2];
                String arrivalDateStr = parts[3];
                double distance = Double.parseDouble(parts[6]);

                // Parse departure and arrival dates
                LocalDate departureDate = LocalDate.parse(departureDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate arrivalDate = LocalDate.parse(arrivalDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (departureDate.isEqual(arrivalDate)) {
                    // Trip happened within a day, calculate fuel consumption
                    Vehicle vehicle = searchVehicle(portID, tripType.startsWith("Sh") ? "Ship" : "Truck", tripType.substring(3));
                    if (vehicle != null) {
                        // Assuming shipFuelEfficiency and truckFuelEfficiency are methods in the Vehicle class
                        double fuelEfficiency = vehicle instanceof Ship ? ((Ship) vehicle).shipFuelEfficiency() : ((Truck) vehicle).truckFuelEfficiency();
                        totalFuelConsumption += fuelEfficiency * distance;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("Total daily fuel consumption for port %s: %.3f units%n", portID, totalFuelConsumption);
        return totalFuelConsumption;
    }
}
