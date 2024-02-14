package DictionaryTypes.abstractClasses;
import DictionaryTypes.ResizingArrayDictionary;
import com.sun.security.jgss.GSSUtil;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * <i>Dictionary</i> This class provides a basic capability for
 * finding and sorting through arrays to find the best suggestions for words that are
 * misspelled.
 * <p>
 *   @author 25116835
 * */

public abstract class Dictionary {
    /**
     * <i>Dictionary</i> is an abstract class the connects the the other 3 Array/Trie classes as well as the ArrayTypeDictionary and provides
     * them with fundamental methods for problems
     */

    /**
     *
     * @param dictionaryWords is the ArrayList data structure that contains all the words in the dictionary
     * @param word is the variable for the word that needs to be analyzed to find the topN most suitable words closest to it
     * @param N the top amount of words that need to in the final array
     *
     * @return it returns a String Array consisting of N of the closest words
     */

    public String[] getTopNSuggestions(ArrayList<String> dictionaryWords, String word, int N) {

        String[] topN;
        int size = dictionaryWords.size();

        int minLength = 0;
                                                                         // Creates new ArrayList for all the LevenshteinDistances and adds them to it
        ArrayList<Integer> distances = new ArrayList<Integer> ();
        for (int i = 0; i < size; i++){
              minLength = getLevenshteinDistance(word,dictionaryWords.get(i));
              distances.add(minLength);
        }


        for (int x = 0; x < distances.size(); x++) {
            for (int y = distances.size() - 1; y > x; y--) {             //  double for loop to order all the LevenshteinDistances from small to large then
                if (distances.get(x) > distances.get(y)) {               //  adds the specific dictionary words back into those places to form a smallest to largest
                    String smallestString = dictionaryWords.get(x);      //  list of the words with the lowest LevenshteinDistances
                        int smallestInt = distances.get(x);
                        distances.set(x,distances.get(y)) ;
                        distances.set(y,smallestInt);
                    dictionaryWords.set(x,dictionaryWords.get(y));
                    dictionaryWords.set(y,smallestString);
                }
            }
        }

      if (N < dictionaryWords.size()) {                                  // if statement for when there is more N requested than in the dictionary
          topN = new String[N];
          for (int i = 0; i < N ; i++) {
              String Nword = dictionaryWords.get(i);
              topN[i] = Nword;
              System.out.println(topN[i]);

          }

          return topN;
      }else{
          topN = new String[dictionaryWords.size()];
          for (int i = 0; i < dictionaryWords.size(); i++) {             // Returns String array with all the elements in TopN
              String Nword = dictionaryWords.get(i);
              topN[i] = Nword;
              System.out.println(topN[i]);
          }
          return topN;
      }
    }

    /**
     * Levenshtein distance is a string metric for measuring the difference between two sequences. Informally, the Levenshtein distance between
     * two words is the minimum number of single-character edits (insertions, deletions or substitutions) required to change one word into the other
     */
    /**
     *
     * @param wrongWord the word that is spelt incorrectly
     * @param actualWord the correct spelling of the word
     *
     * @return uses mathematical formulas to work out a integer value that determines its relation the the wrong word
     */

    public int getLevenshteinDistance(String wrongWord, String actualWord) {

        int[][] Ldistance = new int[wrongWord.length() + 1][actualWord.length() + 1];

        for (int i = 0; i <= wrongWord.length(); i++) {
            Ldistance[i][0] = i;
        }
        for (int j = 1; j <= actualWord.length(); j++) {
            Ldistance[0][j] = j;
        }

        for (int i = 1; i <= wrongWord.length(); i++) {
            for (int j = 1; j <= actualWord.length(); j++) {
                 Ldistance[i][j] =  minimum(Ldistance[i - 1][j] + 1,Ldistance[i][j - 1] + 1,Ldistance[i - 1][j - 1] + LdistinceC(wrongWord, actualWord, i, j));
            }
        } return Ldistance[wrongWord.length()][actualWord.length()];
    }

    /**
     * Finds the minimum value of the input
     * <p>
     * @param a 1st interger
     * @param b 2nd interger
     * @param c 3rd interger
     * @return the smallest of the 3 values
     */
    public int minimum(int a, int b, int c) {
         return Math.min( Math.min(a, b) , c);
    }
    /**
     * @param wrongWord the misspelled word
     * @param actualWord the correct spelling word
     * @param i the i for loop variable
     * @param j the j for loop variable
     * @return
     */
    public int LdistinceC(String wrongWord,String actualWord, int i, int j) {
        int var = 0;
        if (wrongWord.charAt(i - 1) == actualWord.charAt(j - 1)){
            var = 0;
            return var;
        }else {
            var = 1;
            return var;
        }

    }

}




