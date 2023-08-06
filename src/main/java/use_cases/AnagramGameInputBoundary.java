package use_cases;

import java.util.Scanner;

public interface AnagramGameInputBoundary {
    String getDifficulty(Scanner scanner);
    String getRandomWord(String difficulty);
    String shuffleWord(String word);
    int calculateScore(long elapsedTime, String difficulty);
    boolean checkAnagrams(String word1, String word2);
}
