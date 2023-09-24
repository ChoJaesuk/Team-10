/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package Super;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static Super.Calculator.calculateDistance;

public class Trip implements PortLoader {
    private String ID;
    private LocalDate DepartureDate;
    private LocalDate ArrivalDate;
    private String VehicleID;
    private String DeparturePortNum;
    private String ArrivalPortNum;
    private Double Distance;
    private Double fuelConsumption;
    private Vehicle Vehicle;
    private static String Status;
    private static ArrayList<Trip> tripList = new ArrayList<>(); // List to store Trip objects

    public Trip() {
        ID = null;
        DepartureDate = null;
        ArrivalDate = null;
        VehicleID = null;
        DeparturePortNum = null;
        ArrivalPortNum = null;
        Vehicle = null;
        Status = null;
    }

    public Trip(String ID, LocalDate DepartureDate, LocalDate ArrivalDate, String Type, Vehicle Vehicle,
                String DeparturePortNum, String ArrivalPortNum) throws IOException, ClassNotFoundException {
        // Check if there is a Trip with the same ID created before
        if (isTripIDExist(ID)) {
            System.out.println("Trip with the same ID already exists.");
            return;
        }

        LocalDate now = LocalDate.now();
        if (DepartureDate.isBefore(now)) {
            this.Status = "Anchored";
        } else if (DepartureDate.equals(now) && ArrivalDate.isAfter(now)) {
            this.Status = "Delivering";

        } else {
            this.Status = "Delivered";
        }

        double vehicleWeight = Vehicle.CapacityCal(Vehicle);
        this.ID = ID;
        this.DepartureDate = DepartureDate;
        this.ArrivalDate = ArrivalDate;
        this.Vehicle = Vehicle;
        this.VehicleID = Vehicle.getID();
        this.DeparturePortNum = DeparturePortNum;
        this.ArrivalPortNum = ArrivalPortNum;
        this.Distance = calculateDistance(DeparturePortNum, ArrivalPortNum, Vehicle);

        Port target = loadPort();
        try {if (Type.equals("Truck")) {
            double fuelConsumeExpect = Vehicle.FuelCalculation() * Distance;
            if (Vehicle.getContainers() != null) { // Process only if Vehicle.getContainers() is not null
                if (Vehicle.getFuel() > fuelConsumeExpect) {
                    for (Container container : Vehicle.getContainers()) {
                        moveFile(DeparturePortNum, container.getID(), ArrivalPortNum);
                        container.setCurrentPortNum(this.ArrivalPortNum);
                    }
                    Vehicle.FuelConsumption(fuelConsumeExpect);
                    moveTruckToPort(DeparturePortNum, ArrivalPortNum); // The part where you move and delete the Ship
                } else {
                    System.out.println("Vehicle needs to be refueled");
                }
            }
        } else if (Type.equals("Ship")) {
            double fuelConsumeExpect = Vehicle.FuelCalculation() * Distance;
            if (Vehicle.getContainers() != null) { // Process only if Vehicle.getContainers() is not null
                if (Vehicle.getFuel() > fuelConsumeExpect) {
                    for (Container container : Vehicle.getContainers()) {
                        moveFile(DeparturePortNum, container.getID(), ArrivalPortNum);
                        container.setCurrentPortNum(this.ArrivalPortNum);
                    }
                    Vehicle.FuelConsumption(fuelConsumeExpect);
                    moveShipToPort(DeparturePortNum, ArrivalPortNum); // Ship을 이동시키고 삭제하는 부분
                } else {
                    System.out.println("Vehicle needs to be refueled");
                }
            }
        } else {
            System.out.println("Invalid vehicle type.");
        }
            // Your code that throws the exception
        } catch (NoSuchFileException e) {
            System.out.println("File not found: " + e.getFile());
        }


        tripList.add(this); // Add the Trip to the list
    }
    // Method to check if there is a Trip with the same ID
    private boolean isTripIDExist(String tripID) {
        for (Trip trip : tripList) {
            if (trip.getID().equals(tripID)) {
                return true; // Duplicate ID already exists
            }
        }
        return false; // No duplicate ID
    }

    public String getID() {
        return ID;
    }
    // Move the Truck to a new Port
    public void moveTruckToPort(String departurePortNum, String arrivalPortNum) throws IOException {
        if (Vehicle instanceof Truck) {
            Truck truck = (Truck) Vehicle;
            /*truck.setCurrentPortNum(arrivalPortNum);*/
            save(arrivalPortNum);
            save(departurePortNum);
            deleteTruckFromPort(departurePortNum, VehicleID);
            Vehicle.save(arrivalPortNum,VehicleID,Vehicle);
        } else {
            System.out.println("This operation is only applicable to trucks.");
        }
    }
    // Move the Ship to a new Port
    public void moveShipToPort(String departurePortNum, String arrivalPortNum) throws IOException {
        if (Vehicle instanceof Ship) {
            Ship ship = (Ship) Vehicle;
            ship.setCurrentPortNum(arrivalPortNum);
            save(arrivalPortNum);
            save(departurePortNum);
            deleteShipFromPort(departurePortNum, VehicleID);
            Vehicle.setCurrentPortNum(this.ArrivalPortNum);
            Vehicle.save(arrivalPortNum,VehicleID,Vehicle);
        } else {
            System.out.println("This operation is only applicable to ships.");
        }
    }

    // Load a Port
    private Port loadPort() throws IOException, ClassNotFoundException {
        ArrayList<Port> allPorts = (ArrayList<Port>) loadAllPorts();
        Port target = null;
        for (Port port : allPorts) {
            if (port.getPortNum().equals(ArrivalPortNum)) {
                target = port;
                break;
            }
        }
        return target;
    }

    // Save the Trip information

    public void save(String portID) throws IOException {
        String projectRoot = System.getProperty("user.dir");
        try {
            if (this.Vehicle != null) {
                double roundedDistance = Math.round(this.Distance * 100.0) / 100.0; // Round the distance to two decimal places // Round the distance to two decimal places
                this.Distance = roundedDistance;
                PrintWriter output = new PrintWriter(new FileWriter(projectRoot + "/src/Data/" + portID +"/History.txt", true));
                output.println(this.ID + ":" + this.VehicleID + ":" + DepartureDate + ":" + this.ArrivalDate + ":"
                        + this.DeparturePortNum + ":" + this.ArrivalPortNum + ":" + this.Distance + ":" + (this.Vehicle.getContainers() != null ? this.Vehicle.getContainers() : "null") + ":" + Status);
                output.flush();
                output.close();
            } else {
                System.out.println("Vehicle is null. Cannot save trip.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Move a file from one location to another
    private void moveFile(String portID, String fileName, String ArrivalPortName) {
        String projectRoot = System.getProperty("user.dir");
        String departurePath = projectRoot + "/src/Data/" + portID + "/" + fileName + ".txt";
        String arrivalPath = projectRoot + "/src/Data/" + ArrivalPortName + "/" + fileName + ".txt";
        try {
            Path filePath = Paths.get(departurePath);
            Path filePathToMove = Paths.get(arrivalPath);
            Files.move(filePath, filePathToMove, StandardCopyOption.REPLACE_EXISTING); // Add File Overwrite Option
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Delete a Truck from a Port
    private void deleteTruckFromPort(String portID, String truckID) {
        String projectRoot = System.getProperty("user.dir");
        String departurePath = projectRoot + "/src/Data/" + portID + "/" + truckID + ".txt";
        try {
            Path filePath = Paths.get(departurePath);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Delete a Ship from a Port
    private void deleteShipFromPort(String portID, String shipID) {
        String projectRoot = System.getProperty("user.dir");
        String departurePath = projectRoot + "/src/Data/" + portID + "/" + shipID + ".txt";
        try {
            Path filePath = Paths.get(departurePath);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}