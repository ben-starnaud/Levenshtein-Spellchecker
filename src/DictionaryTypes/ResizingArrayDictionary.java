package DictionaryTypes;
import java.util.ArrayList;
import DictionaryTypes.abstractClasses.ArrayTypeDictionary;
/**
 *  ResizingArray class the resizes the array Dictionary and forms a Dictionary, this class inherits form the ArrayTypeDictionary class
 *  and implements the Interface from DictionaryInterface.
 */
public class ResizingArrayDictionary extends ArrayTypeDictionary implements DictionaryInterface {

    String[] arrayDictionary;

    /**
     * Constructor that Initialises Resizing array to size 5
     */
    public ResizingArrayDictionary(){
        arrayDictionary = new String[5];
    }

    /**
     *
     * @param arrayDictionary the array with all the words in it.
     * @return the amount of nulls in the array (OPEN SPACES) to indicate the size.
     */
    public int amountofNulls(String[] arrayDictionary) {
        int nulls = 0;
        for (int i = 0; i < arrayDictionary.length; i++){
            if (arrayDictionary[i] == null){  // looks for all the nulls in the array
                nulls++;
            }else {
            }
        }
     return nulls;
    }

    /**
     * Calculates the amount of open space left in the array and resizes it accordingly.
     *
     * @param arrayDictionary the array with all the words in it
     * @return the amount of open spaces in the array
     */
    public String[] openSpaceCheck(String[] arrayDictionary) {

        if (arrayDictionary.length >= 5 && (getNumberOfElements() <= 0.25 * (arrayDictionary.length))) {
            String[] tempArray = new String[(arrayDictionary.length) / 2]; // new temp array for resizing
            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = arrayDictionary[i];
            }   // Transfers all the elements into a temp array then repopulates the arrayDictionary array
            arrayDictionary = new String[tempArray.length];
            for (int i = 0; i < tempArray.length; i++) {
                arrayDictionary[i] = tempArray[i];
            }
            return arrayDictionary;
        }else{
            return arrayDictionary;
        }
    }
    /**
     * Creates dictionary to store the words in an ResizableArray Data Structure
     * <p>
     * @param filepath links to the desired filepath
     */
    public void CreateDictionary(String filepath) {
        int lines;
        lines = numberOfLines(filepath);
        In input = new In(filepath);
        int duplicates = 0;
        int i = 0;
        if (lines <= 30000) {
            arrayDictionary = new String[lines+5]; // new array with all the elements +5 spaces
            while(input.hasNextLine()){
                String word = input.readLine();// Creates dictionary by adding in words from input
                if (!getDictionaryWords().contains(word)) { // if the word is not pre-contained in the dictionary then it adds it
                    arrayDictionary[i] = word;
                    i++; // increment through positions in the dictionary array
                }else{
                    duplicates++; // increments the amount of duplicates
                }
            }
            String[] temp = new String[arrayDictionary.length-duplicates];
            for (int j = 0; j < arrayDictionary.length-duplicates; j++) {
                temp[j] = arrayDictionary[j];
            } // populates the old array with new array without the duplicates
            arrayDictionary = new String[arrayDictionary.length-duplicates];
            for (int j = 0; j < arrayDictionary.length; j++) {
                arrayDictionary[j] = temp[j];
            }
        } else{
            arrayDictionary = new String[lines];
            for (int j = 0; j < 30000; j++) { // if more than 30 000 lines - restrict dictionary to only having 30 000 elements
                arrayDictionary[j] = input.readLine();
            }
        }

}

    /**
     * isWord returns a boolean value to say if the word provided is in the ResizingArray Data Structure
     * <p>
     * @param word is the word that has to be checked.
     * @return the returns a boolean value for if it is in the Data Structure or not.
     */
    public boolean isWord(String word) {
        if (arrayDictionary != null){
        for (int i = 0; i < arrayDictionary.length - amountofNulls(arrayDictionary); i++) {
            if (arrayDictionary[i].equals(word)) {
                return true;
            }
         }
        return false;
        }
        else{
            return false;
        }

    }

    /**
     * addNewWord adds a new word to the Data Structure
     * <p>
     * @param word new word to be added
     */
    public void addNewWord(String word) {
        if (!isWord(word)) {
            if (arrayDictionary == null) {  // if no dictionary is created
                arrayDictionary = new String[5];
                arrayDictionary[0] = word;
            } else {
                if (!getDictionaryWords().contains(word)) { // make sure there are no duplicates
                    int i = arrayDictionary.length;
                    if (amountofNulls(arrayDictionary) == 0) { // if dictionary is full
                        String[] tempArray = new String[(arrayDictionary.length) * 2]; // double size if array is full

                        for (int q = 0; q < arrayDictionary.length; q++) {
                            tempArray[q] = arrayDictionary[q];
                        }
                        tempArray[arrayDictionary.length] = word; // adds word to next space of new array

                        for (int x = arrayDictionary.length + 1; x < tempArray.length; x++) {
                            tempArray[x] = null;    // makes the next amount of spaces in the array empty
                        }
                        arrayDictionary = new String[tempArray.length];
                        for (int b = 0; b < tempArray.length; b++) {  // repopulates old array
                            arrayDictionary[b] = tempArray[b];
                        }

                    } else { // if not not full
                        arrayDictionary[i - amountofNulls(arrayDictionary)] = word;
                        openSpaceCheck(arrayDictionary);
                    }

                }
            }
        }
    }
    /**
     * removeWord removes a word from the ResizableArray Data Structure.
     * <p>
     * @param word word to be removed
     */
    public void removeWord(String word) {
        if (arrayDictionary == null){  // if dictionary is empty
            System.out.println("Dictionary Empty!");
        }else {
            if (isWord(word)){ // if it is a valid word to remove
                int index = -1;
                for (int i = 0; i < arrayDictionary.length - amountofNulls(arrayDictionary); i++) {// finds index of word
                    if (arrayDictionary[i].equals(word)) {
                        index = i;  // looks for index of word that has to be removed
                    }
                }
                if (index == 0) {
                    String[] newArray1 = new String[arrayDictionary.length];

                    for (int i = 1; i < arrayDictionary.length; i++) {
                        newArray1[i - 1] = arrayDictionary[i]; // populates temp array with words for position 2 in old array
                    }
                    arrayDictionary = new String[arrayDictionary.length];
                    for (int i = 0; i < arrayDictionary.length; i++) { // repopulates old array
                        arrayDictionary[i] = newArray1[i];
                    }
                    arrayDictionary = openSpaceCheck(arrayDictionary);

                } else if (index == arrayDictionary.length - amountofNulls(arrayDictionary) - 1) // if last position in the array
                {
                    arrayDictionary[arrayDictionary.length - amountofNulls(arrayDictionary)-1] = null; // deletes and makes it empty
                    arrayDictionary = openSpaceCheck(arrayDictionary);
                }


                else {
                    String[] newArray1 = new String[index];
                    String[] newArray2 = new String[arrayDictionary.length];
                    String[] testArray = new String[arrayDictionary.length - index - 1];
                    String[] finalArray = new String[arrayDictionary.length - 1];

                    for (int i = 0; i < index; i++) { // all elements before index point
                        newArray1[i] = arrayDictionary[i];
                    }
                    for (int i = index + 1; i < arrayDictionary.length; i++) { // all elements after index point
                        newArray2[i] = arrayDictionary[i];
                    }
                    for (int i = index + 1; i < arrayDictionary.length; i++) {
                        testArray[i - (index + 1)] = newArray2[i]; // add them into another temp array
                    }
                    for (int i = 0; i < newArray1.length; i++) {
                        finalArray[i] = newArray1[i];
                    } // Populates the array with elements before and after now all together
                    for (int i = 0; i < testArray.length; i++) {
                        finalArray[newArray1.length + i] = testArray[i];
                    }
                    arrayDictionary = new String[arrayDictionary.length];
                    for (int i = 0; i < finalArray.length; i++) {
                        arrayDictionary[i] = finalArray[i]; // puts it back into old array
                    }
                    arrayDictionary = openSpaceCheck(arrayDictionary);
                }
            }
        }
    }
    /**
     * getDictionaryWords is a ArrayList<String> that puts all the words from ResizingArray the Data Structure
     * into a new ArrayList and stores them for uses.
     * <p>
     * @return a new ArrayList with all the words in the Dictionary.
     */
    public ArrayList<String> getDictionaryWords() {
        ArrayList<String> dictionaryWords = new ArrayList<>();
        int amount = getNumberOfElements();
        for (int i = 0; i < amount; i++) {
            dictionaryWords.add(arrayDictionary[i]);
        }
        return dictionaryWords;
    }
    /**
     * getNumberOfElements() finds the number of element in an Array Dictionary
     *
     * @return the number of words
     */
    public int getNumberOfElements() {
       int size = 0;
       if (arrayDictionary == null){
           size = 0;
           return size;
       }else {
           for (int i = 0; i < arrayDictionary.length - amountofNulls(arrayDictionary); i++) {
               size++;
           }

           return size;
       }
    }
    /**
     * numberOfLines gets the number of lines.
     *
     * @param filepath the filepath of the input file
     * @return the number of lines in the filepath
     */
    public int numberOfLines(String filepath) {
        int lines = 0;
        In input = new In(filepath);

            while (input.hasNextLine()) {  // Loops through input file and finds all the words
                String word = input.readLine();
                lines++;
            }

            return lines;
       }
    }


