package lab11.Ex3;

import java.util.Scanner;
import java.util.ArrayList;

public class Ex3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        Livro l1 = new Livro("Java Anti-Stress", "Omodionah", "123", 2010);
        Livro l2 = new Livro("A Guerra dos Padrões", "Jorge Omel", "456", 2011);
        Livro l3 = new Livro("A Procura da Luz", "Khumatkli", "789", 2012);

        ArrayList<Livro> livros = new ArrayList<Livro>();

        livros.add(l1);
        livros.add(l2);
        livros.add(l3);

        System.out.println("*** Biblioteca ***");

        for(int i = 0; i < livros.size(); i++) {
            int book_number = i + 1;
            System.out.println(book_number + "     " + livros.get(i));
        }

        System.out.println(">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela; (6)terminar;");

        System.out.println();

        String choice;
        int book, option;


        do {
            System.out.print(">> ");
            choice = sc.nextLine();
            String[] options = choice.split(",");
            book = Integer.parseInt(options[0]);
            option = Integer.parseInt(options[1]);

            switch(option) {
                case 1:
                    livros.get(book - 1).registar();
                    break;

                case 2:
                    livros.get(book - 1).requisitar();
                    break;

                case 3:
                    livros.get(book - 1).devolver();
                    break;

                case 4:
                    livros.get(book - 1).reservar();
                    break;

                case 5:
                    livros.get(book - 1).cancelar();
                    break;

                default:
                    System.out.println("ERROR: Invalid option");
                    break;
            }

            System.out.println();
            System.out.println("*** Biblioteca ***");

            for(int i = 0; i < livros.size(); i++) {
                int book_number = i + 1;
                System.out.println(book_number + "     " + livros.get(i));
            }

            System.out.println(">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela; (6)terminar;");


        } while (book != 6 || option != 6);

        sc.close();

    }

}
