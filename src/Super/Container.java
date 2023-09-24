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
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable, Save {
    @Serial
    private static final long serialVersionUID = 1L;
    private String ID; // Unique identifier for the container
    private String Type; // Type of the container (e.g., dry storage, open top, etc.)
    private Double Weight; // Weight of the container
    private Double consumptionFuel; // Fuel consumption associated with the container
    private String currentPortNum; // The current port number where the container is located (set to a non-null value by default)

    public ArrayList<String> Types = new ArrayList<>(List.of(new String[]{"dry storage", "open top", "open side", "refrigerated", "liquid"})); // List of valid container types

    public Container() {
        this.ID = null; // Initializing container ID to null
        this.Type = null; // Initializing container type to null
        this.Weight = null; // Initializing container weight to null
        this.consumptionFuel = null; // Initializing fuel consumption to null
        this.currentPortNum = null; // Initializing current port number to null (default)
    }

    // Setter method for setting the container type
    public void setType(String type) {
        if (InTypes(type)) {
            this.Type = type;
        }
    }

    // Getter method for retrieving the container weight
    public Double getWeight() {
        return Weight;
    }

    // Method to check if a given container type is valid
    public boolean InTypes(String type) {
        if (Types.contains(type)) {
            return true;
        }
        return false;
    }

    // Constructor for creating a container with specified attributes
    public Container(String ID, String type, Double Weight, String PortNum) throws IOException {
        if (InTypes(type)) {
            this.ID = ID; // Setting the container ID
            this.Type = type; // Setting the container type
            this.Weight = Weight; // Setting the container weight
            this.currentPortNum = PortNum; // Setting the current port number
            save(PortNum, this.ID, this); // Saving container information
        } else {
            System.out.println("Type is not suitable."); // Displaying error message if the container type is not valid
        }
    }

    // Setter method for updating the current port number
    public void setCurrentPortNum(String currentPortNum) {
        if (currentPortNum != null) { // Checking if the provided port number is not null
            this.currentPortNum = currentPortNum; // Updating the current port number
        } else {
            System.out.println("currentPortNum cannot be null."); // Displaying error message if the port number is null
        }
    }


    // Getter method for retrieving the container type
    public String getType() {
        return Type;
    }

    // Getter method for retrieving the fuel consumption associated with the container
    public Double getConsumptionFuel() {
        return consumptionFuel;
    }

    // Getter method for retrieving the container ID
    public String getID() {
        return ID;
    }

    // Override toString method to provide a string representation of the container
    @Override
    public String toString() {
        return "Container{" +
                "ID='" + ID + '\'' +
                ", Type='" + Type + '\'' +
                ", Weight=" + Weight +
                ", consumptionFuel=" + consumptionFuel +
                '}';
    }

    // Implementation of the save method from the Save interface
    @Override
    public void save(String PortNum, String ObjectID, Object obj) throws IOException {
        Save.super.save(PortNum, ObjectID, obj);
    }
}
