/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package Super;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public interface PortLoader {

    // Default method to load all ports
    default List<Port> loadAllPorts() throws IOException, ClassNotFoundException {
        List<Port> ports = new ArrayList<>();

        // Get the root path of the project
        String projectRoot = System.getProperty("user.dir");
        String dirPath = projectRoot + "/src/Data/";

        // Get a list of directories in the data folder
        File dataDir = new File(dirPath);
        if (dataDir.exists() && dataDir.isDirectory()) {
            File[] portDirs = dataDir.listFiles();

            // Iterate through port directories
            for (File portDir : portDirs) {
                if (portDir.isDirectory()) {
                    File[] filesInDir = portDir.listFiles();

                    // Iterate through files in each port directory
                    for (File file : filesInDir) {
                        // Check if it's a valid data file (ends with .txt) and matches the port directory name
                        if (file.isFile() && file.getName().endsWith(".txt") && file.getName().equals(portDir.getName() + ".txt")) {
                            String portNum = portDir.getName();
                            String path = dirPath + portNum + "/" + file.getName();

                            // Read the serialized Port object from the file
                            FileInputStream fileIn = new FileInputStream(path);
                            ObjectInputStream in = new ObjectInputStream(fileIn);
                            Port port = (Port) in.readObject();
                            in.close();
                            fileIn.close();

                            // Add the loaded Port object to the list
                            ports.add(port);
                        }
                    }
                }
            }
        } else {
            System.out.println("There is no port directory");
        }

        return ports;
    }
}
