import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
// -------------------------------------------------------------------------
/**
 *  Week 1 HW #2
 *  Finds the highest scoring set of words for anagrams
 * 
 *  @author mayakkrla
 *  @version 21 May 2026
 */
public class AnagramFinderMultiple
{
    //~ Fields ................................................................
    private ArrayList<String> dictionary;
    private static final int[] SCORES = {1, 3, 2, 2, 1, 3, 3, 1, 1, 4, 4, 2, 2, 1, 1, 3, 4, 1, 1, 1, 2, 3, 3, 4, 3, 4};
    private ArrayList<String> bestSet;
    private int maxScore;

    
    //~ Constructors ..........................................................
    /**
     * loads dictionary from file
     * @param filename 
     * @throws Exception 
     */
    public AnagramFinderMultiple(String filename) throws Exception {
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
    // checks if word freq can be formed using input letters
    private boolean canForm(int[] wordFreq, int[] inputFreq) {
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
            if (c >= 'a' && c <= 'z') {
                // assigns score
                sum += SCORES[c - 'a'];
            }
        }
        return sum;
    }

    private class WordInfo {
        String word;
        int[] freq;
        int score;

        WordInfo(String w, int[] f) {
            this.word = w;
            this.freq = f;
            this.score = score(w);
        }
    }

    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * checks and returns highest scoring set of words
     * @param input the word to be checked
     * @return highest scoring set of words
     */
    public ArrayList<String> bestWords(String input) {
        // converts input into letter count
        int[] inputFreq = buildFreq(input);
        
        ArrayList<WordInfo> validWords = new ArrayList<>();
        for (String word : dictionary) {
            int[] wFreq = buildFreq(word);
            if (canForm(wFreq, inputFreq)) {
                WordInfo wi = new WordInfo(word, wFreq);
                if (wi.score > 0) {
                    validWords.add(wi);
                }
            }
        }
        
        bestSet = new ArrayList<>();
        maxScore = 0;
        
        backtrack(validWords, inputFreq, new ArrayList<>(), 0, 0);
        
        return bestSet;
    }

    private void backtrack(ArrayList<WordInfo> validWords, int[] currentFreq, ArrayList<String> currentSet, int currentScore, int startIndex) {
        if (currentScore > maxScore) {
            maxScore = currentScore;
            bestSet = new ArrayList<>(currentSet);
        }
        
        int maxPossibleRemainingScore = 0;
        for (int j = 0; j < 26; j++) {
            maxPossibleRemainingScore += currentFreq[j] * SCORES[j];
        }
        if (currentScore + maxPossibleRemainingScore <= maxScore) {
            return;
        }

        for (int i = startIndex; i < validWords.size(); i++) {
            WordInfo wi = validWords.get(i);
            
            if (canForm(wi.freq, currentFreq)) {
                for (int j = 0; j < 26; j++) {
                    currentFreq[j] -= wi.freq[j];
                }
                currentSet.add(wi.word);
                
                backtrack(validWords, currentFreq, currentSet, currentScore + wi.score, i);
                
                currentSet.remove(currentSet.size() - 1);
                for (int j = 0; j < 26; j++) {
                    currentFreq[j] += wi.freq[j];
                }
            }
        }
    }
}