package use_cases.scoring;

/**
 * A class that implements ScoringStrategy and generates the score when the difficulty is set to medium.
 */
public class MediumScoringStrategy implements ScoringStrategy{
    private static int MEDIUM_SCORE_DIVIDEND;

    /**
     * Creates a new instance of MediumScoringStrategy.
     */
    public MediumScoringStrategy(){
        MEDIUM_SCORE_DIVIDEND = 150000;
    }
    /**
     * Calculates the score given the time it took to solve the anagram.
     * @param elapsedTime a long representing how long it took
     * @return an int representing the score
     */
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (MEDIUM_SCORE_DIVIDEND / elapsedTime);
    }
}