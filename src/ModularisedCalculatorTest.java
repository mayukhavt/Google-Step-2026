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
     * Tests basic calculator expressions.
     */
    @Test
    public void testCalculator()
    {
        double answer1 =
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("1+2"));

        assertEquals(3.0, answer1, 1e-8);

        double answer2 =
            ModularisedCalculator.evaluate(
                ModularisedCalculator.tokenize("1.0+2.1-3"));

        assertEquals(0.1, answer2, 1e-8);
    }
}
