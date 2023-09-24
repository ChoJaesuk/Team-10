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
import java.util.List;

public class PortDataLoader implements PortLoader {

    public static void main(String[] args) {
        try {
            // Create an instance of PortLoader (assuming it's implemented elsewhere)
            PortLoader loader = new PortDataLoader();

            // Load all ports
            List<Port> ports = loader.loadAllPorts();

            // Iterate through the loaded ports and print their information
            for (Port port : ports) {
                System.out.println("Port Name: " + port.getName());
                System.out.println("Port Number: " + port.getPortNum());
                System.out.println("Storing Capacity: " + port.getStoringCapacity());
                System.out.println("Latitude: " + port.getLatitude());
                System.out.println("Longitude: " + port.getLongitude());
                System.out.println("Landing Ability: " + port.getLandingAbility());
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
