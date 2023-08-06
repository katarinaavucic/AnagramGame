package use_cases.scoring;

public class EasyScoringStrategy implements ScoringStrategy {
    @Override
    public int calculateScore(long elapsedTime) {
        return (int) (100000 / elapsedTime);
    }
}