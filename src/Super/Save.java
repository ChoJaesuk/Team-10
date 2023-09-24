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

public interface Save {

    // Default method for saving an object to a file
    default void save(String PortNum, String ObjectID, Object obj) throws IOException {
        String projectRoot = System.getProperty("user.dir");
        String dirPath = projectRoot + "/src/Data/" + PortNum + "/";

        // Create a directory if it doesn't exist
        File Folder = new File(dirPath);
        if (!Folder.exists()) {
            try {
                Folder.mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        String path = projectRoot + "/src/Data/" + PortNum + "/" + ObjectID + ".txt";

        try {
            // Serialize and save the object to the file
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();

            // Print a success message
//            System.out.printf("Serialized data for %s is saved ", ObjectID);
//            System.out.println(" ");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
