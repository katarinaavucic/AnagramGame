package entities;

import java.util.Arrays;

/**
 * A class used to check if two words are anagrams.
 */
public class AnagramChecker {

    /**
     * Checks if two words are anagrams.
     * @param word1 the first word
     * @param word2 the second word
     * @return a boolean that is true if they are anagrams and false if not
     */
    public boolean areAnagrams(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }
}