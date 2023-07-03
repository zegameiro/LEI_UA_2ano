package lab11.Ex1;

import java.util.ArrayList;

public class QuickSortStrategy implements Strategy {

    // getLista()
    public ArrayList<Telemovel> getLista() {
        return getLista();
    }

    @Override
    public void sort(ArrayList<Telemovel> telemovel) {
        quickSort(telemovel, 0, telemovel.size() - 1);
    }

    private void quickSort(ArrayList<Telemovel> telemovel, int i, int j) {
        if (i < j) {
            int pos = partition(telemovel, i, j);
            quickSort(telemovel, i, pos - 1);
            quickSort(telemovel, pos + 1, j);
        }
    }

    private int partition(ArrayList<Telemovel> telemovel, int i, int j) {
        Telemovel pivot = telemovel.get(j);
        int k = i - 1;
        for (int l = i; l < j; l++) {
            if (telemovel.get(l).getPreço() < pivot.getPreço()) {
                k++;
                swap(telemovel, k, l);
            }
        }
        swap(telemovel, k + 1, j);
        return k + 1;
    }

    private void swap(ArrayList<Telemovel> telemovel, int k, int l) {
        Telemovel aux = telemovel.get(k);
        telemovel.set(k, telemovel.get(l));
        telemovel.set(l, aux);
    }
    
}
