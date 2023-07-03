package lab06.Ex2;

import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class StorageContsBin implements ContactsStorageInterface {

    @Override
    public List<Contact> loadContacts() {
        List<Contact> lstContacts = new ArrayList<Contact>();
        StringBuilder strBuilder = new StringBuilder();

        try {
            FileInputStream file = new FileInputStream(new File("lab06/Ex2/Additional Files/contactsBin.bin"));

            int ch;
            while((ch = file.read()) != -1)
                strBuilder.append((char) ch);

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(strBuilder.toString());
        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            lstContacts.add(new Contact(line[0], Integer.parseInt(line[1]), line[2]));
        }

        return lstContacts;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        File file = new File("lab06/Ex2/Additional Files/contactsBin2.bin");
        if(list == null)
            return false;
        
        StringBuilder strBuilder = new StringBuilder();
        for(Contact c : list)
            strBuilder.append(c.getNome() + " " + c.getTelefone() + " " + c.getEmail() + "\n");
        
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(strBuilder.toString().getBytes(StandardCharsets.UTF_8));
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    
}
