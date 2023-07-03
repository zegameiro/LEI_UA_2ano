package Ex2;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class UserInterface {

    static HashMap<String, Voo> flights = new HashMap<String, Voo>();

    private String[] options;
    private String command;

    public UserInterface(String command, String[] options) {
        this.options = options;
        this.command = command;
    }

    // Getters and Setters ------------------------------------------------
    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] opt) {
        options = opt;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String cmd) {
        command = cmd;
    }

    /*
     * This function is the main function of the program.
     * It interacts with the user and calls the functions
     * that process the user's input.
     */
    public void interact() {
        Scanner sc = new Scanner(System.in);
        do {
            showMenu();
            command = sc.nextLine();
            options = command.split(" ");
            command = options[0];
            setCommand(command);
            setOptions(options);
            processOption();
        } while (command != "0");

        sc.close();
    }

    /*
     * This function shows the menu of the program.
     */
    public void showMenu() {
        System.out.printf("Choose an option: \n" + 
                          "H - Help \n" + 
                          "I - Input a file with information of a file \n" +
                          "M - Display the information of a flight \n" +
                          "F - Add a new flight \n" + 
                          "R - Make a reservation \n" + 
                          "C - Cancel a reservation \n" + 
                          "Q - Quit the program \n" +
                          "  Option: \n");
    }

    /*
     * This function processes the option chosen by the user.
     *  It calls diferent functions depending on the user's choice
     * It checks if the user's input is correct depending on the choice
     */
    public void processOption() {
        switch(getCommand()) {
            case "H": // Consult Menu
                // The format of this argument must be only the char H
                if(getOptions().length != 1) {
                    System.out.println("ERROR: Invalid number of arguments.");
                    System.exit(1);
                }
                showMenu();
                break;
            case "I": // Read file
                // The format of this argument must be I <filename.txt>
                System.out.println(getOptions().length);
                if(getOptions().length != 2) {
                    System.out.println("ERROR: Invalid number of arguments.");
                    System.exit(1);
                }
                String filename = options[1];
                getData(filename);
                System.out.println();
                break;

            case "M": // Show map of the plane
                // The format of this argument must be M <Flight code>
                if(getOptions().length != 2) {
                    System.out.println("ERROR: Invalid number of arguments.");
                    System.exit(1);
                }

                String code = options[1];
                Voo voo = flights.get(code);

                if(voo == null) {
                    System.out.println("ERROR: Flight does not exist.");
                    System.exit(1);
                }
                voo.showMap();
                System.out.println();
                break;

            case "F": // Add a new flight
                // The format of this option must be F <Flight code> <Executive Rows>x<Executive Columns> <Turistic Rows>x<Executive>
                // Or F <Flight Code> <Turist Rows>x<Turist Columns>
                if(getOptions().length != 3 && getOptions().length != 4) {
                    System.out.println("ERROR: Invalid number of arguments.");
                    System.exit(1);
                }
                newFlight();
                System.out.println();
                break;

            case "R": // Add a new reservation
                // The format of this option must be R <Flight Code> <Class> <Number of seats>
                if(getOptions().length != 4) {
                    System.out.println("ERROR: Invalid number of arguments.");
                    System.exit(1);
                }

                newReservation();
                System.out.println();
                break;

            case "C": // Cancel a reservation
                // The format of this option must be C <Reservation_Code>
                if(getOptions().length != 2) {
                    System.out.println("ERROR: Invalid number of arguments.");
                    System.exit(1);
                }
                
                removeReservation();
                System.out.println("Reservation canceled.");
                break;
            case "Q": // Exit the program
                // The format of this option is only the char Q
                System.out.println("End of program.");
                System.exit(0);
            default: // Invalid option
                System.out.println("ERROR: Invalid option.");
                System.out.println();
        }
    }

    /*
     * This function is for option 'C'
     */
    public void removeReservation() {
        String[] info = options[1].split(":");
        String code = info[0];

        if(!(flights.containsKey(code))) {
            System.out.println("ERROR: Flight does not exist.");
            System.exit(1);
        }
        Voo voo = flights.get(code);
        int reservationNum = 0;

        try {
            reservationNum = Integer.parseInt(info[1]);
        } catch(NumberFormatException e) {
            System.out.println("ERROR: Invalid reservation number.");
            System.exit(1);
        }

        boolean removed = voo.cancelReservation(reservationNum);

        if(!removed) {
            System.out.println("ERROR: Reservation does not exist.");
            System.exit(1);
        }
    }

    public void getData(String fileName) {

        File file = new File(fileName);

        try{
            // Read file from terminal
            Scanner scanner = new Scanner(file);
            String[] flightInfo = scanner.nextLine().split("\\s+");

            // First line
            // Check if the first character is a '>' and has 3 more parameters
            if(flightInfo[0].charAt(0) != '>' || flightInfo.length != 3) {
                System.out.println("ERROR: Missing '>' in first line");
                System.exit(1);
            }

            // First line format:
            // <code> <executive> <turist>
            // It can also be
            // <code> <turist>

            String code = flightInfo[0].substring(1);
            if(flights.containsKey(code)) {
                System.out.println("ERROR: Flight already exists.");
                System.exit(1);
            }

            int executiveRows = 0;
            int executiveSeats = 0;
            int turindex;

            if(flightInfo.length == 3) {
                String[] execInfo = flightInfo[1].split("x");
                executiveRows = Integer.parseInt(execInfo[1]);
                executiveSeats = Integer.parseInt(execInfo[0]);
                turindex = 2;
            } else 
                turindex = 1;
            
            // Turist class
            int turistRows = 0;
            int turistSeats = 0;
            String[] turistInfo = flightInfo[turindex].split("x");
            turistRows = Integer.parseInt(turistInfo[0]);
            turistSeats = Integer.parseInt(turistInfo[1]);

            // Create a new flight
            int[][] executive = new int[executiveRows][executiveSeats];
            int[][] turist = new int[turistRows][turistSeats];

            Aviao aviao = new Aviao(executive, turist);
            Voo voo = new Voo(code, aviao);

            flights.put(code, voo);
            voo.printFlightInfo();

            ArrayList<String> reservations = new ArrayList<String>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineInfo = line.split(" ");

                boolean valid = addReservation(code, lineInfo);

                if(!valid && lineInfo.length == 2) {
                    reservations.add(lineInfo[0] + " " + lineInfo[1]);
                }
                
            }

            scanner.close();
            System.out.println("Reservations not made: ");
            for(String reservation : reservations) {
                System.out.println(reservation + "; ");
            }

        } catch(FileNotFoundException e) {
            System.out.println("ERROR: File not found");
            System.exit(1);
        }
    }

    /*
     * This function is for option 'F'
     */
    public void newFlight() {
        String code = options[1];
        if(flights.containsKey(code)) {
            System.out.println("ERROR: Flight already exists.");
            System.exit(1);
        }

        int executiveRows = 0;
        int executiveSeats = 0;
        int turindex;

        if(options.length == 4) {
            String[] execInfo = options[2].split("x");
            executiveRows = Integer.parseInt(execInfo[2]);
            executiveSeats = Integer.parseInt(execInfo[0]);
            turindex = 3;
        } else 
            turindex = 2;
        
        // Turist class
        int turistRows = 0;
        int turistSeats = 0;
        String[] turistInfo = options[turindex].split("x");
        turistRows = Integer.parseInt(turistInfo[0]);
        turistSeats = Integer.parseInt(turistInfo[1]);

        // Create a new flight
        int[][] executive = new int[executiveRows][executiveSeats];
        int[][] turist = new int[turistRows][turistSeats];

        Aviao aviao = new Aviao(executive, turist);
        Voo voo = new Voo(code, aviao);

        flights.put(code, voo);
        voo.printFlightInfo();
    }

    /*
     * This function is for option 'R'
     */
    public void newReservation() {
        String code = options[1];
        boolean valid = addReservation(code, new String[]{options[2], options[3]});
        if(!valid) {
            System.out.println("ERROR: Reservation not made.");
            System.exit(1);
        }

        Voo voo = flights.get(code);

        Classe classe = newClasse(options[2]);

        if(classe == Classe.NONE) {
            System.out.println("ERROR: Invalid class.");
            System.exit(1);
        }

        System.out.print(code + " " + voo.getReserva() + " = ");
        voo.getSeat(voo.getReserva(), classe, Integer.parseInt(options[3]));
    }

    /*
     * This function validates a reservation
     */
    public boolean addReservation(String code, String[] options) {
        Classe classe = newClasse(options[0]);
        if(classe == Classe.NONE) 
            return false;
        
        boolean madeReservation = false;

        if(flights.containsKey(code)) 
            madeReservation = flights.get(code).addReservation(Integer.parseInt(options[1]), classe);
        
        return madeReservation;
    }

    /*
     * This function creates a new class
     */
    public Classe newClasse(String option) {
        if(option.equals("E"))
            return Classe.EXECUTIVE;
        else if(option.equals("T"))
            return Classe.TURIST;
        else
            return Classe.NONE;
    }
}