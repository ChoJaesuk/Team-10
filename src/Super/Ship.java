/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package Super;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Ship extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ID; // Unique ship identifier (combination of "Sh" and numbers)
    private Double fuel = 360000.0; // Initial fuel capacity
    private Double capacity; // Capacity of the ship
    private ArrayList<Container> containers = new ArrayList<>(); // List of containers on the ship
    private Double kmPerFuel; // Fuel consumption per kilometer
    private String currentPortNum = null; // The current port number where the ship is located

    // Getter for fuel consumption per kilometer
    public Double getKmPerFuel() {
        return kmPerFuel;
    }

    // Default constructor initializing attributes to null or default values
    public Ship() {
        this.ID = null;
        this.fuel = 360000.0; // Typical fuel capacity
        this.capacity = null;
        this.containers = new ArrayList<>();
        this.kmPerFuel = null;
    }

    // Getter for Ship ID
    @Override
    String getID() {
        return this.ID;
    }

    // Constructor for creating a Ship object with specified attributes
    public Ship(String ID, Double capacity, String currentPortNum) throws IOException {
        this.ID = ID;
        this.capacity = capacity;
        this.currentPortNum = currentPortNum;
        this.containers = new ArrayList<>();
        save(currentPortNum, ID, this);
    }

    // Method to load a container onto the ship
    @Override
    public void loadContainer(Container container) throws IOException {
        CapacityCal(this);
        this.containers.add(container);
        this.kmPerFuel = FuelCalculation();
        try {
            save(currentPortNum, this.ID, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Container> containerType = new ArrayList<>();

    // Ship fuel efficiency to calculate fuel consumption
    public static Double shipFuelEfficiency() {
        Double total = 0.0;
        for (Container container : containerType) {
            String containerType = container.getType();
            if (containerType.equals("dry storage")) {
                total += 4.6;
            } else if (containerType.equals("open top")) {
                total += 3.2;
            } else if (containerType.equals("open side")) {
                total += 3.2;
            } else if (containerType.equals("refrigerated")) {
                total += 5.4;
            } else if (containerType.equals("liquid")) {
                total += 5.3;
            }
        }
        return total;
    }



    // Method to unload a container from the ship
    @Override
    public void unloadContainer(Container container) throws IOException {
        for (Container c : containers) {
            if (c.getID().equals(container.getID())) {
                containers.remove(c);
                this.kmPerFuel = FuelCalculation();
                try {
                    save(currentPortNum, this.ID, this);
                } catch (Exception e) {
                    System.out.println("There is no matched container");
                }
                return; // Exit the loop after removing the container
            }
        }
        System.out.println("There is no matched container");
    }

    // Getter for fuel capacity
    @Override
    public Double getFuel() {
        return fuel;
    }

    // Setter for current port number
    @Override
    void setCurrentPortNum(String currentPortNum) throws IOException {
        this.currentPortNum = currentPortNum;

    }

    // Method to update fuel consumption and save the ship object
    @Override
    public Double FuelConsumption(Double consumption) throws IOException {
        this.fuel -= consumption;
        return fuel;
    }

    // Method to refuel the ship
    @Override
    public void Refuel() {
        this.fuel = 360000.0; // Reset fuel capacity
        try {
            save(currentPortNum, this.ID, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to calculate the total weight of containers on the ship
    @Override
    public double CapacityCal(Vehicle vehicle) {
        Double totalWeight = 0.0;
        for (Container container : vehicle.Containers) {
            totalWeight += container.getWeight();
            if (totalWeight > vehicle.Capacity) {
                System.out.println("Capacity is full!");
                System.out.println("Last container is not loaded");
                containers.remove(this.containers.size() - 1);
            }
        }
        return totalWeight;
    }

    // Override toString method to provide a string representation of the ship
    @Override
    public String toString() {
        return "Ship{" +
                "ID='" + ID + '\'' +
                ", fuel=" + fuel +
                ", capacity=" + capacity +
                ", containers=" + containers +
                '}';
    }

    // Save method implementation, invoking super class's save method
    @Override
    public void save(String PortNum, String ObjectID, Object obj) throws IOException {
        super.save(currentPortNum, this.ID, this);
    }

    // Method to calculate fuel consumption based on container types
    @Override
    public Double FuelCalculation() {
        Double total = 0.0;
        for (Container container : this.Containers) {
            String containerType = container.getType();
            if (containerType.equals("dry storage")) {
                total += 3.5;
            } else if (containerType.equals("open top")) {
                total += 2.8;
            } else if (containerType.equals("open side")) {
                total += 2.7;
            } else if (containerType.equals("refrigerated")) {
                total += 4.5;
            } else if (containerType.equals("liquid")) {
                total += 4.8;
            }
        }
        return total;
    }

    // Getter for the list of containers on the ship
    @Override
    public ArrayList<Container> getContainers() {
        return containers;
    }
}
