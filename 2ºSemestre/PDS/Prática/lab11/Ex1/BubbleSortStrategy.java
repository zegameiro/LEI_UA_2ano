package lab11.Ex1;

import java.util.ArrayList;

public class BubbleSortStrategy implements Strategy {

    // getLista()
    public ArrayList<Telemovel> getLista() {
        return getLista();
    }

    @Override
    public void sort(ArrayList<Telemovel> telemovel) {
        bubbleSort(telemovel);
    }

    private void bubbleSort(ArrayList<Telemovel> telemovel) {
        for (int i = 0; i < telemovel.size() - 1; i++) {
            for (int j = 0; j < telemovel.size() - i - 1; j++) {
                if (telemovel.get(j).getPreço() > telemovel.get(j + 1).getPreço()) {
                    swap(telemovel, j, j + 1);
                }
            }
        }
    }

    private void swap(ArrayList<Telemovel> telemovel, int i, int j) {
        Telemovel aux = telemovel.get(i);
        telemovel.set(i, telemovel.get(j));
        telemovel.set(j, aux);
    }
    
}
