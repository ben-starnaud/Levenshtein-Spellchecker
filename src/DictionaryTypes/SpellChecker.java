package DictionaryTypes;

/**
 * SpellChecker is the Main Class for the Dictionary and all the other Sub-Classes run off it
 */
public class SpellChecker {

    DictionaryInterface spellChecker;

    /**
     * Constructor for the SpellChecker that runs to find the correct Dictionary
     * <p>
     * @param checkerType the input of what type of Dictionary will be used
     */
    public SpellChecker(String checkerType) {
        if (checkerType.equals("Trie")) {
            spellChecker = new TrieDictionary();
        } else if (checkerType.equals("Array")) {
            spellChecker = new ArrayDictionary();
        } else if (checkerType.equals("ResizingArray")) {
            spellChecker = new ResizingArrayDictionary();
        } else {
            System.out.println("Invalid command line input, use one of: Trie, Array, or ResizingArray");
            System.exit(0);
        }
    }

    /**
     * check method checks if the input word is a valid word and is spelt correctly
     *
     * @param word the word to check
     * @return a boolean value if the word is correctly spelled or not
     */
    public boolean check(String word) {
        int distance = 0;
        String[] StringToCheck = spellChecker.getTopNSuggestions(spellChecker.getDictionaryWords(), word, 1);
        distance = spellChecker.getLevenshteinDistance(word, StringToCheck[0]);
        if (distance == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getBestSuggestion finds the best possible word for a misspell word
     * <p>
     * @param wordToCheck word to check
     * @return a String the best suggestion of a word it could be
     */
    public String getBestSuggestion(String wordToCheck) {
        String[] checker = spellChecker.getTopNSuggestions(spellChecker.getDictionaryWords(), wordToCheck, 3);
        return checker[0];
    }
}

