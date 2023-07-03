package lab06.Ex2;

import java.util.*;

public class ContactList implements ContactsInterface {
    
    private  ContactsStorageInterface cons;
    private List<Contact> contacts;

    public ContactList(ContactsStorageInterface storageTxt) {
        this.cons = storageTxt;
        contacts = new ArrayList<Contact>();
	}

	@Override
    public void openAndLoad(ContactsStorageInterface store) {
        this.cons = store;
        contacts = new ArrayList<Contact>();
    }

    @Override
    public void saveAndClose() {
        this.cons.saveContacts(contacts);
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(contacts);
    }


    @Override
    public boolean exist(Contact contact) {
        for (Contact c : contacts) {
            if (c.equals(contact)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Contact getByName(String name) {
        for(Contact cn : contacts) {
            if (cn.getNome().equals(name)) {
                return cn;
            }    
        }
        
        return null;
    }

    @Override
    public boolean add(Contact contact) {
        if( exist(contact)) {
            System.err.println("ERROR: contact already exists");
            return false;
        } else {
            contacts.add(contact);
            return true;
        }
    }

    @Override
    public boolean remove(Contact contact) {
        if(!exist(contact)) {
            System.err.println("ERROR: contact does not exists");
            return false;
        } else {
            contacts.remove(contact);
            return true;
        }
    }

    @Override
    public String toString() {
        return "ContactList [contacts=" + contacts + "]";
    }
}
