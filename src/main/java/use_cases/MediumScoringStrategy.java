package use_cases;

public class MediumScoringStrategy implements ScoringStrategy{
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (150000 / elapsedTime);
    }
}