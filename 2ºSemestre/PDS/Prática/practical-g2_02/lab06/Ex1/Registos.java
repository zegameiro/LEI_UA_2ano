package lab06.Ex1;

import java.util.*;

class Registos { 
    
        // Data elements 
        private ArrayList<Empregado> empregados; // Stores the employees 

        public Registos() { 
            empregados = new ArrayList<>(); 
        } 

        public void insere(Empregado e1) { 
            // Code to add employee
            empregados.add(e1);
        } 

        public void remove(int codigo) { 
            // Code to remove employee 
            empregados.remove(codigo);
        }

        public boolean isEmpregado(int codigo) { 
            // Code to check if employee exists 
            return empregados.contains(codigo);
        }

        public List<Empregado> listaDeEmpregados() {
            // Code to retrieve collection 
            return empregados; 
        } 

        @Override
        public String toString() {
            return "Empregados: " + empregados;
        }
    
   }