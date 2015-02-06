import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MergeSorter {
     
    private int[] array;
    private int[] tempMergArr;
    private int length;
 
    public static void main(String args[]){
        if (args.length == 0) {
            System.out.println("Please specify the file to sort as an argument");
            return;
        }
       
        try {
            //read the file
            String[] fileStringArray = new Scanner(new File(args[0])).useDelimiter("\\Z").next().split(" ");
            int[] inputArr = new int[fileStringArray.length];

            //convert the string array into integers
            for (int i = 0; i < fileStringArray.length; i++) {
                try {
                    inputArr[i] = Integer.parseInt(fileStringArray[i]);
                } catch (Exception e) {
                    //ignore bad file inputs
                }
            } 
            
            //sort the ever growing array and output times
            System.out.println("Number of Items (n), Time in Milliseconds");
            for (int i = 0; i < inputArr.length; i++) {
                //create our sorter
                //MergeSorter bubbleSorter = new BubbleSorterTwo(Arrays.copyOfRange(fileIntegerArray, 0, i));
                MergeSorter sorter = new MergeSorter();
                
                //sort the array and print the time
                long startTime = System.currentTimeMillis();
                sorter.sort(Arrays.copyOfRange(inputArr, 0, i));
                long endTime = System.currentTimeMillis();

                //System.out.print("[" + i + ", " + (endTime - startTime) + "], ");
                System.out.println(i + "," + (endTime - startTime));
            }
            
            
                                
        } catch (Exception ex) {
            Logger.getLogger(MergeSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
    }
     
    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
 
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
}
