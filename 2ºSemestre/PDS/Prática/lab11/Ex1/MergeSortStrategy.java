package lab11.Ex1;

import java.util.ArrayList;

public class MergeSortStrategy implements Strategy {

    //getLista()
    public ArrayList<Telemovel> getLista() {
        return getLista();
    }

    @Override
    public void sort(ArrayList<Telemovel> telemovel) {
        mergeSort(telemovel, 0, telemovel.size() - 1);
    }

    private void mergeSort(ArrayList<Telemovel> telemovel, int i, int j) {
        if (i < j) {
            int m = (i + j) / 2;
            mergeSort(telemovel, i, m);
            mergeSort(telemovel, m + 1, j);
            merge(telemovel, i, m, j);
        }
    }

    private void merge(ArrayList<Telemovel> telemovel, int i, int m, int j) {
        ArrayList<Telemovel> aux = new ArrayList<>();
        int k = i;
        int l = m + 1;
        while (k <= m && l <= j) {
            if (telemovel.get(k).getPreço() < telemovel.get(l).getPreço()) {
                aux.add(telemovel.get(k));
                k++;
            } else {
                aux.add(telemovel.get(l));
                l++;
            }
        }
        while (k <= m) {
            aux.add(telemovel.get(k));
            k++;
        }
        while (l <= j) {
            aux.add(telemovel.get(l));
            l++;
        }
        for (int n = i; n <= j; n++) {
            telemovel.set(n, aux.get(n - i));
        }
    }
    
}
