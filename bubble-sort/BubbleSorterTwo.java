import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BubbleSorterTwo {

    int[] toSort;

    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Please specify the file to sort as an argument.");
            return;
        }

        try {
            //read the file
            String[] fileStringArray = new Scanner(new File(args[0])).useDelimiter("\\Z").next().split(" ");
            int[] fileIntegerArray = new int[fileStringArray.length];

            //convert the string array into integers
            for (int i = 0; i < fileStringArray.length; i++) {
                try {
                    fileIntegerArray[i] = Integer.parseInt(fileStringArray[i]);
                } catch (Exception e) {
                    //ignore bad file inputs
                }
            }

            //sort the ever growing array and output times
            for (int i = 0; i < fileIntegerArray.length; i++) {
                //create our sorter
                BubbleSorterTwo bubbleSorter = new BubbleSorterTwo(Arrays.copyOfRange(fileIntegerArray, 0, i));

                //sort the array and print the time
                long startTime = System.currentTimeMillis();
                bubbleSorter.sort();
                long endTime = System.currentTimeMillis();

                System.out.println(i + "," + (endTime - startTime));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BubbleSorterTwo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    BubbleSorterTwo(int[] toSort) {
        this.toSort = toSort;
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
