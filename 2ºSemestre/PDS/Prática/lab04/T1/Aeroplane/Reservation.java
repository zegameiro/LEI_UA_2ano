package T1.Aeroplane;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Reservation {

    public static void main(String[] args) {
        ArrayList<Flight> flights = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command (H for help): ");
            String command = sc.nextLine();
            if (command.equals("H")) {
                // formato de comentário, e não string!
                // Corrigido
                String helpString = " H - Help \n " +
                       "I <filename> - Import flight data from file \n" + 
                       "M <flight_code> - Display flight reservation map \n" + 
                       "F <flight_code> <executive_ColsxRows> <tourist_ColsxRows> - Adds a new flight and it's seat map \n " + 
                       "R <flight_code> <class> <number_seats> - Adds a new reservation to a flight \n " +
                       "C <reservation_code> - Cancels a reservation \n" + 
                       "Q - Quit";
                System.out.println(helpString);
            }
            if (command.startsWith("I")) {
                // Check if the command is I <filename>
                String[] split = command.split(" ");
                if (split.length != 2) {
                    System.out.println("Invalid command");
                    continue;
                }
                // Import the file
                String filename = split[1];
                System.out.println("Importing flight data from file " + filename);
                Scanner fileScanner;
                try {
                    fileScanner = new Scanner(new File(filename));
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                    continue;
                }
                // Check if line starts with >
                String line = fileScanner.nextLine();
                char firstChar = line.charAt(0);
                if (firstChar != '>') {
                    System.out.println("File must start with >");
                    continue;
                }
                line = line.substring(1);
                // Check if line has the 3 required fields
                String[] parts = line.split(" ");
                Flight parsed;
                if (parts.length == 2) {
                    String flightCode = parts[0];
                    int[] seats = seatSplit(parts[1]);
                    parsed = new Flight(flightCode, seats[0], seats[1]);
                    flights.add(parsed);
                } else if (parts.length != 3) {
                    System.out.println("Invalid line (format must be <flight_code> <ColxRow> <ColxRow>)");
                    continue;
                } else {
                    // Parse the relevant parts
                    String flightCode = parts[0];
                    int[] seats = seatSplit(parts[1], parts[2]);
                    parsed = new Flight(flightCode, seats[0], seats[1], seats[2], seats[3]);
                }
                flights.add(parsed);
                // Read the reservations
                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine();
                    // Validate the reservation
                    String[] reservationParts = line.split(" ");
                    if (reservationParts.length != 2) {
                        System.out.println("Invalid line (format must be <class> <number_seats>)");
                        continue;
                    }
                    String reservationClass = reservationParts[0];
                    int reservationSeats = Integer.parseInt(reservationParts[1]);
                    // Add the reservation
                    parsed.addReservation(reservationClass, reservationSeats, true);
                    // Check if there's enough seats
                    if (reservationClass.equals("E")) {
                        if (parsed.getAvailableExecutiveSeats() == 0) {
                            System.out.println("Not enough executive seats, skipping reservation");
                        }
                    }
                    if (reservationClass.equals("T")) {
                        if (parsed.getAvailableTouristSeats() == 0) {
                            System.out.println("Not enough tourist seats, skipping reservation");
                        }
                    }
                }
                fileScanner.close();
            }
            if (command.startsWith("M")) {
                // Check if the command is M <flight_code>
                String[] split = command.split(" ");
                if (split.length != 2) {
                    System.out.println("Invalid command");
                    continue;
                }
                // Find the flight
                String flightCode = split[1];
                Flight flight = null;
                for (Flight f : flights) {
                    if (f.getFlightNumber().equals(flightCode))
                        flight = f;
                    break;
                }
                if (flight == null) {
                    System.out.println("Flight not found");
                    continue;
                }
                // Print the flight
                System.out.println(flight);
            }
            if (command.startsWith("F")) {
                // Check if the command is F <flight_code> <executive_ColsxRows>
                // <tourist_ColsxRows>
                String[] split = command.split(" ");
                if (split.length != 4) {
                    System.out.println("Invalid command");
                    continue;
                }
                // Parse the relevant parts
                String flightCode = split[1];
                int[] seats = seatSplit(split[2], split[3]);
                // Create the flight
                Flight flight = new Flight(flightCode, seats[0], seats[1], seats[2], seats[3]);
                flights.add(flight);
            }
            if (command.startsWith("R")) {
                // Check if the command is R <flight_code> <class> <number_seats>
                String[] split = command.split(" ");
                if (split.length != 4) {
                    System.out.println("Invalid command");
                    continue;
                }
                // Find the flight
                String flightCode = split[1];
                Flight flight = null;
                for (Flight f : flights) {
                    if (f.getFlightNumber().equals(flightCode))
                        flight = f;
                    break;
                }
                if (flight == null) {
                    System.out.println("Flight not found");
                    continue;
                }
                // Add the reservation
                String reservationClass = split[2];
                int reservationSeats = Integer.parseInt(split[3]);
                flight.addReservation(reservationClass, reservationSeats, false);
            }
            if (command.startsWith("C")) {
                // Check if the command is C <reservation_code>
                String[] split = command.split(" ");
                if (split.length != 2) {
                    System.out.println("Invalid command");
                    continue;
                }
                // Get parts
                String[] parts = split[1].split(":");
                String flightCode = parts[0];
                int seqReservation = Integer.parseInt(parts[1]);
                // Find the flight
                Flight flight = null;
                for (Flight f : flights) {
                    if (f.getFlightNumber().equals(flightCode))
                        flight = f;
                    break;
                }
                if (flight == null) {
                    System.out.println("Flight not found");
                    continue;
                }
                // Cancel the reservation
                flight.removeReservation(seqReservation);
            }
            if (command.equals("Q")) {
                System.out.println("Goodbye!");
                sc.close();
                break;
            }
        }
    }

    public static int[] seatSplit(String tourist) {
        String[] touristParts = tourist.split("x");
        int touristCols = Integer.parseInt(touristParts[0]);
        int touristRows = Integer.parseInt(touristParts[1]);
        int[] seats = { touristCols, touristRows };
        return seats;
    }

    public static int[] seatSplit(String exec, String tourist) {
        String[] execParts = exec.split("x");
        String[] touristParts = tourist.split("x");
        int execCols = Integer.parseInt(execParts[0]);
        int execRows = Integer.parseInt(execParts[1]);
        int touristCols = Integer.parseInt(touristParts[0]);
        int touristRows = Integer.parseInt(touristParts[1]);
        int[] seats = { execCols, execRows, touristCols, touristRows };
        return seats;
    }
}