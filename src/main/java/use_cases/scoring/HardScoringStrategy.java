package use_cases.scoring;

/**
 * A class that implements ScoringStrategy and generates the score when the difficulty is set to hard.
 */
public class HardScoringStrategy implements ScoringStrategy{
    private static int HARD_SCORE_DIVIDEND;

    /**
     * Creates a new instance of EasyScoringStrategy.
     */
    public HardScoringStrategy(){
        HARD_SCORE_DIVIDEND = 200000;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (HARD_SCORE_DIVIDEND / elapsedTime);
    }
}