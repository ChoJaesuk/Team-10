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

public class Truck extends Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Type; // Type of truck (e.g., tanker, reefer)
    private String ID; // Unique truck identifier (combination of "Tr-" and numbers)
    private Double fuel = 700.0; // Initial fuel capacity
    private Double capacity; // Capacity of the truck
    private ArrayList<Container> containers = new ArrayList<>(); // List of containers on the truck
    private Double kmPerFuel = null; // Fuel consumption per kilometer
    private String currentPortNum = null; // The current port number where the truck is located

    // Default constructor initializing attributes
    public Truck(){
        fuel = 700.0; // Typical fuel capacity
    }

    public String getID() {
        return ID;
    }

    // Constructor for creating a Truck object with specified attributes
    public Truck(String ID, String Type, Double Capacity, String currentPortNum) throws IOException {
        this.ID = ID;
        this.Type = Type;
        this.Capacity = Capacity;
        this.currentPortNum = currentPortNum;
        this.containers = new ArrayList<>();
        save(currentPortNum, this.ID, this);
    }

    public String getType() {
        return Type;
    }

    // Method to load a container onto the truck
    @Override
    public void loadContainer(Container container) throws IOException {
        if (container.getType().equals("liquid")) {
            if (this.Type.equals("tanker")) {
                CapacityCal(this);
                this.containers.add(container);
                this.kmPerFuel = FuelCalculation();
                try {
                    save(currentPortNum, this.ID, this);
                    System.out.println("Truck load container.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Truck doesn't have liquid storage.");
            }
        } else if (container.getType().equals("refrigerated")) {
            if (this.Type.equals("reefer")) {
                CapacityCal(this);
                this.containers.add(container);
                this.kmPerFuel = FuelCalculation();
                try {
                    save(currentPortNum, this.ID, this);
                    System.out.println("Truck load container.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Truck doesn't have refrigerator storage.");
            }
        } else {
            CapacityCal(this);
            this.containers.add(container);
            this.kmPerFuel = FuelCalculation();
            try {
                save(currentPortNum, this.ID, this);
                System.out.println("Truck load container.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Method to unload a container from the truck
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
        this.kmPerFuel = FuelCalculation();
        save(currentPortNum, this.ID, this);
    }

    // Getter for fuel capacity
    @Override
    public Double getFuel() {
        return fuel;
    }

    // Method to update fuel consumption and save the truck object
    @Override
    public Double FuelConsumption(Double consumption) throws IOException {
        if ((this.fuel -= consumption) < 0) {
            System.out.println("Fuel will be lower than 0! Please refuel.");
        } else {
            this.fuel -= consumption;
        }
        save(currentPortNum, this.ID, this);
        return this.fuel;
    }

    // Truck fuel efficiency to calculate fuel consumption
    public Double truckFuelEfficiency() {
        Double total = 0.0;
        for (Container container : this.Containers) {
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



    // Method to refuel the truck
    @Override
    public void Refuel() throws IOException {
        this.fuel = 700.0; // Reset fuel capacity
        save(currentPortNum, this.ID, this);
    }

    // Method to calculate the total weight of containers on the truck
    @Override
    public double CapacityCal(Vehicle vehicle) {
        Double totalWeight = 0.0;
        for (Container container : vehicle.Containers) {
            totalWeight += container.getWeight();
            if (totalWeight > vehicle.Capacity) {
                System.out.println("Capacity is full!");
                System.out.println("Last container is not loaded");
                Containers.remove(this.Containers.size() - 1);
            }
        }
        return totalWeight;
    }

    // Override toString method to provide a string representation of the truck
    @Override
    public String toString() {
        return "Truck{" +
                "Type='" + Type + '\'' +
                ", ID='" + ID + '\'' +
                ", fuel=" + fuel +
                ", capacity=" + capacity +
                ", containsers=" + containers +
                '}';
    }

    // Method to calculate fuel consumption based on container types
    @Override
    Double FuelCalculation() {
        Double total = 0.0;
        for (Container container : this.Containers) {
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

    // Getter for the list of containers on the truck
    @Override
    public ArrayList<Container> getContainers() {
        return containers;
    }

    // Getter for the current port number
    public String getCurrentPortNum() {
        return currentPortNum;
    }

    @Override
    public void setCurrentPortNum(String currentPortNum) throws IOException {
        // Update the current port number with the provided value
        this.currentPortNum = currentPortNum;
        // Save the updated truck object to a file with the new port number and ID
        save(currentPortNum, this.ID, this);
    }
}
