package DictionaryTypes;
import java.util.ArrayList;
/**
 * DictionaryInterface is the interface that links to the 3 Dictionary
 * classes and make methods callable in those classes.
 *
 * These Methods are the basic methods that are needed to make the Dictionary in
 * these different classes and that is why they are in an interface so that they
 * are easily assessable.
 */
public interface DictionaryInterface {

    void CreateDictionary(String filepath);
    boolean isWord(String word);
    void addNewWord(String word);
    void removeWord(String word);
    ArrayList<String> getDictionaryWords();
    String[] getTopNSuggestions(ArrayList<String> dictionaryWords, String word, int N);
    int getLevenshteinDistance(String wrongWord, String actualWord);
    int getNumberOfElements();
    int numberOfLines(String filepath);
}
