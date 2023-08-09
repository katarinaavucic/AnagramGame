package use_cases.gameplay;
import entities.AnagramChecker;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A test class for AnagramGameUseCase.
 * Note: this file only tests the protected methods associated with this class, and not the main public methods, as
 * those are comprised of prints to the console and calling the protected methods.
 */
public class AnagramGameUseCaseTest {
    private static AnagramGameUseCase anagramGameUseCase;

    /**
     * Sets up the AnagramGameUseCase object.
     */
    @BeforeClass
    public static void setUp(){
        anagramGameUseCase = new AnagramGameUseCase(new AnagramChecker());
    }

    /**
     * Tests shuffleWord with a word and checks if its result is an anagram (they contain the same characters).
     */
    @Test
    public void shuffleWord() {
        String word = "leopard";
        String shuffledWord = anagramGameUseCase.shuffleWord("leopard");
        Assert.assertTrue(anagramGameUseCase.checkAnagrams(word, shuffledWord));
    }

    /**
     * Tests calculateScore with difficulty set to "easy".
     */
    @Test
    public void calculateScoreEasy() {
        long elapsedTime = 3000;
        int score = anagramGameUseCase.calculateScore(elapsedTime, "easy");
        Assert.assertEquals((int) (100000 / elapsedTime), score);
    }

    /**
     * Tests calculateScore with difficulty set to "medium".
     */
    @Test
    public void calculateScoreMedium() {
        long elapsedTime = 3000;
        int score = anagramGameUseCase.calculateScore(elapsedTime, "medium");
        Assert.assertEquals((int) (150000 / elapsedTime), score);
    }

    /**
     * Tests calculateScore with difficulty set to "hard".
     */
    @Test
    public void calculateScoreHard() {
        long elapsedTime = 3000;
        int score = anagramGameUseCase.calculateScore(elapsedTime, "hard");
        Assert.assertEquals((int) (200000 / elapsedTime), score);
    }

    /**
     * Tests calculateScore with difficulty set to "default", which is "easy".
     */
    @Test
    public void calculateScoreDefault() {
        long elapsedTime = 3000;
        int score = anagramGameUseCase.calculateScore(elapsedTime, "");
        Assert.assertEquals((int) (100000 / elapsedTime), score);
    }

    /**
     * Tests calculateScore with difficulty set to "default" and a long wait period.
     */
    @Test
    public void calculateScoreDefaultLongTime() {
        long elapsedTime = 300000;
        int score = anagramGameUseCase.calculateScore(elapsedTime, "");
        Assert.assertEquals(0, score);
    }

    /**
     * Tests calculateScore with difficulty set to "default" and a short wait period.
     * Note: realistically this is impossible, as the elapsedTime is calculated in milliseconds, however, it is nice to
     * see that even a super-small value will not fail!
     */
    @Test
    public void calculateScoreDefaultShortTime() {
        long elapsedTime = 1;
        int score = anagramGameUseCase.calculateScore(elapsedTime, "");
        Assert.assertEquals((int) (100000 / elapsedTime), score);
    }

    /**
     * Tests checkAnagrams with two words that are anagrams.
     * Note: this method simply calls AnagramChecker, so it is not thoroughly tested in this Test file.
     */
    @Test
    public void testCheckAnagramsTrue() {
        String word1 = "mountain";
        String word2 = "tainmoun";
        Assert.assertTrue(anagramGameUseCase.checkAnagrams(word1, word2));
    }

    /**
     * Tests checkAnagrams with two words that are not anagrams.
     * Note: this method simply calls AnagramChecker, so it is not thoroughly tested in this Test file.
     */
    @Test
    public void testCheckAnagramsFalse() {
        String word1 = "mountain";
        String word2 = "tainfoun";
        Assert.assertFalse(anagramGameUseCase.checkAnagrams(word1, word2));
    }
}