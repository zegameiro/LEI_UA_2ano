package lab11.Ex1;

import java.util.ArrayList;

public class Ex1 {

    public static void main(String[] args) {
        ArrayList<Telemovel> telemovel = new ArrayList<>();
        telemovel.add(new Telemovel("Snapdragon 888", 999.99, "8GB", "108MP", "Pixel"));
        telemovel.add(new Telemovel("Bionic A12", 1200, "6GB", "12MP", "iPhone"));
        telemovel.add(new Telemovel("Exynos 2100", 899.99, "12GB", "64MP", "Galaxy S21"));
        telemovel.add(new Telemovel("MediaTek", 123.4, "2GB", "6MP", "IndianPhone"));

        Strategy bubbleSort = new BubbleSortStrategy();
        Context context = new Context(telemovel, bubbleSort);
        System.out.println("\nBubble Sort:\n " + context.getTelemovel() + "\n");

        Strategy quickSort = new QuickSortStrategy();
        context.setStrategy(quickSort);
        System.out.println("\nQuick Sort:\n " + context.getTelemovel() + "\n");

        Strategy mergeSort = new MergeSortStrategy();
        context.setStrategy(mergeSort);
        System.out.println("\nMerge Sort:\n " + context.getTelemovel() + "\n");
    }
    
}
