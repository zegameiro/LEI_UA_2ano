package Ex2;

public class Voo {

    private final String flightCode;
    private final Aviao aviao;
    private final int execCapacity;
    private final int turCapacity;
    private int reservation;

    /*
     * Constructor definition
     */
    public Voo(String codigo, Aviao aviao) {
        this.flightCode = codigo;
        this.aviao = aviao;

        this.execCapacity = aviao.executiveTotalCapacity();
        this.turCapacity = aviao.turistTotalCapacity();

        reservation = 0;
    }

    // Getters and Setters ------------------------------------------------
    public int getReserva() {
        return reservation;
    }

    public void setReserva(int r) {
        reservation = r;
    }
    // ---------------------------------------------------------------------

    /*
     * This function has the purpose to add a reservation to the flight
     * It returns true if the reservation was successful and false otherwise
     */
    public boolean addReservation(int place, Classe cl) {
        int[][] seats = null;

        if(cl == Classe.EXECUTIVE) {
            // Check if there are seats available
            if(aviao.executiveTotalCapacity() - aviao.executiveOccupied() < place)
                return false;
            else
             seats = aviao.getExecutive();
        } else {
            // Check if there are seats available
            if(aviao.turistTotalCapacity() - aviao.turistOccupied() < place)
                return false;
            else
                seats = aviao.getTurist();
        }

        // Get the number of rows and columns of the plain
        int nColumns = seats[0].length;
        int nRows = seats.length;

        int aux = 0;

        for(int i = 0; i < nColumns; i++) {
            // For the first queue to be empty then the first line needs to be empty
            if(seats[0][i] == '\0') {
                for(int j = 0; j < place ; j++) {
                    // Distribute the seats in the rows
                    if(i + j / nRows < nColumns) {
                        seats[j % nRows][i + j / nRows] = reservation;
                        aux++;
                    }
                }
                break;
            }
        }

        boolean flag;
        // If flag is true then the reservation was successful meaning that the aux equals the number of places occupied
        if(aux == place)
            flag = true;
        // Else the reservation was not successful, then the places will have to be distributed sequentially
        else    
            flag = false;

        if(flag == false){
            for(int i = 0; i < nColumns && flag == false ; i++) {
                for(int j = 0 ; j < seats.length && flag == false ; j++) {
                    if(seats[i][j] == '\0') {
                        seats[i][j] = reservation + 1;
                        aux++;

                        if(aux == place)
                            flag = true;
                    }
                }
            }
        }

        // Save the data of the reservation in the plain
        if(flag == true) {
            if(cl == Classe.TURIST)
                aviao.setTurist(seats);
            else
                aviao.setExecutive(seats);

            reservation++;
        }

        return flag;
    }

    /*
     * This function prints the information of the flight
     */
    public void printFlightInfo() {
        System.out.println("Flight Code: " + flightCode);
        
        if(execCapacity > 0) {
            System.out.println("Executive Class : ");
            System.out.println("   Total Capacity: " + execCapacity);
            System.out.println("   Occupied: " + aviao.executiveOccupied());
        } 

        System.out.println("Turist Class : ");
        System.out.println("   Total Capacity: " + turCapacity);
        System.out.println("   Occupied: " + aviao.turistOccupied());
        
        if(execCapacity == 0) {
            System.out.println("The Executive Class is not available in this flight");
        }
    }

    /*
     * This function prints a map of the flight's places
     */
    public void showMap() {
        int[][] executive = aviao.getExecutive();
        int[][] turist = aviao.getTurist();

        int nColumns = 0;
        int nRows = 0;

        if(execCapacity != 0) {
            if(turist.length >= executive.length) {
                nColumns = executive[0].length + turist[0].length;
                nRows = turist.length;
            }
        } else {
            nColumns = turist[0].length;
            nRows = turist.length;
        }

        // Print the numeration of the columns
        System.out.print("\t");
        for(int i = 0; i < nColumns; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();

        int letra = 65; // ASCII code for 'A'

        for(int i = 0; i < nRows; i++) {
            System.out.println((char)letra + " ");
            letra++;

            for(int j = 0; j < nColumns; j++) {
                if(execCapacity != 0 && j < executive[0].length) {
                    if(i < executive.length) {
                        if(executive[i][j] == '\0')
                            System.out.print("0\t");
                        else
                            System.out.print(executive[i][j] + "\t");
                    } else {
                        System.out.print("\t");
                    }
                } else { 
                    int x;
                    if(execCapacity != 0)
                        x = j - 1;
                    else
                        x = j;
                    
                    if(turist[i][x - executive.length] == '\0')
                        System.out.print("0");
                    else
                        System.out.print(turist[i][x - executive.length]);
                    System.out.print("\t");
                }
            }
            System.out.println();
        }

    }

    /*
     * This function gets the seat of the reservation
     */
    public void getSeat(int nReservation, Classe cl, int place) {
        int[][] seats;

        if(cl == Classe.EXECUTIVE)
            seats = aviao.getExecutive();
        else
            seats = aviao.getTurist();
        
        if(seats == null) 
            return;

        int x;

        if(execCapacity != 0)
            x = aviao.getExecutive()[0].length;
        else
            x = 0;
        
        for(int j = 0 ; j < seats[0].length; j++) {
            for(int i = 0; i < seats.length; i++) {
                if(seats[i][j] == nReservation) {
                    System.out.println(" " + (j + 1 + x) + (char)(i + 65) + " |");
                    return;
                }
            }
        }
    }

    /*
     * This function cancels a reservation
     */
    public boolean cancelReservation(int nReservation) {
        boolean found = false;

        if(execCapacity != 0) {
            int[][] executive = aviao.getExecutive();

            for(int j = 0; j < executive[0].length; j++) {
                for(int i = 0; i < executive.length; i++) {
                    if(executive[i][j] == nReservation) {
                        executive[i][j] = '\0';
                        found = true;
                    }
                }
            }
            aviao.setExecutive(executive);
        }

        if(found == false) {
            int[][] turist = aviao.getTurist();

            for(int j = 0; j < turist[0].length; j++) {
                for(int i = 0; i < turist.length; i++) {
                    if(turist[i][j] == nReservation) {
                        turist[i][j] = '\0';
                        found = true;
                    }
                }
            }
            aviao.setTurist(turist);
        }

        return found; 
    }

    public void refBanco(int reserva, Classe classe, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}    
