import java.util.Random;
/**
 *
 * @author Bradk
 * @param <E>
 */
public class SortableArray<E extends Comparable> {

    // Underlying array
    public E[] array;

    // Default constructor
    public SortableArray() {

    }

    // Fills sortable array with input array
    public void setArray(E[] array) {
        this.array = array;
    }

    // Public method to sort array
    public void quickSort() {
        quickSort(array, 0, array.length - 1);
    }

    // Encapsulated 
    private void quickSort(E[] array, int lowIndex, int highIndex) {
        if (lowIndex < highIndex + 1) // If more than 1 item to sort
        {
            int partitionIndex = partition(array, lowIndex, highIndex); // Get new pivot value and sort partition
            quickSort(array, lowIndex, partitionIndex - 1); // Sort each half
            quickSort(array, partitionIndex + 1, highIndex); // Sort each half
        }
    }

    // Sorts items larger & smaller than pivot value, returns new partition index
    private int partition(E[] array, int lowIndex, int highIndex) {
        swapAtIndex(lowIndex, getPivot(lowIndex, highIndex)); // Swap pivot into lowest index
        int indexBoundary = lowIndex + 1; // Stores location past pivot and already sorted values
        for (int i = indexBoundary; i <= highIndex; i++) // Iterates through elements in partition
        {
            if (array[i].compareTo(array[lowIndex]) < 1) // Compares item to pivot at lowestIndex
            {
                swapAtIndex(i, indexBoundary++); // Swap element if smaller
            }
        }
        swapAtIndex(lowIndex, indexBoundary - 1); // Return pivot to correct location
        return indexBoundary - 1; // Returns pivot index
    }

    // Returns a random int between two integers
    private int getPivot(int lowIndex, int highIndex) {
        Random random = new Random();
        return random.nextInt((highIndex - lowIndex) + 1) + lowIndex;
    }

    // Swaps elements at input index's
    private void swapAtIndex(int index1, int index2) {
        if (index1 >= 0 && index1 < array.length && index2 >= 0 && index2 < array.length) { // Checks both index's are within bounds
            E tempValue = array[index2]; // Sets temporary value to 2
            array[index2] = array[index1]; // Sets 2 to 1
            array[index1] = tempValue; // Sets 1 to temp (2)
        }
    }

//    // ======================[TEST METHODS]======================
//    // Methods below are used to test array function
//    
//    // Prints items in array seperated by commas
//    @Override
//    public String toString() {
//        String output = "";
//        for (int i = 0; i < array.length; i++) {
//            output += array[i].toString() + ", ";
//        }
//        return output;
//    }
//
//    // Test quicksort method
//    public static void main(String[] args) {
//        SortableArray array = new SortableArray();
//        array.setArray(new Integer[]{1, 9, 3, 0, 2, 15, 2, 8});
//        System.out.println(array.toString());
//        array.quickSort();
//        System.out.println(array.toString());
//    }
}
