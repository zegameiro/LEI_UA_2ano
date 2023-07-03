package T1.Aeroplane;

import java.util.ArrayList;

public class Flight {
    private String flightNumber;
    private int[][] executiveSeats;
    private int availableExecutiveSeats;
    private int[] lastExecutiveSeat = new int[2];
    private int[][] TouristSeats;
    private int availableTouristSeats;
    private int[] lastTouristSeat = new int[2];
    private int totalCols;
    private int maxRows;
    int resNumber = 1;

    public Flight(String flightNumber, int executiveCols, int executiveRows, int TouristCols, int TouristRows) {
        this.flightNumber = flightNumber;
        if ((executiveCols > 0 && TouristRows <= 0) || (executiveCols <= 0 && executiveRows > 0)) {
            throw new IllegalArgumentException(
                    "Can not have positive and zero or negative combination of rows and columns");
        }
        this.executiveSeats = new int[executiveRows][executiveCols];
        if (TouristCols <= 0 || TouristRows <= 0) {
            throw new IllegalArgumentException("Can not have 0 or less columns or rows of Tourist seats");
        }
        this.TouristSeats = new int[TouristRows][TouristCols];
        this.availableExecutiveSeats = executiveRows * executiveCols;
        this.availableTouristSeats = TouristRows * TouristCols;
        this.totalCols = executiveCols + TouristCols;
        this.maxRows = Math.max(executiveRows, TouristRows);
    }

    public Flight(String flightNumber, int TouristCols, int TouristRows) {
        this(flightNumber, 0, 0, TouristCols, TouristRows);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int[][] getExecutiveSeats() {
        return executiveSeats;
    }

    public int[][] getTouristSeats() {
        return TouristSeats;
    }

    public int getAvailableExecutiveSeats() {
        return availableExecutiveSeats;
    }

    public int getAvailableTouristSeats() {
        return availableTouristSeats;
    }

    public int getTotalCols() {
        return totalCols;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void reserveExecutiveSeat(int numberOfSeats, boolean file) {
        if (numberOfSeats > availableExecutiveSeats || numberOfSeats <= 0) {
            return;
        }
        int reserve = resNumber++;
        int originalNumberOfSeats = numberOfSeats;
        int col = lastExecutiveSeat[1];
        if (col >= executiveSeats[0].length) {
            col = 0;
        }

        ArrayList<int[]> reservedSeats = new ArrayList<>(numberOfSeats);
        for (int i = col; i < executiveSeats[0].length; i++) {
            for (int j = 0; j < executiveSeats.length; j++) {
                if (executiveSeats[j][i] == 0) {
                    executiveSeats[j][i] = reserve;
                    numberOfSeats--;
                    availableExecutiveSeats--;
                    reservedSeats.add(new int[] { i + 1, j + 1 });
                    if (numberOfSeats == 0) {
                        lastExecutiveSeat[0] = j + 1;
                        lastExecutiveSeat[1] = i + 1;

                        if (!file) {
                            StringBuilder seats = new StringBuilder();
                            for (int[] seat : reservedSeats) {
                                seats.append(seat[0]).append((char) (seat[1] + 64))
                                        .append(reservedSeats.indexOf(seat) != reservedSeats.size() - 1 ? " | " : "");
                            }
                            System.out.printf("%s:%d = %s\n", flightNumber, reserve, seats);
                        }
                        return;
                    }
                }
            }
            if (numberOfSeats > availableExecutiveSeats && availableExecutiveSeats == 0) {
                System.out.println("Unable to obtain seats for reservation E " + originalNumberOfSeats);
                return;
            }
        }
    }

    public void reserveTouristSeat(int numberOfSeats, boolean file) {
        int reserve = resNumber++;
        int originalNumberOfSeats = numberOfSeats;
        int col = lastTouristSeat[1];
        if (col >= TouristSeats[0].length) {
            col = 0;
        }

        ArrayList<int[]> reservedSeats = new ArrayList<>(numberOfSeats);
        for (int i = col; i < TouristSeats[0].length; i++) {
            for (int j = 0; j < TouristSeats.length; j++) {
                if (TouristSeats[j][i] == 0) {
                    TouristSeats[j][i] = reserve;
                    numberOfSeats--;
                    availableTouristSeats--;
                    reservedSeats.add(new int[] { i + 1, j + 1 });
                    if (numberOfSeats == 0) {
                        lastTouristSeat[0] = j + 1;
                        lastTouristSeat[1] = i + 1;

                        if (!file) {
                            StringBuilder seats = new StringBuilder();
                            for (int[] seat : reservedSeats) {
                                seats.append(seat[0] + executiveSeats[0].length).append((char) (seat[1] + 64))
                                        .append(reservedSeats.indexOf(seat) != reservedSeats.size() - 1 ? " | " : "");
                            }
                            System.out.printf("%s:%d = %s\n", flightNumber, reserve, seats);
                        }
                        return;
                    }
                }
            }
            if (numberOfSeats > availableTouristSeats && availableTouristSeats == 0) {
                System.out.println("Unable to obtain seats for reservation T " + originalNumberOfSeats);
                return;
            }
        }
    }

    public void addReservation(String type, int numberOfSeats, boolean file) {
        if (type.equals("E")) {
            reserveExecutiveSeat(numberOfSeats, file);
        } else if (type.equals("T")) {
            reserveTouristSeat(numberOfSeats, file);
        } else {
            System.out.println("Invalid type of seat");
        }
    }

    public void removeReservation(int nRes) {
        for (int i = 0; i < executiveSeats.length; i++) {
            for (int j = 0; j < executiveSeats[i].length; j++) {
                if (executiveSeats[i][j] == nRes) {
                    executiveSeats[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < TouristSeats.length; i++) {
            for (int j = 0; j < TouristSeats[i].length; j++) {
                if (TouristSeats[i][j] == nRes) {
                    TouristSeats[i][j] = 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        StringBuilder seats = new StringBuilder();
        for (int i = 1; i <= totalCols; i++) {
            line.append(String.format("%2d", i)).append(" ");
        }
        for (int i = 0; i < maxRows; i++) {
            seats.append((char) (i + 65)).append(" ");
            try {
                for (int j = 0; j < executiveSeats[i].length; j++) {
                    seats.append(String.format("%2d", executiveSeats[i][j])).append(" ");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (executiveSeats.length > 0) {
                    seats.append(" ".repeat(Math.max(0, 3 * executiveSeats[0].length)));
                }
            }
            try {
                for (int j = 0; j < TouristSeats[i].length; j++) {
                    seats.append(String.format("%2d", TouristSeats[i][j])).append(" ");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                seats.append("\n");
                continue;
            }
            seats.append("\n");
        }

        return "  " + line + "\n" + seats;
    }
}
