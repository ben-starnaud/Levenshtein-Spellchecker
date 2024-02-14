package DictionaryTypes;
import java.util.ArrayList;
import DictionaryTypes.TrieStructureComponents.Edge;
import DictionaryTypes.TrieStructureComponents.States;
import DictionaryTypes.abstractClasses.Dictionary;

/**
 * The Trie Data Structure also called digital tree or prefix tree, is a type of search tree that is used to form
 * a Dictionary, it implements form the DictionaryInterface class interface and inherits for the Dictionary class
 * it also has Components from the State and Edge Classes
 */
public class TrieDictionary extends Dictionary implements DictionaryInterface {
    String[] arrayDictionary;
    int lines;

    /**
     * Constructor
     */
    public TrieDictionary() {

    }

    public ArrayList<Character> BFS() {
        //todo
        return null;
    }

    public ArrayList<String> DFS() {
        //todo
        return null;
    }

    public String findCommonPrefix(String word) {
        String commonPrefix = "";
        //todo
        return commonPrefix;
    }

    /**
     * Creates dictionary to store the words in an Trie Data Structure
     * <p>
     * @param filepath links to the desired filepath
     */
    public void CreateDictionary(String filepath) {
        lines = numberOfLines(filepath);
        In input = new In(filepath);
        int dups = 0;
        int i = 0;
        if (lines <= 30000) {
            arrayDictionary = new String[lines];
            while(input.hasNextLine()){
                String word = input.readLine();// Creates dictionary by adding in words from input
                if (!getDictionaryWords().contains(word)) { // if the word is not pre-contained in the dictionary then it adds it
                    arrayDictionary[i] = word;
                    i++;// increment through positions in the dictionary array
                }else{
                    dups++; // increments the amount of duplicates
                }
            }
            String[] temp = new String[arrayDictionary.length-dups];
            for (int j = 0; j < arrayDictionary.length-dups; j++) {
                temp[j] = arrayDictionary[j];
            } // populates the old array with new array without the duplicates
            arrayDictionary = new String[arrayDictionary.length-dups];
            for (int j = 0; j < arrayDictionary.length; j++) {
                arrayDictionary[j] = temp[j];
            }
        } else{
            arrayDictionary = new String[lines];
            for (int j = 0; j < 30000; j++) {// if more than 30 000 lines - restrict dictionary to only having 30 000 elements
                arrayDictionary[j] = input.readLine();
            }
        }
    }

    /**
     * isWord returns a boolean value to say if the word provided is in the Trie Data Structure
     * <p>
     * @param word is the word that has to be checked.
     * @return the returns a boolean value.
     */
    public boolean isWord(String word) {
        if (arrayDictionary != null){
            for (int i = 0; i < arrayDictionary.length; i++) {
                if (arrayDictionary[i].equals(word)) {
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }

    /**
     * addNewWord adds a new word to the Trie Data Structure
     * <p>
     * @param word new word to be added
     */
    public void addNewWord(String word) {
        if (arrayDictionary == null ) {  // The person has not run CreateDictionary and this is the first word they are adding
            arrayDictionary = new String[1];
            arrayDictionary[0] = word;
        } else if (arrayDictionary.length == 30000){
            System.out.println("Limit Reached");
        } else {  // 2nd Word Added or Dictionary Pre Created
            if (!getDictionaryWords().contains(word)) {
                int i = arrayDictionary.length;
                int n = ++i;
                String[] newArray = new String[n];
                for (int x = 0; x < arrayDictionary.length; x++) {
                    newArray[x] = arrayDictionary[x];   // forms new array and increases the size by 1 to add the new element
                }
                newArray[n - 1] = word;
                arrayDictionary = new String[newArray.length];
                for (int y = 0; y < arrayDictionary.length; y++) {
                    arrayDictionary[y] = newArray[y];   // new array with added word after dictionary is created
                }
            }else {
            }
        }
    }
    /**
     * removeWord removes a word from the Trie Data Structure
     * <p>
     * @param word word to be removed
     */
    public void removeWord(String word) {
        if (arrayDictionary == null){

        }else {
            int index = -1;
            for (int i = 0; i < arrayDictionary.length; i++) {  // Finds index of word
                if (arrayDictionary[i].equals(word)) {
                    index = i;
                }
            }
            if (index == -1) {

            }
            if (index == 0) {
                if (arrayDictionary.length == 1) { // if word to be removed is the first and only word in the Array
                    arrayDictionary = new String[0];

                } else {
                    String[] newArray1 = new String[arrayDictionary.length - 1];

                    for (int i = 1; i < arrayDictionary.length; i++) {
                        newArray1[i - 1] = arrayDictionary[i];
                    }
                    arrayDictionary = new String[newArray1.length];  // if word to be removed is the first word in the Array
                    for (int i = 0; i < newArray1.length; i++) {
                        arrayDictionary[i] = newArray1[i];
                    }
                }
            } else if (index == arrayDictionary.length - 1) { // if word to be removed is last element in the array

                String[] newArray1 = new String[arrayDictionary.length - 1];
                for (int i = 0; i < arrayDictionary.length - 1; i++) {
                    newArray1[i] = arrayDictionary[i];
                }
                arrayDictionary = new String[arrayDictionary.length - 1];
                for (int i = 0; i < newArray1.length; i++) {
                    arrayDictionary[i] = newArray1[i];
                }

            } else if (index != -1) {  // if word to be removed is in the middle of the array
                String[] newArray1 = new String[index];   // Forms new array for words before index word
                String[] newArray2 = new String[arrayDictionary.length];  // Forms new array for words after index word
                String[] testArray = new String[arrayDictionary.length - index - 1];
                String[] finalArray = new String[arrayDictionary.length - 1]; //Arrays for Shifting

                for (int i = 0; i < index; i++) {
                    newArray1[i] = arrayDictionary[i];
                }
                for (int i = index + 1; i < arrayDictionary.length; i++) {
                    newArray2[i] = arrayDictionary[i];
                }
                for (int i = index + 1; i < arrayDictionary.length; i++) {  // Shifting of elements in array
                    testArray[i - (index + 1)] = newArray2[i];
                }
                for (int i = 0; i < newArray1.length; i++) {
                    finalArray[i] = newArray1[i];
                }

                for (int i = 0; i < testArray.length; i++) {
                    finalArray[newArray1.length + i] = testArray[i];
                }
                arrayDictionary = new String[arrayDictionary.length - 1];
                for (int i = 0; i < finalArray.length; i++) { //Re-Declaration of array to size and final array
                    arrayDictionary[i] = finalArray[i];
                }

            }
        }
    }

    /**
     * getDictionaryWords is a ArrayList<String> that puts all the words from the Trie Data Structure
     * into a new ArrayList and stores them for uses.
     * <p>
     * @return a new ArrayList with all the words in the Dictionary.
     */
    public ArrayList<String> getDictionaryWords() {
        ArrayList<String> dictionaryWords = new ArrayList<>();
        int amount = getNumberOfElements();
        for (int i = 0; i < amount; i++) {
            dictionaryWords.add(arrayDictionary[i]); // Adds to list
        }
        return dictionaryWords;
    }

    /**
     * getNumberOfElements() finds the number of element in an Trie Dictionary
     * @return the number of words
     */
    public int getNumberOfElements() {
        int count = 0;
        if (arrayDictionary == null) {
            count = 0;
            return count;
        } else {
            for (int i = 0; i < arrayDictionary.length; i++) {
                count++;
            }
            return count;
        }
    }

    /**
     *
     * @param filepath the filepath of the input file
     * @return the number of lines in the filepath
     */
    public int numberOfLines(String filepath) {
        int lines = 0;
        In input = new In(filepath);

        while (input.hasNextLine()) {
            String word = input.readLine();
            lines++;
        }

        return lines;
    }

}