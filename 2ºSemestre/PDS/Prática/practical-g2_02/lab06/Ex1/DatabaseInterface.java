package lab06.Ex1;

public class DatabaseInterface implements EmployeeInterface{
    
    DatabaseInterface db;

    public DatabaseInterface(DatabaseInterface db2) {
        this.db = db2;
    }

    @Override
    public String firstAndLastName() {
        return db.firstAndLastName();
    }

    @Override
    public String nome() {
        return db.nome();
    }

    @Override
    public String apelido() {
        return db.apelido();
    }

    @Override
    public int empNum() {
        return db.empNum();
    }

    @Override
    public double salary() {
        return db.salary();
    }

    @Override
    public String toString() {
        return db.toString();
    }

}
