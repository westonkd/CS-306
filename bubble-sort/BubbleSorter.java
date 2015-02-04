import java.util.Arrays;

public class BubbleSorter {
	private static int[] toSort = new int[10];

	BubbleSorter(int[] toSort) {
		this.toSort = toSort;
	}

	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("Error: Please provide at least two arguments");
			return;
		}

		//convert the string args to ints
		int[] newToSort = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			newToSort[i] = Integer.parseInt(args[i]);
		}

		for (int i = 0; i < args.length; i++) {
			//create our sorter
			BubbleSorter bubbleSorter = new BubbleSorter(Arrays.copyOfRange(newToSort,0,i));

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