import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BubbleSorter {

    private static int[] toSort = new int[10];

    BubbleSorter(int[] toSort) {
        BubbleSorter.toSort = toSort;
    }

    public static void main(String args[]) {
        int[] newToSort = new int[10];

        if (args.length == 0) {
            System.out.println("Error: Please provide at least two arguments");
            return;
            //if a file is provided    
        } else if (args.length == 1) {
            try {
                String content;
                content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
                String[] numbers = content.split(" ");
                newToSort = new int[numbers.length];

                //convert all strings to integers
                for (int i = 0; i < numbers.length; i++) {
                    try {
                        newToSort[i] = Integer.parseInt(numbers[i]);
                    } catch (Exception e) {
                        //ignore bad input
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BubbleSorter.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(toSort.length);
        } else {
            //if we are just sorting cmd line arguments
            //convert the string args to ints
            newToSort = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                newToSort[i] = Integer.parseInt(args[i]);
            }
        }

        for (int i = 0; i < args.length; i++) {
            //create our sorter
            BubbleSorter bubbleSorter = new BubbleSorter(Arrays.copyOfRange(newToSort, 0, i));

            //sort the array and print the time
            long startTime = System.currentTimeMillis();
            bubbleSorter.sort();
            long endTime = System.currentTimeMillis();

            System.out.println(i + "," + (endTime - startTime));
        }
    }

    public void sort() {
        boolean didSwap = true;
        int range = 0;

        //while a swap occured
        while (didSwap) {
            didSwap = false;
            range++;
            //loop through the ever shrinking range and make swaps
            for (int i = 0; i < toSort.length - range; i++) {
                if (toSort[i] > toSort[i + 1]) {
                    swapNextToSort(i);
                    didSwap = true;
                }
            }
        }
    }

    private void swapNextToSort(int i) {
        int temp = toSort[i];
        toSort[i] = toSort[i + 1];
        toSort[i + 1] = temp;
    }
}
