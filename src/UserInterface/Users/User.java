/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package UserInterface.Users;

import Super.Container;
import Super.Port;
import Super.Ship;
import Super.Truck;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String userName;
    private String password;
    private String role;
    private String portNum;

    public User() {
        role = null;
        userName = null;
        password = null;
    }

    private ArrayList<String> availableRoles = new ArrayList<String>(List.of(new String[]{"Admin", "PortManager"}));

    public User(String role, String userName, String password, String portNum) throws IOException {
        if (availableRoles.contains(role)) {
            this.role = role;
            this.userName = userName;
            this.password = password;
            if (role.equals("Admin")) {
                SaveAdmin();
            } else if (this.role.equals("PortManager")) {
                this.role = role;
                this.userName = userName;
                this.password = password;
                this.portNum = portNum;
                SavePortManager();
            }
        } else {
            System.out.println("Please enter the valid role.");
        }
    }

    public String getRole() {
        return role;
    }

    private void SavePortManager() throws IOException {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/";
        PrintWriter output = new PrintWriter(new FileWriter(path + "Data/PortManagers.txt", true));
        output.println(this.role + " " + this.userName + " " + this.password + this.portNum + "\n");
        output.println("");
        output.flush();
        output.close();

    }

    private void SaveAdmin() throws IOException {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/";
        PrintWriter output = new PrintWriter(new FileWriter(path + "Data/Admins.txt", true));
        output.println(this.role + " " + this.userName + " " + this.password + "\n");
        output.flush();
        output.close();

    }
    public Truck refuelTruck(String PortNum, String truckID) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "\\src/Data/" + PortNum + "\\Tr-" + truckID + ".txt";

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return null;
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = input.readObject();
            if (obj instanceof Truck) {
                return (Truck) obj;
            } else {
                System.out.println("Found an object that is not a truck.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("There is no matching truck.");
        return null;
    }

    public Ship refuelShip(String PortNum, String shipID) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "\\src/Data/" + PortNum + "\\Sh-" + shipID + ".txt";

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return null;
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = input.readObject();
            if (obj instanceof Ship) {
                return (Ship) obj;
            } else {
                System.out.println("Found an object that is not a Ship.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("There is no matching Ship.");
        return null;
    }

    public Ship searchingShip(String PortNum, String ShipID) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + PortNum;

        File folder = new File(path);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory.");
            return null; // or take another action
        }

        File[] FileList = folder.listFiles();

        if (FileList == null) {
            System.out.println("No files in the directory.");
            return null; // or take another action
        }

        for (File f : FileList) {
            String name = f.getName();

            if (name.equals(ShipID + ".txt")) {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                    Object obj = input.readObject();
                    if (obj instanceof Ship) {
                        return (Ship) obj;
                    } else {
                        System.out.println("Found an object that is not a Ship.");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("There is no matching Ship.");
        return null;
    }
    public Truck searchingTruck(String PortNum, String truckID) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + PortNum;

        File folder = new File(path);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory.");
            return null; // or take another action
        }

        File[] FileList = folder.listFiles();

        if (FileList == null) {
            System.out.println("No files in the directory.");
            return null; // or take another action
        }

        for (File f : FileList) {
            String name = f.getName();

            if (name.equals(truckID + ".txt")) {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                    Object obj = input.readObject();
                    if (obj instanceof Truck) {
                        return (Truck) obj;
                    } else {
                        System.out.println("Found an object that is not a truck.");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("There is no matching truck.");
        return null;
    }

    public Port searchingPort(String PortNum) {
        Port result = null;
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + PortNum;
        File folder = new File(path);

        // Check directory exists
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory.");
            return null; // or take another action
        }

        File[] FileList = folder.listFiles();

        // process when file is null
        if (FileList == null) {
            System.out.println("No files in the directory.");
            return null; // or take another action
        }

        for (File f : FileList) {
            String name = f.getName();
            if (f.isFile() && name.startsWith(PortNum)) {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                    Object obj = input.readObject();
                    if (obj instanceof Port) {
                        return (Port) obj;
                    } else {
                        System.out.println("Found an object that is not a Port.");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("There is no matching port.");
        return null;
    }


    //    public Container searchingContainer(String PortNum,String containerID){
//        String projectRoot = System.getProperty("user.dir");
//        String path = projectRoot+"untitled2/src/Data/"+PortNum;
//        File folder = new File(path);
//        File[] FileList = folder.listFiles();
//        Container result = new Container();
//        for (File f : FileList) {
//            String name = f.getName();
//            if (f.isFile() && name.startsWith("C-"+containerID)) {
//                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
//                    Object obj = input.readObject();
//                    result = (Container) obj;
//                } catch (IOException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//            return result;
//    }
//        System.out.println("There is no matching container.");
//    return null;
//    }
    public Container searchingContainer(String PortNum, String containerID) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + PortNum;

        File folder = new File(path);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory.");
            return null; // Or take other action
        }

        File[] FileList = folder.listFiles();

        if (FileList == null) {
            System.out.println("No files in the directory.");
            return null; // Or take other action
        }

        for (File f : FileList) {
            String name = f.getName();

            if (name.equals(containerID + ".txt")) {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                    Object obj = input.readObject();
                    if (obj instanceof Container) {
                        return (Container) obj;
                    } else {
                        System.out.println("Found an object that is not a Container.");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("There is no matching container.");
        return null;
    }

    public void printAllFilesStartWith(String startWord, String portNum) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + portNum;
        File folder = new File(path);
        File[] FileList = folder.listFiles();

        for (File f : FileList) {

            String name = f.getName();
            String fileTag = "";
            if (startWord.equals("C-")) {
                fileTag = "" + name.charAt(0) + name.charAt(1);
                if (fileTag.equals(startWord)) {
                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                        Object obj = input.readObject();
                        System.out.println(obj.toString());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else if (startWord.equals("Sh-") || startWord.equals("Tr-")) {
                fileTag = "" + name.charAt(0) + name.charAt(1) + name.charAt(2);
                if (fileTag.equals(startWord)) {

                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                        Object obj = input.readObject();
                        System.out.println(obj.toString());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void removePortManagerID(String targetID) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/PortManagers.txt";
        File tempFile = new File(projectRoot + "/src/Data/tmp.txt");
        try {
            FileReader fr = new FileReader(path);
            FileWriter tempFileWriter = new FileWriter(tempFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            BufferedWriter bufferedWriter = new BufferedWriter(tempFileWriter);
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!currentLine.contains(targetID)) {
                    bufferedWriter.write(currentLine);
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            File originalFile = new File(path);
            if (originalFile.delete()) {
                if (tempFile.renameTo(originalFile)) {
                    System.out.println("Delete complete");
                } else {
                    System.out.println("Failed to delete");
                }
            } else {
                System.out.println("Failed to delete original file");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public double TotalWeightCalculate(String portnum){
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + portnum;
        File folder = new File(path);
        File[] fileList = folder.listFiles();
        double totalResult = 0.0;
        if (fileList != null) {
            for (File f : fileList) {
                String name = f.getName();
                String tag = ""+name.charAt(0)+name.charAt(1);
                if (tag.equals("C-")) {
                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                        Container c = (Container) input.readObject();
                        double weight = c.getWeight();
                        totalResult+=weight;
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return totalResult;
    }

    public void weightCalculate(String portNum) {
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/src/Data/" + portNum;
        File folder = new File(path);
        File[] fileList = folder.listFiles();
        HashMap<String, Double> weightByType = new HashMap<>();
        HashMap<String, Integer> countByType = new HashMap<>();

        if (fileList != null) {
            for (File f : fileList) {
                String name = f.getName();
                String tagName = "" + name.charAt(0) + name.charAt(1);

                if (tagName.equals("C-")) {
                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(f))) {
                        Container c = (Container) input.readObject();
                        String type = c.getType();
                        double weight = c.getWeight();

                        if (weightByType.containsKey(type)) {
                            double currentWeight = weightByType.get(type);
                            weightByType.put(type, currentWeight + weight);
                            int currentCount = countByType.get(type);
                            countByType.put(type, currentCount + 1);
                        } else {
                            weightByType.put(type, weight);
                            countByType.put(type, 1);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (Map.Entry<String, Double> entry : weightByType.entrySet()) {
            String type = entry.getKey();
            double totalWeight = entry.getValue();
            int count = countByType.get(type);
            System.out.println("Type: " + type + ", Total Weight: " + totalWeight + ", Count: " + count);
        }
    }

}


