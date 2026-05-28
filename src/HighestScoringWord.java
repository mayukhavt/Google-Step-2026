import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
// -------------------------------------------------------------------------
/**
 *  runner class for AnagramFinderScore
 *  
 * 
 *  @author mayakkrla
 *  @version 22 May 2026
 */
public class HighestScoringWord
{

    // ----------------------------------------------------------
    /**
     * main runner method
     * @param args command line argument
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // load dictionary and store to finder
        AnagramFinderScore finder =
                new AnagramFinderScore("words.txt");
        // create input files
        String[] files = {"small.txt", "large.txt"};
        // create output file names
        for (String inputFile : files) {
            String outputFile = inputFile + "_output.txt";
            // open input file and write to output file
            Scanner sc = new Scanner(new File(inputFile));
            PrintWriter pw = new PrintWriter(outputFile);
            // remove spaces
            while (sc.hasNextLine()) {
                String input = sc.nextLine().trim();
                // skip empty lines safely
                if (input.isEmpty()) {
                    pw.println("");
                    continue;
                }
                // send to logic class and write result
                String best = finder.bestWord(input);
                pw.println(finder.scoreOf(best));
            }

            sc.close();
            pw.close();
            // status message
            System.out.println("Done: " + outputFile);
        }
    }
}