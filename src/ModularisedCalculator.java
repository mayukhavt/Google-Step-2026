import java.util.ArrayList;
import java.util.Scanner;
// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author mayakkrla
 *  @version 3 Jun 2026
 */
public class ModularisedCalculator
{
    /**
     * Token class
     */
    public static class Token
    {
        /**
         * 
         */
        String type;
        /**
         * 
         */
        double number;

        // ----------------------------------------------------------
        /**
         * Create a new Token object.
         * @param type
         */
        public Token(String type)
        {
            this.type = type;
        }

        // ----------------------------------------------------------
        /**
         * Create a new Token object.
         * @param type
         * @param number
         */
        public Token(String type, double number)
        {
            this.type = type;
            this.number = number;
        }
    }


    /**
     * Read a number token.
     */
    private static int readNumber(
        String line,
        int index,
        ArrayList<Token> tokens)
    {
        int currentIndex = index;
        double number = 0;

        while (currentIndex < line.length()
            && Character.isDigit(line.charAt(currentIndex)))
        {
            number = number * 10
                + (line.charAt(currentIndex) - '0');
            currentIndex++;
        }

        if (currentIndex < line.length()
            && line.charAt(currentIndex) == '.')
        {
            currentIndex++;

            double decimal = 0.1;

            while (currentIndex < line.length()
                && Character.isDigit(line.charAt(currentIndex)))
            {
                number += (line.charAt(currentIndex) - '0')
                    * decimal;
                decimal /= 10;
                currentIndex++;
            }
        }

        tokens.add(new Token("NUMBER", number));
        return currentIndex;
    }


    /**
     * Read plus token.
     */
    private static int readPlus(
        ArrayList<Token> tokens,
        int index)
    {
        tokens.add(new Token("PLUS"));
        return index + 1;
    }


    /**
     * Read minus token.
     */
    private static int readMinus(
        ArrayList<Token> tokens,
        int index)
    {
        tokens.add(new Token("MINUS"));
        return index + 1;
    }
    /**
     * Read multiply token.
     */
    private static int readMultiply(
        ArrayList<Token> tokens,
        int index)
    {
        tokens.add(new Token("MULTIPLY"));
        return index + 1;
    }


    /**
     * Read divide token.
     */
    private static int readDivide(
        ArrayList<Token> tokens,
        int index)
    {
        tokens.add(new Token("DIVIDE"));
        return index + 1;
    }
    /**
     * Read left paranthesis token.
     */
    private static int readLeftParen(
        ArrayList<Token> tokens,
        int index)
    {
        tokens.add(new Token("LEFT_PAREN"));
        return index + 1;
    }
    
    /**
     * Read right paranthesis token.
     */

    private static int readRightParen(
        ArrayList<Token> tokens,
        int index)
    {
        tokens.add(new Token("RIGHT_PAREN"));
        return index + 1;
    }

    /**
     * Convert input string to tokens.
     * @param line 
     * @return tokens
     */
    public static ArrayList<Token> tokenize(String line)
    {
        ArrayList<Token> tokens = new ArrayList<>();
        int index = 0;

        while (index < line.length())
        {
            char current = line.charAt(index);

            if (Character.isDigit(current))
            {
                index = readNumber(line, index, tokens);
            }
            else if (current == '+')
            {
                index = readPlus(tokens, index);
            }
            else if (current == '-')
            {
                index = readMinus(tokens, index);
            }
            else if (current == '*')
            {
                index = readMultiply(tokens, index);
            }
            else if (current == '/')
            {
                index = readDivide(tokens, index);
            }
            else if (current == '(')
            {
                index = readLeftParen(tokens, index);
            }
            else if (current == ')')
            {
                index = readRightParen(tokens, index);
            }
            else
            {
                throw new IllegalArgumentException(
                    "Invalid character: " + current);
            }
        }

        return tokens;
    }


    /**
     * Evaluate tokens.
     * @param tokens token list
     * @return answer
     */
    public static double evaluate(ArrayList<Token> tokens)
    {
        int[] index = {0};
        return evaluateAs(tokens, index);
    }


    /**
     * Handles + and -
     */
    private static double evaluateAs(
        ArrayList<Token> tokens,
        int[] index)
    {
        double value = evaluateDm(tokens, index);

        while (index[0] < tokens.size())
        {
            String type = tokens.get(index[0]).type;

            if (type.equals("PLUS"))
            {
                index[0]++;
                value += evaluateDm(tokens, index);
            }
            else if (type.equals("MINUS"))
            {
                index[0]++;
                value -= evaluateDm(tokens, index);
            }
            else
            {
                break;
            }
        }

        return value;
    }


    /**
     * Handles * and /
     */
    private static double evaluateDm(
        ArrayList<Token> tokens,
        int[] index)
    {
        double value = evaluateB(tokens, index);

        while (index[0] < tokens.size())
        {
            String type = tokens.get(index[0]).type;

            if (type.equals("MULTIPLY"))
            {
                index[0]++;
                value *= evaluateB(tokens, index);
            }
            else if (type.equals("DIVIDE"))
            {
                index[0]++;
                value /= evaluateB(tokens, index);
            }
            else
            {
                break;
            }
        }

        return value;
    }


    /**
     * Handles numbers and parentheses
     */
    private static double evaluateB(
        ArrayList<Token> tokens,
        int[] index)
    {
        Token current = tokens.get(index[0]);

        if (current.type.equals("NUMBER"))
        {
            index[0]++;
            return current.number;
        }

        if (current.type.equals("LEFT_PAREN"))
        {
            index[0]++;

            double value =
                evaluateAs(tokens, index);

            index[0]++;

            return value;
        }

        throw new IllegalArgumentException(
            "Unexpected token");
    }


    /**
     * Main method.
     *
     * @param args command-line arguments
     */
    @SuppressWarnings("resource")
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.print("> ");

            String line = scanner.nextLine();

            ArrayList<Token> tokens = tokenize(line);
            double answer = evaluate(tokens);

            System.out.printf("answer = %f%n%n", answer);
        }
    } 

}
