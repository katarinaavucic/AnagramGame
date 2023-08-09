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
     * {@inheritDoc}
     */
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (MEDIUM_SCORE_DIVIDEND / elapsedTime);
    }
}