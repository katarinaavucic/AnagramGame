package use_cases.scoring;

/**
 * An interface that several ScoringStrategy classes implement. This is the Strategy design pattern, where multiple
 * classes differ only in their algorithm, and thus can be swapped.
 */
public interface ScoringStrategy {
    /**
     * Calculates the score given the time it took to solve the anagram.
     * @param elapsedTime a long representing how long it took
     * @return an int representing the score
     */
    int calculateScore(long elapsedTime);
}