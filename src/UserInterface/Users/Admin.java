/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package UserInterface.Users;

import Super.Port;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    public Admin() {
    }

    public Admin(String userName, String password) throws IOException {
        super("Admin", userName, password, "Admin");
    }

    Scanner scan = new Scanner(System.in);

    public void LoadAllUsers() throws IOException {
        try {
            LoadAllAdmins();
            LoadAllPortManagers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadAllAdmins() throws IOException {
        try {
            ListByRoles("Admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadAllPortManagers() throws IOException {
        try {
            ListByRoles("PortManager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ListByRoles(String role) throws IOException {
        try {
            String projectRoot = System.getProperty("user.dir");
            String path = projectRoot + "/src/";
            BufferedReader reader = new BufferedReader(new FileReader(path + "Data/" + role + "s.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(" ");
                if (lines.length > 1) {
                    String username = lines[1];
                    System.out.println(role + " User name: " + username + "\n");
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deletePort(String PortNum) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + PortNum;
        File target = new File(path);
        try {
            while (target.exists()) {
                File[] folder_list = target.listFiles();
                for (int j = 0; j < folder_list.length; j++) {
                    folder_list[j].delete();
                }
                if (folder_list.length == 0 && target.isDirectory()) {
                    target.delete();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void deleteFile(String PortNum, String Filename) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + PortNum;
        File target = new File(path);
        File[] folder_list = target.listFiles();

        for (int j = 0; j < folder_list.length; j++) {
            String file = folder_list[j].getName();

            if (Filename.equals(file)) {
                folder_list[j].delete();
                System.out.println("Deleting complete");
                return;
            }
        }
        System.out.println("Deleting failed");
    }

    public List<Port> loadAllPorts() throws IOException, ClassNotFoundException {
        List<Port> ports = new ArrayList<>();
        String projectRoot = System.getProperty("user.dir");
        String dirPath = projectRoot + "/src/Data/";

        // Bringing file list from data folder
        File dataDir = new File(dirPath);
        if (dataDir.exists() && dataDir.isDirectory()) {
            File[] portDirs = dataDir.listFiles();
            for (File portDir : portDirs) {
                if (portDir.isDirectory()) {
                    File[] filesInDir = portDir.listFiles();
                    for (File file : filesInDir) {
                        if (file.isFile() && file.getName().endsWith(".txt") && file.getName().equals(portDir.getName() + ".txt")) {
                            String portNum = portDir.getName();
                            String path = dirPath + portNum + "/" + file.getName();
                            FileInputStream fileIn = new FileInputStream(path);
                            ObjectInputStream in = new ObjectInputStream(fileIn);
                            Port port = (Port) in.readObject();
                            in.close();
                            fileIn.close();

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
