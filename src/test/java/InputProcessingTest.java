import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for the {@link InputProcessing} class.
 */
class InputProcessingTest {
    private static InputProcessing inputProcessing;

    @BeforeAll
    static void setUp() {
        inputProcessing = new InputProcessing(new Document());
    }

    /**
     * Tests the {@link InputProcessing#filterInvalidChars(String)} method. The expected
     * behaviour is that the method removes all invalid characters. This is a positive test.
     */
    @Test
    void testFilterInvalidChars() {
        String input = "This^èà is a_ ~text |with invalid¬ §characters°.";
        String expected = "This is a text with invalid characters.";
        Assertions.assertEquals(expected, inputProcessing.filterInvalidChars(input));
    }
}