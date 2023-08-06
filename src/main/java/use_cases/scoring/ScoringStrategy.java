package use_cases.scoring;

public interface ScoringStrategy {
    int calculateScore(long elapsedTime);
}