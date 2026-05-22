import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
// -------------------------------------------------------------------------
/**
 *  Week 1 HW #2
 *  Scores anagrams 
 * 
 *  @author mayakkrla
 *  @version 21 May 2026
 */
public class AnagramFinderScore
{
    //~ Fields ................................................................
    private ArrayList<String> dictionary;

    
    //~ Constructors ..........................................................
    /**
     * loads dictionary from file
     * @param filename 
     * @throws Exception 
     */
    public AnagramFinderScore(String filename) throws Exception {
        dictionary = new ArrayList<>();
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            dictionary.add(sc.nextLine().trim());
        }

        sc.close();
    }
    // private helpers
    //converts word into letter counts
    private int[] buildFreq(String word) {
        int[] freq = new int[26];
        for (char c : word.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                //maps letter to index
                freq[c - 'a']++;
            }
        }
        return freq;
    }
    // checks if word exists using input letters
    private boolean canForm(String word, int[] inputFreq) {
        int[] wordFreq = buildFreq(word);
        // checks if word has enough letters
        // returns false if needs more letters
        for (int i = 0; i < 26; i++) {
            if (wordFreq[i] > inputFreq[i]) {
                return false;
            }
        }
        return true;
    }
    // calculates word val
    private int score(String word) { 
        int sum = 0;
        for (char c : word.toLowerCase().toCharArray()) {
            // 1 point: a, e, h, i, n, o, r, s, t 
            // 2 points: c, d, l, m, u
            // 3 points: b, f, g, p, v, w, y
            // 4 points: j, k, q, x, z
            int[] scores = {1, 3, 2, 2, 1, 3, 3, 1, 1, 4, 4, 2, 2, 1, 1, 3, 
                4, 1, 1, 1, 2, 3, 3, 4, 3, 4};

            if (c >= 'a' && c <= 'z') {
                // assigns score
                sum += scores[c - 'a'];
            }
        }
        return sum;
    }
    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * checks and returns highest scoring word
     * @param input the word to be checked
     * @return highest scoring word
     */
    public String bestWord(String input) {
        // converts input into letter count
        int[] inputFreq = buildFreq(input);
        // stores best word and score
        String best = "";
        int bestScore = 0;
        // if valid calculate best score
        for (String word : dictionary) {
            if (canForm(word, inputFreq)) {
                int s = score(word);
                // checks if better than prev word
                //update result
                if (s > bestScore) {
                    bestScore = s;
                    best = word;
                }
            }
        }
        return best;
    }
}