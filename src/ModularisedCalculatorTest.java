import static org.junit.Assert.*;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 *  tests 
 * 
 *  @author mayakkrla
 *  @version 3 Jun 2026
 */
public class ModularisedCalculatorTest
{
    /**
     * Tests addition.
     */
    @Test
    public void testAddition()
    {
        assertEquals(
            3.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("1+2")),
            1e-8);
    }


    /**
     * Tests subtraction.
     */
    @Test
    public void testSubtraction()
    {
        assertEquals(
            7.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("10-3")),
            1e-8);
    }


    /**
     * Tests multiplication.
     */
    @Test
    public void testMultiplication()
    {
        assertEquals(
            12.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("3*4")),
            1e-8);
    }


    /**
     * Tests division.
     */
    @Test
    public void testDivision()
    {
        assertEquals(
            5.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("10/2")),
            1e-8);
    }


    /**
     * Tests decimal numbers.
     */
    @Test
    public void testDecimals()
    {
        assertEquals(
            0.1,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("1.0+2.1-3")),
            1e-8);
    }


    /**
     * Tests multiplication precedence.
     */
    @Test
    public void testMultiplicationPrecedence()
    {
        assertEquals(
            7.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("1+2*3")),
            1e-8);
    }


    /**
     * Tests division precedence.
     */
    @Test
    public void testDivisionPrecedence()
    {
        assertEquals(
            5.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("9-8/2")),
            1e-8);
    }


    /**
     * Tests simple parentheses.
     */
    @Test
    public void testParentheses()
    {
        assertEquals(
            9.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("(1+2)*3")),
            1e-8);
    }


    /**
     * Tests nested parentheses.
     */
    @Test
    public void testNestedParentheses()
    {
        assertEquals(
            15.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("1+(2*(3+4))")),
            1e-8);
    }


    /**
     * Tests multiple parenthesised expressions.
     */
    @Test
    public void testMultipleParentheses()
    {
        assertEquals(
            21.0,
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("(1+2)*(3+4)")),
            1e-8);
    }
}
