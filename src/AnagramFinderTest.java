import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  tests anagramfinder
 * 
 *  @author mayakkrla
 *  @version 21 May 2026
 */
public class AnagramFinderTest
{

    // ----------------------------------------------------------
    /**
     * basic test case
     * @throws Exception
     */
    @Test
    public void testBasicAnagram() throws Exception {

        AnagramFinder finder =
                new AnagramFinder("words.txt");

        ArrayList<String> result =
                finder.findAnagrams("listen");

        assertTrue(result.contains("silent"));
    }
    // ----------------------------------------------------------
    /**
     * basic negative test case
     * @throws Exception
     */
    @Test
    public void testNoAnagramFound() throws Exception {

        AnagramFinder finder =
                new AnagramFinder("words.txt");

        ArrayList<String> result =
                finder.findAnagrams("zzzzzz");

        assertTrue(result.isEmpty());
    }

    // ----------------------------------------------------------
    /**
     * minimum input case
     * @throws Exception
     */
    @Test
    public void testVeryShortWord() throws Exception {

        AnagramFinder finder =
                new AnagramFinder("words.txt");

        ArrayList<String> result =
                finder.findAnagrams("a");

        assertTrue(result.isEmpty());
    }

    // ----------------------------------------------------------
    /**
     * stress test
     * @throws Exception
     */
    @Test
    public void testVeryLongWord() throws Exception {

        AnagramFinder finder =
                new AnagramFinder("words.txt");

        String longWord =
                "internationalization";

        ArrayList<String> result =
                finder.findAnagrams(longWord);

        assertNotNull(result);
    }

    // ----------------------------------------------------------
    /**
     * test missing input
     * @throws Exception
     */
    @Test
    public void testEmptyString() throws Exception {

        AnagramFinder finder =
                new AnagramFinder("words.txt");

        ArrayList<String> result =
                finder.findAnagrams("");

        assertTrue(result.isEmpty());
    }
    // ----------------------------------------------------------
    /**
     * checks if function returns more than one result
     * @throws Exception
     */
    @Test
    public void testMultipleAnagrams() throws Exception {

        AnagramFinder finder =
                new AnagramFinder("words.txt");

        ArrayList<String> result =
                finder.findAnagrams("evil");

        assertTrue(result.size() >= 3);
        assertTrue(result.contains("vile"));
        assertTrue(result.contains("live"));
        assertTrue(result.contains("veil"));
    }

}
