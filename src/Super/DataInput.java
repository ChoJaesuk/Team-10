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

public class DataInput {
    public static void main(String[] args) {
        int numberOfPorts = 5; // Input data for 5 ports

        String[] portDataArray = new String[numberOfPorts]; // Array to store port data

        try {
            // Input data for 5 ports
            inputData(portDataArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print data stored in the array (can be later saved to a file)
        System.out.println("Port data:");
        for (String portData : portDataArray) {
            System.out.println(portData);
        }
    }

    public static void inputData(String[] portDataArray) throws IOException {
        System.out.println("Start port data input.");

        // Provided longitude and latitude data for the ports
        double[] longitudes = {105.8542, 108.2022, 103.9840, 107.1839, 106.6819};
        double[] latitudes = {21.0285, 16.0544, 10.2899, 20.9101, 20.8561};
        boolean[] landingAbilities = {true, false, true, false, true};

        // Input data for 5 ports
        for (int i = 0; i < portDataArray.length; i++) {
            String portName = "Port" + (i + 1);
            String portNum = "PortNum" + (i + 1);
            double storingCapacity = 1000.0 * (i + 1); // Arbitrary storage capacity data (Example: 1000, 2000, ...)
            double longitude = longitudes[i]; // Use provided longitude data
            double latitude = latitudes[i]; // Use provided latitude data
            boolean landingAbility = landingAbilities[i];

            // Create a Port object
            Port port = new Port(portName, portNum, storingCapacity, longitude, latitude, landingAbility);

            // Convert Port object to a string and store it in the array
            portDataArray[i] = port.toString(); // Modify the toString() method to match the format
        }

        // Input process completed
        System.out.println("Port data input is completed.");
    }
}
