package lab06.Ex1;

public class RegistosAdaptor implements AdaptorInterface{

        private Registos registos;

        public RegistosAdaptor(Registos registos) {
            this.registos = registos;
        }

        @Override
        public void addEmployee(Employee e1) {
            Empregado emp = new Empregado(e1.getFirstName(), e1.getLastName(), e1.getEmpNum(), e1.getSalary());
            registos.insere(emp);
        }

        @Override
        public void deleteEmployee(int emp_num) {
            registos.remove(emp_num);
        }

        @Override
        public boolean containsEmployee(int emp_num) {
            return registos.isEmpregado(emp_num);
        }

        @Override   
        public void getAllEmployees() {
            for (Empregado e : registos.listaDeEmpregados()) {
                System.out.println(e);
            }
        }   

        @Override
        public String toString() {
            return registos.toString();
        }
    }
