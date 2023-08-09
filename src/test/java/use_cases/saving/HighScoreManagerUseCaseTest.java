package use_cases.saving;
import interface_adapters.FileHighScoreGateway;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the HighScoreManagerUseCase.
 * Note: the saveHighScores functionality is not tested here.
 */
public class HighScoreManagerUseCaseTest {
    private static HighScoreManagerUseCase highScoreManager;

    /**
     * Sets up the HighScoreManagerUseCase object.
     */
    @BeforeClass
    public static void setUp(){
        HighScoreGatewayBoundary highScoreGateway = new FileHighScoreGateway("high_scores_test.txt");
        highScoreManager = new HighScoreManagerUseCase(highScoreGateway);
    }

    /**
     * Tests the getHighScore, isHighScore, and updateHighScore functionalities on "easy" difficulty. It first checks
     * that the high score is 0 in our "high_scores_test.txt" file and then checks for a high score and updates the
     * local Map with that high score. Then it checks a lower score to ensure it is updated, and lastly asserts that the
     * updated score is indeed the new high score.
     */
    @Test
    public void highScoreManagerEasy() {
        Assert.assertEquals(0, highScoreManager.getHighScore("easy"));
        Assert.assertTrue(highScoreManager.isHighScore("easy", 10));
        highScoreManager.updateHighScore("easy", 10);
        Assert.assertFalse(highScoreManager.isHighScore("easy", 5));
        Assert.assertEquals(10, highScoreManager.getHighScore("easy"));
    }

    /**
     * Tests the getHighScore, isHighScore, and updateHighScore functionalities on "medium" difficulty. It first checks
     * that the high score is 0 in our "high_scores_test.txt" file and then checks for a high score and updates the
     * local Map with that high score. Then it checks a lower score to ensure it is updated, and lastly asserts that the
     * updated score is indeed the new high score.
     */
    @Test
    public void highScoreManagerMedium() {
        Assert.assertEquals(0, highScoreManager.getHighScore("medium"));
        Assert.assertTrue(highScoreManager.isHighScore("medium", 20));
        highScoreManager.updateHighScore("medium", 20);
        Assert.assertFalse(highScoreManager.isHighScore("medium", 15));
        Assert.assertEquals(20, highScoreManager.getHighScore("medium"));
    }

    /**
     * Tests the getHighScore, isHighScore, and updateHighScore functionalities on "hard" difficulty. It first checks
     * that the high score is 0 in our "high_scores_test.txt" file and then checks for a high score and updates the
     * local Map with that high score. Then it checks a lower score to ensure it is updated, and lastly asserts that the
     * updated score is indeed the new high score.
     */
    @Test
    public void highScoreManagerHard() {
        Assert.assertEquals(0, highScoreManager.getHighScore("hard"));
        Assert.assertTrue(highScoreManager.isHighScore("hard", 30));
        highScoreManager.updateHighScore("hard", 30);
        Assert.assertFalse(highScoreManager.isHighScore("hard", 25));
        Assert.assertEquals(30, highScoreManager.getHighScore("hard"));
    }
}