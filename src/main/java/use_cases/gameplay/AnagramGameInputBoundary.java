package use_cases.gameplay;

/**
 * An interface that the AnagramGameUseCase implements to maintain the Dependency Inversion Principle.
 */
public interface AnagramGameInputBoundary {
    /**
     * Gets a random word from a set of generates words.
     * @param difficulty a String representing the difficulty level
     * @return a String representing the random word
     */
    String getRandomWord(String difficulty);

    /**
     * Shuffles the word.
     * @param word a String representing the random word
     * @return a String representing the shuffled word
     */
    String shuffleWord(String word);

    /**
     * Calculates the score by calling on the correct ScoringStrategy class according to difficulty.
     * @param elapsedTime a long representing how long it took
     * @param difficulty a String representing the difficulty level
     * @return an int representing the score
     */
    int calculateScore(long elapsedTime, String difficulty);

    /**
     * Checks if two words are anagrams by calling the anagramChecker's areAnagrams method.
     * @param word1 the first word
     * @param word2 the second word
     * @return a boolean that is true if they are anagrams and false if not
     */
    boolean checkAnagrams(String word1, String word2);
}