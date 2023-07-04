package lab10.Ex1;

import java.util.ArrayList;

public class Gestor implements Observer{
    private String name;
    private ArrayList<Produto> stock_products;
    private ArrayList<Produto> auction_products;
    private ArrayList<Produto> sold_products;

    public Gestor(String name) {
        this.name = name;
        this.stock_products = new ArrayList<Produto>();
        this.auction_products= new ArrayList<Produto>();
        this.sold_products = new ArrayList<Produto>();
    }

    public String getName() {
        return name;
    }

    public void addProductStock(Produto product) {
        this.stock_products.add(product);
    }

    public void addProductAuction(Produto product) {
        this.auction_products.add(product);
    }

    public void addProductSold(Produto product) {
        this.sold_products.add(product);
    }

    public void removeProductStock(Produto product) {
        this.stock_products.remove(product);
    }

    public void removeProductAuction(Produto product) {
        this.auction_products.remove(product);
    }

    @Override
    public void update(String notification) {
        System.out.println("Manager " + getName() + " received notification: " + notification);
    }

    public void getProductsStock() {
        System.out.println("Products in Stock:");
        for (Produto produto : stock_products) {
            System.out.println(produto);
        }
        System.out.println();
    }

    public void getProductsAuction() {
        System.out.println("Products in Auction:");
        for (Produto produto : auction_products) {
            System.out.println(produto);
        }
    }

    public void getProductsSold() {
        System.out.println("Products Sold:");
        for (Produto produto : sold_products) {
            System.out.println(produto);
        }
    }

    public String toString() {
        return "Manager " + getName();
    }

}
