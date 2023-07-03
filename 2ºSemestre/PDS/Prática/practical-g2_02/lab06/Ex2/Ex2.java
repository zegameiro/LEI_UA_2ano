package lab06.Ex2;

public class Ex2 {

    public static void main(String[] args) {

        // Test StorageContstxt ------------------------------------------------------------------------
        System.out.println("---------------------------------------- Test StorageContstxt ----------------------------------------");
        ContactsStorageInterface storageTxt = new StorageContstxt();
        ContactList listaContactos = new ContactList(storageTxt);

        System.out.println("Lista de contactos: " + listaContactos);

        listaContactos.openAndLoad(storageTxt);
        listaContactos.toString();


        Contact contact1= new Contact("Joao", 15175451, "uau");
        Contact contact2= new Contact("Maria", 15568194, "uau2");


        listaContactos.add(contact1);
        listaContactos.add(new Contact("Edgar Dias",911518, "uau3"));


        System.out.println("Contacto: "+ listaContactos.getByName("Edgar Dias"));
        System.out.println("Contacto: "+ listaContactos.getByName("Joao"));


        System.out.println("Maria existe na lista de contactos? " + listaContactos.exist(contact2));


        listaContactos.toString();

        listaContactos.remove(listaContactos.getByName("Filipe"));

        System.out.println("Lista de contactos: " + listaContactos);

        listaContactos.saveAndClose(storageTxt);

        // Test StorageContsBin ------------------------------------------------------------------------

        System.out.println("---------------------------------------- Test StorageContsBin ----------------------------------------");

        ContactsStorageInterface storageBin = new StorageContsBin();
        ContactList listaContactos2 = new ContactList(storageBin);

        System.out.println("Lista de contactos: " + listaContactos2);

        listaContactos2.openAndLoad(storageBin);
        listaContactos2.toString();


        Contact contact3= new Contact("Paulo", 876987654, "uuu@gmail.com");
        Contact contact4= new Contact("Mariana", 123432412, "aaa@gmail.com");


        listaContactos2.add(contact3);
        listaContactos2.add(new Contact("Carlos Pereira",987234123, "kjh@outlook.com"));


        System.out.println("Contacto: "+ listaContactos2.getByName("Carlos Pereira"));
        System.out.println("Contacto: "+ listaContactos2.getByName("Paulo"));


        System.out.println("Mariana existe na lista de contactos? " + listaContactos2.exist(contact4));


        listaContactos2.toString();

        listaContactos2.remove(listaContactos2.getByName("Soraia"));

        System.out.println("Lista de contactos: " + listaContactos2);

        listaContactos2.saveAndClose(storageBin);

    }
}