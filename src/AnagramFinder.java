import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
// -------------------------------------------------------------------------
/**
 *  Week 1 HW #1
 *  find and return anagram from a dictionary file
 * 
 *  @author mayakkrla
 *  @version 21 May 2026
 */
public class AnagramFinder
{
    //~ Fields ................................................................
    private ArrayList<String> dictionary;
    // ----------------------------------------------------------
    /**
     * Create a new AnagramFinder object.
     * loads all words from dictionary
     * @param filename name of dictionary file
     * @throws Exception if file cannot be opened
     */
    //~ Constructors ..........................................................
    public AnagramFinder(String filename)
        throws Exception {

        dictionary = new ArrayList<>();

        try (Scanner fileScanner =
            new Scanner(new File(filename))) {

        while (fileScanner.hasNextLine()) {

            dictionary.add(fileScanner.nextLine());
        }
       }

   }
  //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * sorts letters of a word in alphabetical order
     * @param str word to sort
     * @return sorted word
     */
    public String sortString(String str) {
        // convert to lower case then array
        char[] chars =
                str.toLowerCase().toCharArray();
        // sort alphabetically
        Arrays.sort(chars);
        //convert back int string
        return new String(chars);
    }
    // ----------------------------------------------------------
    /**
     * find all anagrams  of the word 
     * @param input word being searched
     * @return arraylist containing the anagram
     */
    public ArrayList<String> findAnagrams(
        String input) {
        // store anagram
            ArrayList<String> results =
                    new ArrayList<>();
            // sort alphabetically
            String sortedInput =
                    sortString(input);
            
            //loop through every word in the dictionary 
            //sorted letters must match and not be same as word
            for (String word : dictionary) {
        
                if (!word.equalsIgnoreCase(input)
                        && sortString(word)
                        .equals(sortedInput)) {
        
                    results.add(word);
                }
        }
        // return all found anagrams
        return results;
    }
}
