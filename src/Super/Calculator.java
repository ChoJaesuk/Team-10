/*COSC2081 GROUP ASSIGNMENT
CONTAINER PORT MANAGEMENT SYSTEM
Instructor: Mr. Minh Vu & Dr. Phong Ngo
Group 10
S3914532, Cho Jaesuk
S3912055, Han Yeeun
S3940575, Cho Jimin
S3847581, Yoon Taesung*/

package Super;

import UserInterface.Users.User;

public class Calculator {
    public static double calculateDistance(String DeparturePortNum, String ArrivalPortNum, Vehicle vehicle) {
        User user = new User();

        Port portA = user.searchingPort(DeparturePortNum);
        Port portB = user.searchingPort(ArrivalPortNum);

        double lat1 = Math.toRadians(portA.getLatitude());
        double lon1 = Math.toRadians(portA.getLongitude());
        double lat2 = Math.toRadians(portB.getLatitude());
        double lon2 = Math.toRadians(portB.getLongitude());

/*        // The code for debugging
        System.out.println("Departure Port Lat: " + portA.getLatitude());
        System.out.println("Departure Port Lon: " + portA.getLongitude());
        System.out.println("Arrival Port Lat: " + portB.getLatitude());
        System.out.println("Arrival Port Lon: " + portB.getLongitude());

        System.out.println("lat1: " + lat1);
        System.out.println("lon1: " + lon1);
        System.out.println("lat2: " + lat2);
        System.out.println("lon2: " + lon2);*/

        // Calculate the distance between two points using the Haversine formula
        double r = 6371; // basic radius

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = r * c;

        if (distance < 0.0) {
            distance = 0.0; // Negative distance is treated as zero
        }

        // Formulate distances to four decimal places
        String formattedDistance = String.format("%.4f", distance);
        // Convert formalized distance to double and return
        return Double.parseDouble(formattedDistance);
    }

    private static double getDefaultRadius(Vehicle vehicle) {
        if (vehicle instanceof Ship) {
            return 2.34;
        } else {
            return 6371; // basic radius
        }
    }

    private static Port getCurrentPort(Vehicle vehicle) {
        // Implement logic to get the vehicle's current port
        // Returns the current port or null if unavailable
        return null;
    }

}
