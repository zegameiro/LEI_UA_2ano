package lab10.Ex1;
 
import java.util.ArrayList;

public class Cliente implements Observer{

    private String name;
    private ArrayList<Produto> bidProducts;
    private ArrayList<Produto> auction;


    public Cliente(String name) {
        this.name = name;
        this.bidProducts = new ArrayList<Produto>();
        this.auction = new ArrayList<Produto>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Produto> getAuction() {
        return auction;
    }

    public ArrayList<Produto> getBidProducts() {
        return bidProducts;
    }

    public void addAuction(Produto p) {
        auction.add(p);
    }

    public void removeAuction(Produto p) {
        auction.remove(p);
    }

    public int bid(Produto p, double price) {
        int x = p.placeBid(this, price);

        if(x == 2) {
            bidProducts.add(p);
            return 0;

        } else if(x == 0) {
            System.out.println("ERROR: client " + name + " the product " + p.getDescription() + " is not in auction");
            return 0;
        }
        return 1;
    }

    public void consult() {
        System.out.println("Products in Auction:");
        System.out.println(getAuction());
    }

    @Override
    public void update(String notification) {
        System.out.println("Client " + name + " received a notification: " + notification);
    }
}
