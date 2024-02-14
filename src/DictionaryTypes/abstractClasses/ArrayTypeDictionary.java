package DictionaryTypes.abstractClasses;
/**
 * This is an Abstract class that inherits form the Dictionary class and is used in the
 * two Array Type Classes for soring words
 */
public abstract class ArrayTypeDictionary extends Dictionary {

    /**
     * insertionSort is a sorting algorithm that individually compares each element of
     * the array with each other and replaces them in the sorting order
     * <p>
     * Algorithm sourced from "Introduction To Programming in Java" by Robert Sedgewick and Kevin Wayne
     * @param arr the input array
     * @param finalIndexForSort the final index of the number of elements you want to DictionaryTypes.sort
     */
    public void insertionSort(String[] arr, int finalIndexForSort) {
        int N;
        if (finalIndexForSort >= (arr.length-nulls(arr)) ) {
            N = arr.length-nulls(arr); // Makes sure than if the finalIndexForSort is large than the amount of inputs it will not give an error and will set the finalIndexForSort accordingly
        }else{
            N = finalIndexForSort;
        }
            for (int i = 1; i < N; i++) {
                for (int j = i; j > 0; j--) { // loop to compare all the elements of the array with each other
                    if (arr[j - 1].compareTo(arr[j]) > 0) {  // Compares the elements
                        exch(arr, j - 1, j); // Method to swap elements to sort the array
                    } else {
                        break;
                    }
                }
            }
        }

    /**
     * mergeSort is a sorting algorithm that recursively compares each element of
     * the array by splitting up the array into smaller components and replaces them in the sorting order by using a secondary private
     * method that sorts them.
     * <p>
     * Algorithm sourced from "Introduction To Programming in Java" by Robert Sedgewick and Kevin Wayne
     * @param arr the input array
     * @param finalIndexForSort the final index of the number of elements you want to DictionaryTypes.sort
     */
    public void mergeSort(String[] arr, int finalIndexForSort) {
        int N;
        if (finalIndexForSort >= (arr.length-nulls(arr)) ) {
            N = arr.length-nulls(arr);
        }else{ // Makes sure than if the finalIndexForSort is large than the amount of inputs it will not give an error and will set the finalIndexForSort accordingly
            N = finalIndexForSort;
        }
        mergeSort(arr,0,N); // Takes Parameters and adds it into a Secondary Method to MergeSort it.
    }
    private void mergeSort(String[] arr, int low, int high) {
        int X = high - low;
        if (X <= 1){
            return;
        }
        int middle = low + X/2;
        mergeSort(arr,low,middle); // Recursively sorts the Left side of the Array
        mergeSort(arr,middle,high);// Recursively sorts the Right side of the Array

        String[] tempArr = new String[X]; // Forms new array to gather all the elements

        int i = low, j = middle;

        // Sorts all the elements
        for (int k = 0; k < X; k++) {
            if (i == middle){
                tempArr[k] = arr[j++];
            }
            else if (j == high){
                tempArr[k] = arr[i++];
            }
            else if (arr[j].compareTo(arr[i]) < 0){
                tempArr[k] = arr[j++];
            }
            else{
                tempArr[k] = arr[i++];
            }
        }

        for (int n = 0; n < X; n++) {
            arr[low + n] = tempArr[n]; // Populates the array again with sorted elements
        }

    }


    /**
     * HybridSort is a sorting algorithm that first divides the specific wordlist into subsets of words then runs them
     * through a insertion DictionaryTypes.sort to individually DictionaryTypes.sort ets of "sizes" then adds them together and merge sorts them into a
     * final sorted array.
     * <p>
     * @param arr the input array
     * @param size the size of the segments of words the array is broken up into
     * @param finalIndexForSort the final index of the number of elements you want to DictionaryTypes.sort
     */
    public void hybridSort(String[] arr, int size, int finalIndexForSort) {
        int N;
        if (finalIndexForSort >= (arr.length-nulls(arr)) ) {
            N = arr.length-nulls(arr);
        }else{  // Makes sure than if the finalIndexForSort is large than the amount of inputs it will not give an error and will set the finalIndexForSort accordingly
            N = finalIndexForSort;
        }
        String[] mergeSortArray = new String[N]; // Temp array
        int count = 0;
        int p = 0;
        String[] array = new String[size];
        int numberOfGroups = N/size;
        int res = N%size;

        if (finalIndexForSort >= size)
        {
            if (res == 0) { // if split into even groups
                for (int i = 0; i < numberOfGroups;i++){  // cycles throughout number of groups
                    for (int x = 0; x < size; x++){  // cycles throughout each element in those groups
                        array[x] = arr[x+count];  // adds them to temp array
                    }
                    count = count+size;
                    insertionSort(array,size);  // sorts the size of elements
                    for (int j = 0; j < array.length; j++) {
                        mergeSortArray[j+p] = array[j]; // adds all elements into temp array
                    }
                    p=p+size;
                }
                mergeSort(mergeSortArray,N); // mergeSorts the Array with all the semi sorted elements in it
            }else
                {
                for (int i = 0; i < numberOfGroups;i++){ // goes throughout number of groups that are full to size
                     for (int x = 0; x < size; x++){ //cycles throughout each element in those groups
                        array[x] = arr[x+count];// adds them to temp array
                    }
                       insertionSort(array,size);// sorts the size of elements
                       count = count + size;

                        for (int k = 0; k < array.length; k++) {
                            mergeSortArray[k+p] = array[k];
                            }
                            p=p+size;
                }
                    for (int x = 0; x < res; x++){ // cycles through amount of elements that are in last uneven group
                        array[x] = arr[x + (size*numberOfGroups)]; // adds them to temp array
                    }
                    insertionSort(array,res); // sorts the size of elements

                    for (int i = 0; i < res; i++) {
                        mergeSortArray[i+p] = array[i]; // Adds them to large Array

                    }
                    mergeSort(mergeSortArray,N); // Merge Sorts the array with Semi sorted elements
            }
        }else {
            insertionSort(arr,N);
            mergeSort(arr,N);
        }
    }

    /**
     * exch is a methoud used in InsertionSort to swap and exchange elements
     * with each other to successfully sort the array
     * <p>
     * Algorithm sourced from "Introduction To Programming in Java" by Robert Sedgewick and Kevin Wayne
     * @param a the input String Array with all the words
     * @param i the first String to swap
     * @param j the second String to swap
     */
    private static void exch(String[] a, int i, int j) {
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * nulls find out how many
     * @param arr
     * @return
     */
    public int nulls(String[] arr){
        int nulls = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                nulls++;
            }
        }
        return nulls;
    }
}

