package use_cases.scoring;

/**
 * A class that implements ScoringStrategy and generates the score when the difficulty is set to easy.
 */
public class EasyScoringStrategy implements ScoringStrategy {
    private static int EASY_SCORE_DIVIDEND;

    /**
     * Creates a new instance of EasyScoringStrategy.
     */
    public EasyScoringStrategy(){
        EASY_SCORE_DIVIDEND = 100000;
    }

    /**
     * Calculates the score given the time it took to solve the anagram.
     * @param elapsedTime a long representing how long it took
     * @return an int representing the score
     */
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (EASY_SCORE_DIVIDEND / elapsedTime);
    }
}