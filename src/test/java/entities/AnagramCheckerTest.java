package entities;

import org.junit.Test;
import org.junit.Assert;

/**
 * A test class for AnagramChecker.
 */
public class AnagramCheckerTest {

    /**
     * Tests areAnagrams with two short words that are anagrams.
     */
    @Test
    public void areAnagramsShortWordTrue() {
        String word1 = "dog";
        String word2 = "god";

        AnagramChecker anagramChecker = new AnagramChecker();
        Assert.assertTrue(anagramChecker.areAnagrams(word1, word2));
    }

    /**
     * Tests areAnagrams with two long words that are anagrams.
     */
    @Test
    public void areAnagramsLongWordTrue() {
        String word1 = "juxtaposition";
        String word2 = "xtaposnoitiuj";

        AnagramChecker anagramChecker = new AnagramChecker();
        Assert.assertTrue(anagramChecker.areAnagrams(word1, word2));
    }

    /**
     * Tests areAnagrams with two short words that are not anagrams.
     */
    @Test
    public void areAnagramsShortWordFalse() {
        String word1 = "dog";
        String word2 = "doe";

        AnagramChecker anagramChecker = new AnagramChecker();
        Assert.assertFalse(anagramChecker.areAnagrams(word1, word2));
    }

    /**
     * Tests areAnagrams with two long words that are not anagrams.
     */
    @Test
    public void areAnagramsLongWordFalse() {
        String word1 = "independence";
        String word2 = "dependecnenx";

        AnagramChecker anagramChecker = new AnagramChecker();
        Assert.assertFalse(anagramChecker.areAnagrams(word1, word2));
    }

    /**
     * Tests areAnagrams with a long first word and short second word.
     */
    @Test
    public void areAnagramsLongFirstWord() {
        String word1 = "bobsled";
        String word2 = "bob";

        AnagramChecker anagramChecker = new AnagramChecker();
        Assert.assertFalse(anagramChecker.areAnagrams(word1, word2));
    }

    /**
     * Tests areAnagrams with a short first word and long second word.
     */
    @Test
    public void areAnagramsShortFirstWord() {
        String word1 = "dog";
        String word2 = "doorstep";

        AnagramChecker anagramChecker = new AnagramChecker();
        Assert.assertFalse(anagramChecker.areAnagrams(word1, word2));
    }

    /**
     * Tests areAnagrams with a second word that is the empty string.
     */
    @Test
    public void areAnagramsEmptySecondWord() {
        String word1 = "keyboard";
        String word2 = "";

        AnagramChecker anagramChecker = new AnagramChecker();
        Assert.assertFalse(anagramChecker.areAnagrams(word1, word2));
    }
}