package use_cases.gameplay;
import entities.AnagramChecker;
import use_cases.scoring.EasyScoringStrategy;
import use_cases.scoring.HardScoringStrategy;
import use_cases.scoring.MediumScoringStrategy;
import use_cases.scoring.ScoringStrategy;
import java.util.Random;

/**
 * A class that executes the gameplay for the anagram games. It implements AnagramGameInputBoundary, and contains an
 * anagramChecker to check for anagrams.
 */
public class AnagramGameUseCase implements AnagramGameInputBoundary {
    private final AnagramChecker anagramChecker;

    /**
     * Creates a new instance of AnagramGameUseCase with the given inputs.
     * @param anagramChecker an AnagramChecker for checking anagrams
     */
    public AnagramGameUseCase(AnagramChecker anagramChecker) {
        this.anagramChecker = anagramChecker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRandomWord(String difficulty) {
        // for simplicity, we'll use predefined lists here
        String[] easyWords = {"apple", "cat", "dog", "sun", "tree"};
        String[] mediumWords = {"banana", "elephant", "guitar", "mountain", "keyboard"};
        String[] hardWords = {"independence", "chocolate", "juxtaposition", "university", "sophisticated"};

        String[] selectedWords;

        switch (difficulty) {
            case "medium":
                selectedWords = mediumWords;
                break;
            case "hard":
                selectedWords = hardWords;
                break;
            default: // default is easy (so not a case)
                selectedWords = easyWords;
                break;
        }
        // selects a random word
        Random random = new Random();
        int randomIndex = random.nextInt(selectedWords.length);
        return selectedWords[randomIndex];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String shuffleWord(String word) {
        char[] characters = word.toCharArray();
        Random random = new Random();

        // shuffles the word
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateScore(long elapsedTime, String difficulty) {
        ScoringStrategy scoringStrategy;
        switch (difficulty) {
            case "medium":
                scoringStrategy = new MediumScoringStrategy();
                break;
            case "hard":
                scoringStrategy = new HardScoringStrategy();
                break;
            default: // default is easy
                scoringStrategy = new EasyScoringStrategy();
                break;
        }
        return scoringStrategy.calculateScore(elapsedTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAnagrams(String word1, String word2) {
        return anagramChecker.areAnagrams(word1, word2);
    }
}