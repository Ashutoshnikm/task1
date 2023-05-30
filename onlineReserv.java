package task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class onlineReserv {


	
	    // Database to store user credentials and reservations
	    private static Map<String, String> users = new HashMap<>();
	    private static Map<String, Reservation> reservations = new HashMap<>();

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Populate some dummy data
	        users.put("user1", "password1");
	        users.put("user2", "password2");

	        while (true) {
	            System.out.println("Welcome to the Online Reservation System!");
	            System.out.println("1. Login");
	            System.out.println("2. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    login(scanner);
	                    break;
	                case 2:
	                    System.out.println("Thank you for using the Online Reservation System. Goodbye!");
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    private static void login(Scanner scanner) {
	        System.out.print("Enter your login ID: ");
	        String loginId = scanner.nextLine();
	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();

	        if (users.containsKey(loginId) && users.get(loginId).equals(password)) {
	            System.out.println("Login successful!");
	            reservationSystem(scanner);
	        } else {
	            System.out.println("Invalid login ID or password. Please try again.");
	        }
	    }

	    private static void reservationSystem(Scanner scanner) {
	        System.out.println("Welcome to the Reservation System!");
	        System.out.print("Enter your basic details: ");
	        String basicDetails = scanner.nextLine();
	        System.out.print("Enter the train number: ");
	        String trainNumber = scanner.nextLine();
	        System.out.print("Enter the class type: ");
	        String classType = scanner.nextLine();
	        System.out.print("Enter the date of journey: ");
	        String dateOfJourney = scanner.nextLine();
	        System.out.print("Enter the origin: ");
	        String origin = scanner.nextLine();
	        System.out.print("Enter the destination: ");
	        String destination = scanner.nextLine();

	        // Create a new Reservation object
	        Reservation reservation = new Reservation(basicDetails, trainNumber, classType, 
	        		dateOfJourney, origin, destination);

	        // Store the reservation in the database
	        reservations.put(reservation.getPNR(), reservation);

	        System.out.println("Reservation successful!");
	        System.out.println("Your PNR number is: " + reservation.getPNR());
	    }

	    private static void cancellationForm(Scanner scanner) {
	        System.out.print("Enter your PNR number: ");
	        String pnrNumber = scanner.nextLine();

	        if (reservations.containsKey(pnrNumber)) {
	            Reservation reservation = reservations.get(pnrNumber);
	            System.out.println("Reservation details for PNR " + pnrNumber + ":");
	            System.out.println(reservation.toString());

	            System.out.print("Press 'OK' to confirm cancellation: ");
	            String confirmation = scanner.nextLine();

	            if (confirmation.equalsIgnoreCase("OK")) {
	                reservations.remove(pnrNumber);
	                System.out.println("Reservation with PNR " + pnrNumber + " has been cancelled.");
	            } else {
	                System.out.println("Cancellation aborted.");
	            }
	        } else {
	            System.out.println("Invalid PNR number. Please try again.");
	        }
	    }

	    // Class to represent a reservation
	    static class Reservation {
	        private static int counter = 0;
	        private String pnr;
	        private String basicDetails;
	        private String trainNumber;
	        private String classType;
	        private String dateOfJourney;
	        private String origin;
	        private String destination;

	        public Reservation(String basicDetails, String trainNumber, String classType, 
	        		String dateOfJourney, String origin, String destination) {
	            this.pnr = generatePNR();
	            this.basicDetails = basicDetails;
	            this.trainNumber = trainNumber;
	            this.classType = classType;
	            this.dateOfJourney = dateOfJourney;
	            this.origin = origin;
	            this.destination = destination;
	        }

	        public String getPNR() {
	            return pnr;
	        }

	        private String generatePNR() {
	            counter++;
	            return "PNR" + counter;
	        }

	        @Override
	        public String toString() {
	            return "PNR: " + pnr +
	                    "\nBasic Details: " + basicDetails +
	                    "\nTrain Number: " + trainNumber +
	                    "\nClass Type: " + classType +
	                    "\nDate of Journey: " + dateOfJourney +
	                    "\nOrigin: " + origin +
	                    "\nDestination: " + destination;
	        }
	    }
	}


