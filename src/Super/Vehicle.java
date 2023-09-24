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
import java.util.ArrayList;

// Abstract class representing a generic vehicle
public abstract class Vehicle implements Save {
    public String ID; // Unique identifier for the vehicle
    public ArrayList<Container> Containers = new ArrayList<>(); // List of containers on the vehicle
    public Double Capacity; // Capacity of the vehicle
    public String currentPortNum; // The current port number where the vehicle is located
    public static Double fuel = 100.0; // Static attribute representing the fuel capacity for all vehicles

    // Default constructor for a vehicle
    public Vehicle() {}

    // Constructor for creating a vehicle with specified attributes
    public Vehicle(String ID, double Capacity, String currentPortNum) throws IOException {
        this.ID = ID;
        this.Capacity = Capacity;
        this.currentPortNum = currentPortNum;
    }



    // Abstract method to retrieve the ID of the vehicle (to be implemented by subclasses)
    abstract String getID();

    // Abstract method to load a container onto the vehicle (to be implemented by subclasses)
    abstract void loadContainer(Container container) throws IOException;

    // Abstract method to unload a container from the vehicle (to be implemented by subclasses)
    abstract void unloadContainer(Container container) throws IOException;

    // Abstract method to get the current fuel level of the vehicle (to be implemented by subclasses)
    abstract Double getFuel();

    // Abstract method to set the current port number of the vehicle (to be implemented by subclasses)
    abstract void setCurrentPortNum(String currentPortNum) throws IOException;

    // Abstract method to calculate fuel consumption given a certain consumption rate (to be implemented by subclasses)
    abstract Double FuelConsumption(Double consumption) throws IOException;

    // Abstract method to refuel the vehicle (to be implemented by subclasses)
    abstract void Refuel() throws IOException;

    // Abstract method to calculate the total weight of containers on the vehicle (to be implemented by subclasses)
    abstract double CapacityCal(Vehicle vehicle);

    // Abstract method to calculate fuel consumption based on container types (to be implemented by subclasses)
    abstract Double FuelCalculation();

    // Abstract method to retrieve the list of containers on the vehicle (to be implemented by subclasses)
    public abstract ArrayList<Container> getContainers();
}
