package use_cases.scoring;

/**
 * A class that implements ScoringStrategy and generates the score when the difficulty is set to hard.
 */
public class HardScoringStrategy implements ScoringStrategy{

    /**
     * Calculates the score given the time it took to solve the anagram.
     * @param elapsedTime a long representing how long it took
     * @return an int representing the score
     */
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (200000 / elapsedTime);
    }
}