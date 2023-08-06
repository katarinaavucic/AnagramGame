package use_cases;

public class HardScoringStrategy implements ScoringStrategy{
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (200000 / elapsedTime);
    }
}