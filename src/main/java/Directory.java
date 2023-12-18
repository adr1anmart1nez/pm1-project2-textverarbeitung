import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that searches for words and returns a directory of them. In other
 * words, it shows the occurrences of the words and their indices within the document.
 *
 * @author Team03 - Stackoverflow
 * @version 1.0
 */
public class Directory {

    /**
     * Get a mapping between each word and the indices of its occurrences.
     *
     * @param paragraphs The paragraphs of the document.
     * @return A mapping between each word and the indices of its occurrences. If {@code paragraphs} is <code>null</code>, it returns <code>null</code> again.
     */
    public HashMap<String, ArrayList<Integer>> getIndices(ArrayList<String> paragraphs) {
        if (paragraphs == null) {
            return null;
        }
        HashMap<String, ArrayList<Integer>> indicesMap = new HashMap<>();

        String text = String.join(" ", paragraphs);
        HashMap<String, Integer> wordCounts = countOccurrences(text);

        for (String word : wordCounts.keySet()) {
            if (wordCounts.get(word) < 4 || word.isBlank()) {
                continue;
            }

            ArrayList<Integer> indices = new ArrayList<>();

            for (int i = 0; i < paragraphs.size(); i++) {
                if (paragraphs.get(i).contains(word)) {
                    indices.add(i + 1);
                }
            }
            indicesMap.put(word, indices);
        }
        return indicesMap;
    }

    /**
     * Counts all the occurrences of each word and returns it as a mapping.
     *
     * @param text The whole text of the document.
     * @return A mapping between a word and all of its occurrences. If {@code text} is <code>null</code>, it returns <code>null</code> again.
     */
    HashMap<String, Integer> countOccurrences(String text) {
        if (text == null) {
            return null;
        }

        HashMap<String, Integer> wordCounts = new HashMap<>();
        String[] words = text.split(" ");

        for (String s : words) {
            String word = removeSpecialCharacters(s);

            // Skip Strings that are empty or that don't start with an uppercase letter
            if (word.isBlank() || word.matches("[^A-ZÄÖÜ]\\w*")) {
                continue;
            }

            if (wordCounts.containsKey(word)) {
                Integer count = wordCounts.get(word);
                wordCounts.put(word, ++count);
            } else {
                wordCounts.put(word, 1);
            }
        }
        return wordCounts;
    }

    /**
     * Removes all the special characters, including punctuation marks, from a word.
     *
     * @param word The word to remove the special characters from.
     * @return The word without special characters.
     */
    private String removeSpecialCharacters(String word) {
        // Remove all characters that are not letters or numbers
        return word.replaceAll("[^a-zA-ZäöüÄÖÜ\\d]", "");
    }
}