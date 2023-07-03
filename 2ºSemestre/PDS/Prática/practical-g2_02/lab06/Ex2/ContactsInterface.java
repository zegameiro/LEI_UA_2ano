package lab06.Ex2;

public interface ContactsInterface {

    public void openAndLoad(ContactsStorageInterface store);  

    public void saveAndClose();  

    public void saveAndClose(ContactsStorageInterface store);  

    public boolean exist(Contact contact); 

    public Contact getByName(String name); 

    public boolean add(Contact contact); 

    public boolean remove(Contact contact);

}
