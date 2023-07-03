package lab05.Ex3;

public class Place {

    // Atributs
    private String city;

    // Constructor
    public Place(String city) {
        this.city = city;
    }

    // Get method
    public String getCity() {
        return city;
    }

    // Set method
    public void setCity(String c) {
        city = c;
    }

    public String toString() {
        return getCity();
    }
    
}
