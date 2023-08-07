package use_cases.gameplay;

/**
 * An interface that the AnagramGameUseCase implements to maintain the Dependency Inversion Principle.
 */
public interface AnagramGameInputBoundary {

    /**
     * Creates a new round of the anagram game.
     */
    void playAnagramGame();

    /**
     * Creates a new round of the anagram checker.
     */
    void checkAnagrams();
}
