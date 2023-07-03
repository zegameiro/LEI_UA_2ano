package lab06.Ex2;

import java.util.*;
import java.io.*;

public class StorageContstxt implements ContactsStorageInterface {


    @Override
    public boolean saveContacts(List<Contact> list) {
        try {
            File file = new File("lab06/Ex2/Additional Files/contacts2.txt");
            FileWriter fw = new FileWriter(file);

            for (Contact contact : list) 
                fw.write(contact.getNome() + " " + contact.getTelefone() + " " + contact.getEmail());

            fw.close();

            return true;
            
        } catch (IOException e) {
            System.err.println("ERROR: could not save the file");
            System.exit(1);
        }
        return false; 
    }

    @Override
    public List<Contact> loadContacts() {
        List<Contact> lstContacts = new ArrayList<Contact>();

        try {
            File file = new File("lab06/Ex2/contacts.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String contactInfo = sc.nextLine();

                // Check there's an empty line on the file
                if (contactInfo.isEmpty()) {
                    System.err.println("ERROR: the file contains a empty line");
                    System.exit(1);
                }

                String[] contactInfoArray = contactInfo.split(" ");

                // Check the format of the line
                if(contactInfoArray.length != 3) {
                    System.err.println("ERROR: the file contains a line with wrong format");
                    System.exit(1);

                // Check if the first element of the line (name) is valid
                } else if(contactInfoArray[0].length() > 20) {
                    System.err.println("ERROR: invalid name found");
                    System.exit(1);

                // Check if the second element of the line (phone number) is valid 
                } else if(contactInfoArray[1].length() != 9) {
                    System.err.println("ERROR: invalid phone number found");
                    System.exit(1);
                
                // Check if the last element of the line (email address) is valid
                } else if(contactInfoArray[2].length() > 30 || !contactInfoArray[2].contains("@") || !contactInfoArray[2].contains(".")) {
                    System.err.println("ERROR: invalid email found");
                    System.exit(1);
                }

                Contact contact = new Contact(contactInfoArray[0], Integer.parseInt(contactInfoArray[1]), contactInfoArray[2]);
                lstContacts.add(contact);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: could not open the file");
            System.exit(1);
        }

        return lstContacts;
    }
}