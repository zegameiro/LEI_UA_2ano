package lab10.Ex1;

import java.util.ArrayList;

public class Ex1 {
    public static void main(String[] args) {
        Gestor manager = new Gestor("Diogo");
        Cliente client1= new Cliente("José");
        Cliente client2 = new Cliente("João");
        Cliente client3 =new Cliente("Diana");
        Cliente clientes[]={client1,client2,client3};


        Produto p1 = new Produto("Computer", 1499.59, manager);
        Produto p2 = new Produto("Trip to Maldivas", 532.58, manager);
        Produto p3 = new Produto("Mercedes EQC", 2832.21, manager);
        Produto p4 = new Produto("Scooter", 745.96, manager);
        Produto p5 = new Produto("Ticket to Green Day concert", 94.00, manager);

        ArrayList<Produto> products = new ArrayList<Produto>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);

        System.out.println("Start auction");
        for(Produto p : products){
            p.startAuction(5);
            for (Cliente c: clientes){
                c.addAuction(p);
            }
        }
        System.out.println("-----------------------------------------------------------------------");

        System.out.println();

        System.out.println("Information consult by manager");
        manager.getProductsStock();
        manager.getProductsAuction();
        System.out.println("-----------------------------------------------------------------------");

        System.out.println();

        System.out.println("Information consult by client");
            client2.consult();
        System.out.println("-----------------------------------------------------------------------");

        System.out.println();

        System.out.println("Bidings");
        client1.bid(p5, 100.76);
        client2.bid(p5, 89.09); // solicitação nao aceite, mais baixa do que esta
        client3.bid(p2, 487.32); // solicitação nao aceite, mais baixa do que seu valor
        client2.bid(p2, 550.00);
        System.out.println("-----------------------------------------------------------------------");

        System.out.println("Check if auction has ended");
        //so na verificação é que é possivel remover os produtos que nao tinham sido removidos da lista de leilao, do cliente, do gestor
        for(Produto p : products){
            if(p.checkAuctionEnd()){
                for (Cliente c: clientes){
                    c.removeAuction(p);
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        manager.getProductsAuction();
        client1.consult();
    }
}