package lab06.Ex1;

class Employee { 

        private String name; 
        private int emp_num; 
        private double salary; 
        
        public Employee(String name, int emp_num, double salary) { 
        this.name = name; 
        this.emp_num = emp_num; 
        this.salary = salary; 
        } 
        public String getLastName() {
        return name;
        }
        public String getFirstName() {
        return name;
        }
        public String getName() { 
        return name; 
        } 
        public int getEmpNum() { 
        return emp_num; 
        } 
        public double getSalary() { 
        return salary; 
        } 

        public String toString() {
        return "Name: " + name + " EmpNum: " + emp_num + " Salary: " + salary;
        }
        
        
   } 
