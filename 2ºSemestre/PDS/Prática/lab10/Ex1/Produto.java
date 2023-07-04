package lab10.Ex1;

import java.util.HashMap;
import java.util.Map;

public class Produto {
    private String description;
    private double initialPrice;
    private int id;
    private static int counter = 0;
    public Gestor manager;

    private double newPrice;
    private Estados state;
    private double auctionStart;
    private double auctionTime;

    private HashMap<Cliente, Double> bids;

    public Produto(String description, double initialPrice, Gestor manager) {
        this.description = description;
        this.initialPrice = initialPrice;
        this.id = counter++;
        this.state = Estados.STOCK;
        this.auctionTime = 0;
        this.newPrice = initialPrice;
        this.manager = manager;
        this.bids = new HashMap<Cliente, Double>();
    }

    public void startAuction(double auctionTime) {
        this.auctionTime = auctionTime;
        this.auctionStart = System.currentTimeMillis();
        this.state = Estados.AUCTION;
        manager.addProductAuction(this);
        manager.removeProductStock(this);
        manager.update("The product " + description + " has entered the auction");

    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Estados getState() {
        return state;
    }

    public void setNewPrice(double p) {
        newPrice = p;
    }

    public boolean checkAuctionEnd() {
        double currentTime = System.currentTimeMillis();
        double timeBid = currentTime - auctionStart;
        if (timeBid > auctionTime) {
            if(state == Estados.AUCTION) 
                return true;

            return true;
        }
        return false;
    }

    public int placeBid(Cliente client, double price) {
        double currentTime = System.currentTimeMillis();
        double timeBid = currentTime - auctionStart;
        if(state == Estados.AUCTION) {
            if(timeBid > auctionTime) {
                endAuction();
                return 1;
            } else {
                if(price > newPrice) {
                    bids.put(client,price);
                    setNewPrice(price);

                    manager.update("An new offer of " + price + " € has been made on the product " + description + " by the client" + client.getName());

                    for( Map.Entry<Cliente, Double> x : bids.entrySet())
                        x.getKey().update("A new offer of " + price + " € has been made on the product" + description);
        
                    return 2;
                } else {
                    manager.update("An new offer of " + price + " € has been made on the product " + description + " by the client" + client.getName());
                    client.update("WARNING: low biding for the product" + description + ", its value is currently + " + newPrice + "€");
                    return 1;
                }
            }
        }
        return 0;
    }

    public void endAuction() {
        if(bids.size() == 0) {
            state = Estados.STOCK;
            manager.addProductStock(this);
            manager.removeProductAuction(this);

            manager.update("The product" + description + "wasn't sold on the auction, it will return to stock products");

        } else {
            if(newPrice > initialPrice) {
                state = Estados.SOLD;
                manager.removeProductAuction(this);
                manager.addProductSold(this);

                manager.update("The product " + description + " has been sold for " + newPrice + " €");
            }

            state = Estados.STOCK;
            manager.addProductStock(this);
            manager.removeProductAuction(this);

            manager.update("The product" + description + "was not sold on the auction, it will return to stock products");

        }

        for(Map.Entry<Cliente, Double> x : bids.entrySet()) {
            bids.put(x.getKey(),x.getValue());
            System.out.println("Removed " + x.getKey().getName() + " from the auction");
        }
    }

    public String toString() {
        return getDescription() + "\n" +
               "   Initial Price: " + getInitialPrice() + "\n" +
               "   Manager: " + manager.toString() + "\n" +
               "   Maximum Bid: " + getNewPrice() + "\n" +
               "   Number of clients interested: " + bids.size();
    }

}
