/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

import Super.*;
import UserInterface.Users.Admin;
import UserInterface.Users.PortManager.PortManager;
import UserInterface.Users.User;
import UserInterface.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class MainMenu implements PortLoader {

    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Welcome to port management system interface");
        initalization();

    }

    // Initialization method: Starts the program and allows the user to choose their role
    private static void initalization() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while(true){

            System.out.println("1. Login"); // Login option
            System.out.println("e. Exit"); // Exit program option
            System.out.print("Input: ");
            String roleChoice = scanner.next();
            switch (roleChoice) {
                case "1":
                    loginMenu(); // Move to the login menu
                    break;
                case "e":
                    System.out.println("Exiting the program.");
                    System.exit(0); // Exit the program
                    break;
                default:
                    System.out.println("Invalid input. Please choose again.");
                    pressEnterKeyToContinue();

            }


        }
    }
    // Login menu: Allows users to select their role (Admin or Port Manager) and log in
    private static void loginMenu() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        login login = new login();
        String password;
        String username;
        int Count = 0;
        while(true){
            if(Count>=5){
                System.out.println("Exceeded the maximum number of attempts. Exiting the program.");
                System.exit(0);
            }
            System.out.println("[ Choose your role ]");
            System.out.println("1. Admin");
            System.out.println("2. Port Manager");
            System.out.println("e. Exit");
            System.out.print("Input: ");
            String roleChoice = scanner.nextLine();
            switch (roleChoice) {
                case "1":
                    System.out.println("Enter your username.");
                    System.out.print("Input: ");
                    username = scanner.nextLine();
                    System.out.println("Enter your password.");
                    System.out.print("Input: ");
                    password = scanner.nextLine();
                    if(login.Validation("Admin",username,password)){
                        login login1 = new login("Admin",username,password);
                        adminMenu(login1);
                    }else{
                        System.out.println("Your username or password is incorrect");
                        System.out.println("If count is more than 5 times. System will be closed");
                        Count +=1;
                        switch (Count) {
                            case 1:
                                System.out.printf("You have failed to log in %d time%n", Count);
                                break;
                            default:
                                System.out.printf("You have failed to log in %d times%n", Count);
                        }
                        System.out.printf("Your left trial is %d%n%n", 5 - Count);
                        pressEnterKeyToContinue();
                        break;
                    }
                case "2":
                    System.out.println("Enter your username.");
                    System.out.print("Input: ");
                    username = scanner.nextLine();
                    System.out.println("Enter your password.");
                    System.out.print("Input: ");
                    password = scanner.nextLine();
                    if(login.Validation("PortManager",username,password)){
                        login login1 = new login("PortManager",username,password);
                        portManagerTaskMenu(scanner,login1.getPortNum(),login1);
                    }else{
                        System.out.println("Your username or password is incorrect");
                        System.out.println("If count is more than 5 times. System will be closed");
                        switch (Count) {
                            case 1:
                                System.out.printf("You have failed to log in %d time%n", Count);
                                break;
                            default:
                                System.out.printf("You have failed to log in %d times%n", Count);
                        }
                        System.out.printf("Your left trial is %d%n%n", 5 - Count);
                        pressEnterKeyToContinue();
                        break;
                    }
                case "e":
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please choose again.");
                    pressEnterKeyToContinue();
            }
        }
    }
    // Admin menu: After logging in as an Admin, allows users to select tasks to perform
    private static void adminMenu(login login) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Choose the menu:");
            System.out.println("1. Admin");
            System.out.println("2. Port Manager");
            System.out.println("e. Exit");
            System.out.print("Input: ");
            String roleChoice = scanner.nextLine();
            switch (roleChoice) {
                case "1":
                    adminTaskMenu(scanner, login);
                case "2":
                    adminPortManagerTaskMenu(scanner, login);
                case "e":
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please choose again.");
                    pressEnterKeyToContinue();
            }}}

    // Method for handling the admin's menu to select a Port Manager's task for a specific port
    private static void adminPortManagerTaskMenu(Scanner scanner, login login) throws IOException, ClassNotFoundException {

        System.out.println("This is the port list");
        while (true){
            System.out.println("1. Port1 ");
            System.out.println("2. Port2 ");
            System.out.println("3. Port3 ");
            System.out.println("4. Port4 ");
            System.out.println("5. Port5 ");
            System.out.println("0. Exit ");
            System.out.println("Select the port to access: ");

            int adminChoice = 0;

            if (scanner.hasNextInt()) {
                adminChoice = scanner.nextInt();
                scanner.nextLine(); // Handling the newline character
            } else {
                // Handling non-integer input
                scanner.nextLine(); // Clearing invalid input
                System.out.println("Invalid input. Please choose again.");
                pressEnterKeyToContinue();
                continue;
            }

            switch (adminChoice) {

                case 1:
                    // Access Port Manager tasks for Port1
                    portManagerTaskMenu(scanner,"Port1",login);
                    return;
                case 2:
                    // Access Port Manager tasks for Port2
                    portManagerTaskMenu(scanner,"Port2",login);
                    return;
                case 3:
                    // Access Port Manager tasks for Port3
                    portManagerTaskMenu(scanner,"Port3",login);
                    return;
                case 4:
                    // Access Port Manager tasks for Port4
                    portManagerTaskMenu(scanner,"Port4",login);
                    return;
                case 5:
                    // Access Port Manager tasks for Port5
                    portManagerTaskMenu(scanner,"Port5",login);
                    return;
                case 0:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please choose again.");
                    pressEnterKeyToContinue();
            }
        }


    }
    // Method to load containers into a vehicle after finding them
    private static void loadContainers(Scanner scanner, String PortNum) throws IOException {
        User user = new User();
        System.out.println("Please Type the number of container id");
        String containerNum = scanner.next();
        System.out.println("Please Type the number of vehicle id");
        String vehicleID = scanner.next();
        System.out.println("Please Type the number of vehicle type");
        String type = scanner.next();

        Container container = user.searchingContainer(PortNum,"C-"+containerNum);
        if(type.equals("Ship")){
            Ship ship = user.searchingShip(PortNum,"Sh-"+vehicleID);
            ship.loadContainer(container);
            System.out.println(ship);
        } else if (type.equals("Truck")) {
            Truck truck = user.searchingTruck(PortNum,"Tr-"+vehicleID);
            truck.loadContainer(container);
            System.out.println(truck);
        }
    }
    // Method to unload containers from a vehicle after finding them
    private static void unloadContainers(Scanner scanner,String PortNum) throws IOException {
        User user = new User();

        System.out.println("Please enter the vehicle id number(e.g. 12)");
        String vehicleID = scanner.next();
        System.out.println("Please enter the vehicle type (e.g. 1. Ship 2. Truck)");
        String type = scanner.next();

        System.out.println("Please enter the container id number (e.g. 23)");
        String containerNum = scanner.next();

        Container container = user.searchingContainer(PortNum,"C-"+containerNum);
        if (container != null) {
            // Perform the operation only when the container is not null
            if (type.equals("Ship")) {
                Ship ship = user.searchingShip(PortNum, "Sh-"+vehicleID);
                if (ship != null) {
                    ship.unloadContainer(container);
                } else {
                    // Handle the case when the ship is not found
                    System.out.println("Ship not found.");
                }
            } else if (type.equals("Truck")) {
                Truck truck = user.searchingTruck(PortNum, "Tr-"+vehicleID);
                if (truck != null) {
                    truck.unloadContainer(container);
                } else {
                    // Handle the case when the truck is not found
                    System.out.println("Truck not found.");
                    pressEnterKeyToContinue();
                }
            }
        } else {
            // Handle the case when the container is not found
            System.out.println("Container not found.");
            pressEnterKeyToContinue();
        }
    }
    // Method to refuel a vehicle
    private static void refuel(Scanner scanner, String PortNum) throws IOException{
        System.out.println("Enter the Vehicle ID number(e.g. 12): ");
        int vehicleID = scanner.nextInt();
        System.out.println("Enter the Vehicle's type (e.g. Ship): ");
        String type = scanner.next();
        User user = new User();
        if(type.equals("Ship")){
            Ship ship = user.refuelShip(PortNum, String.valueOf(vehicleID));
            if(ship.getFuel() >= 360000.0){
                System.out.println("The ship is already fully fueled.");
            } else {
                ship.Refuel();
                System.out.println("Refueling completed.");
                pressEnterKeyToContinue();
            }
        } else if (type.equals("Truck")) {
            Truck truck = user.refuelTruck(PortNum, String.valueOf(vehicleID));
            if(truck.getFuel() >= 700.0){
                System.out.println("The truck is already fully fueled.");
            } else {
                truck.Refuel();
                System.out.println("Refueling completed.");
                pressEnterKeyToContinue();
            }

        } else {
            System.out.println("Invalid vehicle type.");
            pressEnterKeyToContinue();
        }
    }

    private static void listTripsInDate(Scanner scanner,login login, String PortNum) throws IOException, ClassNotFoundException {
        PortManager portManager = new PortManager();
        System.out.println("Enter the departure date month");
        String ddm1 = scanner.next();
        System.out.println("Enter the departure date day");
        String ddd1 = scanner.next();
        String departureDate = "2023-"+ddm1+"-"+ddd1;

        System.out.println("Enter the arrival date month");
        String adm1 = scanner.next();
        System.out.println("Enter the arrival date day");
        String add1 = scanner.next();
        String arrivalDate = "2023-"+adm1+"-"+add1;


        System.out.println("Recall History conditionally.");
        portManager.ListTripsinDays( PortNum, departureDate, arrivalDate);
        portManagerTaskMenu(scanner,PortNum, login);
        pressEnterKeyToContinue();
    }
    // Port Manager menu: After logging in as a Port Manager, allows users to select tasks to perform
    private static void portManagerTaskMenu(Scanner scanner,String PortNum, login login) throws IOException, ClassNotFoundException {
        while (true){
            User user = new User();
            PortManager portManager = new PortManager();
            System.out.println("[ Port Manager Menu ]");
            System.out.println("ac. Adding a container to a vehicle");
            System.out.println("dc. Deleting a container into a vehicle");
            System.out.println("r. Fueling vehicle");
            System.out.println("v. List all vehicles.");
            System.out.println("tr. List all trucks.");
            System.out.println("as. List all ships.");
            System.out.println("h. Recall History (7 days as of now)");
            System.out.println("td list today's trip");
            System.out.println("q. Recall History (conditional)");
            System.out.println("tw. Load Weight by Type");
            System.out.println("aw. Load all container weights");
            System.out.println("s. Output current remaining storage");
            System.out.println("df. Output the amount of fuel used per day");
            System.out.println("t. Create Trip (relevant information will be saved in History)");
            System.out.println("b. Back to previous Menu");
            System.out.print("Input: ");
            String portManagerChoice = scanner.next();

            switch (portManagerChoice) {
                case "ac":
                    loadContainers(scanner,PortNum);
                    // feature to assign containers to vehicle
                    System.out.println("Add a Container to the Vehicle.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "dc":
                    unloadContainers(scanner,PortNum);
                    // feature to assign containers to vehicle
                    System.out.println("Remove the container from the vehicle.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "r":
                    refuel(scanner, PortNum); // send fuel data
                    portManagerTaskMenu(scanner, PortNum, login); // send fuel data
                    break;

                case "v":
                    portManager.listAllVehicle(PortNum);
                    System.out.println("Output a list of all vehicles.");
                    portManagerTaskMenu(scanner,PortNum, login);

                    break;
                case "tr":
                    portManager.listAllTruck(PortNum);
                    System.out.println("Output a list of all Trucks.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "as":
                    portManager.listAllShip(PortNum);
                    System.out.println("Output a list of all Ships.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "h":
                    portManager.ListTripsin7Days(PortNum);
                    System.out.println("Print the History for 7 days.");
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "td":
                    portManager.ListTripsToday(PortNum);
                    portManagerTaskMenu(scanner,PortNum, login);
                case "q":
                    listTripsInDate(scanner,login,PortNum);
                    break;
                case "tw":
                    System.out.println("Output the total of container weights by type.");
                    user.weightCalculate(PortNum);
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "aw":
                    Port port = new Port();
                    double totalWeight = port.TotalWeightCalculate(PortNum);
                    System.out.println("Total weight of all containers: " + totalWeight);
                    break;
                case "s":
                    Port port1 = new Port();
                    double remainStorage =user.searchingPort(PortNum).getStoringCapacity() - port1.TotalWeightCalculate(PortNum); // 오류, 고쳐야됨
                    System.out.println("The total amount left now is " + remainStorage);
                    portManagerTaskMenu(scanner,PortNum, login);
                    break;
                case "t":
                    System.out.println("Enter Trip ID(e.g. just ): ");
                    String tripID = scanner.next();
                    System.out.println("Enter the departure Month (e.g. MM): ");
                    String ddm2 = scanner.next();
                    System.out.println("Enter the departure Day (e.g. DD): ");
                    String ddd2 = scanner.next();
                    System.out.println("Enter the arrivalDate Month (e.g. MM): ");
                    String adm2 = scanner.next();
                    System.out.println("Enter the arrivalDate day (e.g. DD): ");
                    String add2 = scanner.next();

                    System.out.println("Enter the type of Vehicle. Departure port should have this vehicle. 1.Truck 2.Ship (just enter the integer)");
                    int Type = scanner.nextInt();
                    String T;
                    if (Type == 1) {
                        T = "Truck";
                    } else {
                        T = "Ship";
                    }
                    System.out.println("Enter the Vehicle's ID(just enter the integer):. Departure port should have this vehicle.");
                    String VehicleIDNumber = scanner.next();
                    System.out.println("Enter the departure port number");
                    String departueIDNumber = scanner.next();
                    System.out.println("Enter the arrival port number");
                    String arrivalIDNumber = scanner.next();

                    Vehicle vehicle = portManager.searchVehicle("Port" + departueIDNumber, T, VehicleIDNumber);

                    if (vehicle != null) { // only works when vehicle is not null
                        LocalDate departureDate2 = portManager.StringToDate("2023-" + ddm2 + "-" + ddd2);
                        LocalDate arrivalDate2 = portManager.StringToDate("2023-" + adm2 + "-" + add2);
                        Trip trip = new Trip("T" + tripID, departureDate2, arrivalDate2, T, vehicle, "Port" + departueIDNumber, "Port" + arrivalIDNumber);
                        String departurePortID = "Port" + departueIDNumber;
                        String arrivalPortID = "Port" + arrivalIDNumber;


                    } else {
                        System.out.println("Vehicle not found.");
                        pressEnterKeyToContinue();
                    }

                    portManagerTaskMenu(scanner, PortNum, login);
                    break;
                case "b":
                    // go to the previous menu
                    if(login.getRole().equals("Admin")){
                        adminMenu(login);
                    }else{
                        main(null);
                    }
                    break;
                case"df": //fuel used per day - future work
                    String portID = PortNum;
                    double todayFuelConsumption = portManager.calculateDailyFuelConsumption(portID);
                    System.out.println("Today's fuel consumption of " + portID + " : " + todayFuelConsumption);
                    break;
                default:
                    System.out.println("Invalid input. Please choose again.");
                    pressEnterKeyToContinue();
            }
        }
    }


    // Admin task menu: Allows administrators to perform various tasks
    private static void adminTaskMenu(Scanner scanner, login login) throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        boolean exit = false; // Variable to check the exit condition

        while (!exit) {
            System.out.println("Admin Menu");
            System.out.println("1. Adding (Create Port, Container, Truck, Ship etc.)");
            System.out.println("2. Delete (Delete Port, Container, Truck, Ship, user etc.)");
            System.out.println("3. List All Ports");
            System.out.println("4. Add New User");
            System.out.println("5. List All Users");
            System.out.println("6. List All Port Managers");
            System.out.println("7. List All Admins");
            System.out.println("8. Back to previous Menu");
            System.out.print("Input: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    addingMenu(scanner, login);
                    break;
                case 2:
                    deletingMenu(scanner, login);
                    break;
                case 3:
                    try {
                        System.out.println(admin.loadAllPorts());
                    } catch (ClassNotFoundException e) {
                        System.out.println("There are no ports in this system");
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    signUp(scanner, login);
                    break;
                case 5:
                    admin.LoadAllUsers();
                    break;
                case 6:
                    admin.LoadAllPortManagers();
                    break;
                case 7:
                    admin.LoadAllAdmins();
                    break;
                case 8:
                    // Set exit to true to exit the loop
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input. Please choose again.");
                    pressEnterKeyToContinue();
            }
        }
    }
    // Adding menu: Allows users to add various entities
    private static void addingMenu(Scanner scanner,login login) throws IOException, ClassNotFoundException {
        if(login.getRole().equals("Admin") ){
            System.out.println("Adding List Menu:");
            System.out.println("1. Container");
            System.out.println("2. Truck");
            System.out.println("3. Ship");
            System.out.println("4. Port");
            System.out.println("5. Back to previous Menu");
            System.out.print("input: ");
            int addingChoice = scanner.nextInt();

            while (true){
                switch (addingChoice) {
                    case 1:
                        addingMenuContainer(scanner,login);
                        addingMenu(scanner,login);
                        break;
                    case 2:
                        addingMenuVehicle(scanner,"Truck",login);
                        addingMenu(scanner,login);
                        break;
                    case 3:
                        addingMenuVehicle(scanner,"Ship",login);
                        addingMenu(scanner,login);
                        break;
                    case 4:
                        adminAddingMenuPort(scanner);
                        addingMenu(scanner,login);
                        break;
                    case 5:
                        if(login.getRole().equals("Admin")){
                            adminTaskMenu(scanner,login);
                        }else{
                            portManagerTaskMenu(scanner,login.getPortNum(),login);
                        }
                        break;
                    default:
                        System.out.println("Invalid input. Please choose again.");
                        pressEnterKeyToContinue();
                }
            }
        }else{
            System.out.println("Adding menu:");
            System.out.println("1. Container");
            System.out.println("2. Back to previous Menu");
            System.out.print("Input: ");
            int addingChoice = scanner.nextInt();

            while (true){
                switch (addingChoice) {
                    case 1:
                        addingMenuContainer(scanner,login);
                        addingMenu(scanner,login);
                        break;
                    case 2:
                        addingMenu(scanner,login);
                        break;
                    default:
                        System.out.println("Invalid input. Please choose again.");
                        pressEnterKeyToContinue();
                }
            }
        }

    } // used for both admin and port manager
    // Deleting menu: Allows users to delete various entities
    private static void deletingMenu(Scanner scanner,login login) throws IOException, ClassNotFoundException { // used for both admin and port manager
        if(login.getRole().equals("Admin")){
            System.out.println("Deleting 메뉴:");
            System.out.println("1. Container");
            System.out.println("2. Truck");
            System.out.println("3. Ship");
            System.out.println("4. Port");
            System.out.println("5. User");
            System.out.println("6. Back To Previous Menu");
            System.out.print("Input: ");
            int addingChoice = scanner.nextInt();

            while (true){
                switch (addingChoice) {
                    case 1:
                        deletingMenuTask(scanner,"Container",login);
                        deletingMenu(scanner,login);
                        break;
                    case 2:
                        deletingMenuTask(scanner,"Truck",login);
                        deletingMenu(scanner,login);
                        break;
                    case 3:
                        // Trip management feature
                        deletingMenuTask(scanner,"Ship",login);
                        deletingMenu(scanner,login);
                        break;
                    case 4:
                        deletingMenuTask(scanner,"Port",login);
                        deletingMenu(scanner,login);
                        break;
                    case 5:
                        deletingMenuTask(scanner,"PortManager",login);
                        break;
                    case 6:
                        adminTaskMenu(scanner,login);
                    default:
                        System.out.println("Invalid input. Please choose again.");
                        pressEnterKeyToContinue();
                }
            }
        }else{
            System.out.println("Deleting Menu:");
            System.out.println("1. Container");
            System.out.println("2. Back to Previous Menu");
            String choice = scanner.next();
            while (true){
                switch (choice){
                    case "1": deletingMenuTask(scanner,"Container",login);
                        portManagerTaskMenu(scanner, login.getPortNum(),login);
                    case "2":
                        portManagerTaskMenu(scanner, login.getPortNum(),login);

                }
            }
        }
    }
    // Method to add a vehicle (Truck or Ship)
    private static void addingMenuVehicle(Scanner scanner, String type,login login) throws IOException {
        System.out.println("Enter the ID number");
        int idNumber = scanner.nextInt();
        System.out.println("Enter the capacity");
        double capacity = scanner.nextDouble(); // Handle non-Double values by returning to the previous menu
        if(login.getRole().equals("Admin")){
            if(type.equals("Truck")){
                System.out.println("Select the truck's type.");
                System.out.println("1. Basic");
                System.out.println("2. Reefer");
                System.out.println("3. Tanker");
                int typeNum = scanner.nextInt();
                switch (typeNum) {
                    case 1:
                        type = "basic";
                        break;
                    case 2:
                        type = "reefer";
                        break;
                    case 3:
                        type = "Tanker";
                        break;
                    default:
                }

                System.out.println("Type the port number you want to place.");
                int portNumber = scanner.nextInt();
                Truck truck = new Truck("Tr-"+idNumber,type,capacity,"Port"+portNumber);
            }else{
                System.out.println("Type the port number you want to place.");
                String portNumber = scanner.next();
                Ship ship = new Ship("Sh-"+idNumber,capacity,"Port"+portNumber);
            }
        }else{
            if(type.equals("Truck")){
                System.out.println("Select the truck's type. ");
                String truckType = scanner.next();
                Truck truck = new Truck("Tr-"+idNumber, type, capacity,login.getPortNum());
            }else{
                Ship ship = new Ship("Sh-"+idNumber,capacity,login.getPortNum());
            }


        }
    }
    private static void adminAddingMenuPort(Scanner scanner){
        System.out.println("Enter the name of port");
        String portName = scanner.next();
        System.out.println("Enter the ID number");
        int idNumber = scanner.nextInt();
        System.out.println("Enter the capacity");
        Double capacity = scanner.nextDouble(); // Handle non-Double values by returning to the previous menu
        System.out.println("Enter the port's Longitude");
        Double Longitude = scanner.nextDouble();
        System.out.println("Enter the port's Latitude");
        Double Latitude = scanner.nextDouble();
        System.out.println("Enter the port's landing ability. 0 - No, 1- Yes");
        int landingAbility = scanner.nextInt(); // Handle values other than 0 and 1
        boolean landing = true;
        if(landingAbility == 0){
            landing = false;
        }
        Port port = new Port(portName,"Port"+idNumber,capacity,Longitude,Latitude,landing);


    }
    // Method to add a container
    private static void addingMenuContainer(Scanner scanner,login login) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("Enter the number of containerID");
            int containerNum = scanner.nextInt();
            System.out.println("Select the Type of container");
            System.out.println("1.dry storage, 2.open top, 3.open side, 4.refrigerated, 5.liquid, 9.exit"); // 5이외의 값 혹은 String이 들어왔을때 대처
            int typeNum = scanner.nextInt();
            String type = null;
            switch (typeNum) {
                case 1:
                    type = "dry storage";
                    break;
                case 2:
                    type = "open top";
                    break;
                case 3:
                    type = "open side";
                    break;
                case 4:
                    type = "refrigerated";
                    break;
                case 5:
                    type = "liquid";
                    break;
                case 9:
                    addingMenu(scanner,login);
                    break;
                default:
                    System.out.println("Invalid input. Please choose again.");
                    addingMenuContainer(scanner,login);
            }
            System.out.println("Enter the weight");
            Double weight = scanner.nextDouble();
            if(login.getRole().equals("Admin")){
                System.out.println("Enter the port number you want to position");
                String portNum1 = scanner.next();
                Container container = new Container("C-" + containerNum, type, weight, "Port"+portNum1);
            }else{
                Container container = new Container("C-" + containerNum, type, weight, login.getPortNum());
            }
            break;
        }
    }
    // Method to sign up a new user (Admin or Port Manager)
    private static void signUp(Scanner scanner, login login) throws IOException, ClassNotFoundException {
        System.out.println("Please select the role. ");
        System.out.println("1. Admin ");
        System.out.println("2. PortManager");
        int addingChoice = scanner.nextInt();
        String username=null;
        String password = null;
        while (true){
            switch (addingChoice) {
                case 1:
                    System.out.println("Type the user name."); // Handle non-String values with exception handling
                    username = scanner.next();
                    System.out.println("Type the password."); // Handle non-String values with exception handling
                    password = scanner.next();
                    Admin admin1 = new Admin(username,password);
                    break;
                case 2:
                    System.out.println("Type the user name."); // Handle non-String values with exception handling
                    username = scanner.next();
                    System.out.println("Type the password."); // Handle non-String values with exception handling
                    password = scanner.next();
                    System.out.println("Type the port number want to set in.");
                    int num = scanner.nextInt();
                    PortManager portManager = new PortManager(username,password," Port"+num); // Handle cases where the specified port does not exist
                    break;
                default:
                    System.out.println("Please type available number.");
            }
            addingMenu(scanner,login);
        }

    }

    // Port Manager menu
    // Method for deleting entities (Container, Truck, Ship, Port, Port Manager)
    private static void deletingMenuTask(Scanner scanner, String type, login login) {
        Admin admin = new Admin();
        if(login.getRole().equals("Admin")){
            if(type.equals("Container")){
                System.out.println("Enter the port number (e.g 3)");
                int portNumber = scanner.nextInt();

                System.out.println("Enter the id number");
                int idNumber = scanner.nextInt();
                admin.deleteFile("Port"+portNumber,"C-"+idNumber+".txt");


            } else if (type.equals("Truck")) {
                System.out.println("Enter the port number (e.g 3): ");
                int portNumber = scanner.nextInt();

                System.out.println("Enter the Truck id number (e.g. 34): ");
                int idNumber = scanner.nextInt();
                admin.deleteFile("Port"+portNumber, "Tr-" + idNumber+".txt");
            } else if (type.equals("Ship")) {
                System.out.println("Enter the port number (e.g 3): ");
                int portNumber = scanner.nextInt();

                System.out.println("Enter the Ship id number (e.g. 34): ");
                int idNumber = scanner.nextInt();
                admin.deleteFile("Port"+portNumber, "Sh-" + idNumber+".txt");

            } else if (type.equals("Port")) {
                System.out.println("Enter the port number (e.g 3): ");
                int portNumber = scanner.nextInt();
                admin.deletePort("Port"+portNumber);



            } else if (type.equals("PortManager")) {

                System.out.println("Enter the user name: ");
                String userName = scanner.next();
                User user = new User();
                user.removePortManagerID(userName);


            }
        }else{

            System.out.println("Enter the id number (e.g. 23): ");
            String idNumber = scanner.nextLine();
            admin.deleteFile(login.getPortNum(), "C-"+idNumber);


        }


    }private static void pressEnterKeyToContinue(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter key to continue...");
        try {
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

