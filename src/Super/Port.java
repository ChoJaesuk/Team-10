/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package Super;

import java.io.*;

public class Port implements Serializable, Save {
    private static final long serialVersionUID = 1L;
    private String Name; // Name of the port
    private String portNum; // Unique port number identifier
    private Double StoringCapacity; // Storage capacity of the port
    private Double Longitude; // Longitude coordinate of the port location
    private Double Latitude; // Latitude coordinate of the port location
    private boolean LandingAbility; // Indicates if the port has landing ability for ships

    // Getter for Latitude
    public Double getLatitude() {
        return Latitude;
    }

    // Getter for Longitude
    public Double getLongitude() {
        return Longitude;
    }

    // Getter for Port Number
    public String getPortNum() {
        return portNum;
    }

    // Getter for Name
    public String getName() {
        return Name;
    }

    // Getter for Storing Capacity
    public Double getStoringCapacity() {
        return StoringCapacity;
    }

    // Getter for Landing Ability
    public boolean getLandingAbility() {
        return LandingAbility;
    }

    // Default constructor initializing attributes to null
    public Port() {
        Name = null;
        portNum = null;
        StoringCapacity = null;
    }

    // Constructor to create a Port object with specified attributes
    public Port(String Name, String PortNum, Double StoringCapacity, Double Longitude, Double Latitude, boolean LandingAbility) {
        this.Name = Name;
        this.portNum = PortNum;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
        this.StoringCapacity = StoringCapacity;
        this.LandingAbility = LandingAbility;

        // Save port information
        try {
            save(PortNum, PortNum, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a directory for the port's data
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/src/Data/" + PortNum + "/";
        try {
            File file = new File(filePath + "/History.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Method to calculate the total weight of containers in a port
    public double TotalWeightCalculate(String portnum) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + portnum;
        File folder = new File(path);
        File[] fileList = folder.listFiles();
        double totalResult = 0.0;
        if (fileList != null) {
            for (File f : fileList) {
                String name = f.getName();
                String fileTag = "" + name.charAt(0) + name.charAt(1);
                if (f.isFile() && fileTag.equals("C-")) {
                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                        Container c = (Container) input.readObject();
                        double weight = c.getWeight();
                        totalResult += weight;
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return totalResult;
    }

    // Implementation of the save method from the Save interface
    @Override
    public void save(String PortNum, String ObjectID, Object obj) throws IOException {
        Save.super.save(PortNum, ObjectID, obj);
    }

    // Override toString method to provide a string representation of the port
    @Override
    public String toString() {
        return "Port{" +
                "Name='" + Name + '\'' +
                ", portNum='" + portNum + '\'' +
                ", StoringCapacity=" + StoringCapacity +
                ", Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                ", LandingAbility=" + LandingAbility +
                '}';
    }
}
