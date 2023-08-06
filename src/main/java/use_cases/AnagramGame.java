package use_cases;

import entities.AnagramChecker;

public class AnagramGame {
    public String playAnagramGame(String word1, String word2) {
        if (AnagramChecker.areAnagrams(word1, word2)) {
            return "Congratulations! " + word1 + " and " + word2 + " are anagrams.";
        } else {
            return word1 + " and " + word2 + " are not anagrams.";
        }
    }
}
