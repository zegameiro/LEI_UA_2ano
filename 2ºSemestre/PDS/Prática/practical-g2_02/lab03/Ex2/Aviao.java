package Ex2;

public class Aviao {

    private int[][] executive;
    private int[][] turist;

    /*
     * Constructor definition
     */
    public Aviao(int[][] executive, int[][] turist) {
        this.executive = executive;
        this.turist = turist;
    }

    // Getters and Setters ------------------------------------------------
    public int[][] getExecutive() {
        return executive;
    }

    public void setExecutive(int[][] exec) {
        executive = exec;
    }

    public int[][] getTurist() {
        return turist;
    }

    public void setTurist(int[][] tur) {
        turist = tur;
    }
    // --------------------------------------------------------------------

    /*
     * This function returns the number of total seats in the executive class
     */
    public int executiveTotalCapacity() {
        if(executive.length == 0) 
            return 0;
        else 
            return executive.length * executive[0].length;
    }

    /*
     * This function returns the number of total seats in the turist class
     */
    public int turistTotalCapacity() {
        if(turist.length == 0) 
            return 0;
        else 
            return turist.length * turist[0].length;
    }

    /*
     * This function returns the number os seats occupied in the executive class
     */
    public int executiveOccupied() {
        int count = 0;
        for(int i = 0 ; i < executive.length ; i++) {
            for(int j = 0 ; j < executive[0].length ; j++) {
                if(executive[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * This function returns the number os seats occupied in the turist class
     */
    public int turistOccupied() {
        int count = 0;
        for(int i = 0 ; i < turist.length ; i++) { // Run through all the rows
            for(int j = 0 ; j < turist[0].length ; j++) { // Run through all the columns
                if(turist[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }
}