import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

/**
 * Test class for the {@link Directory} class.<br>
 *
 * <br>This test class has the following equivalence classes:
 * <br>01. TestingOccurrencesUpperCaseOnLimit: Are all words that occurs 4 times are not count.
 * <br>02. TestingOccurrencesUpperCaseUnderLimit: Are all words that occurs less than 4 times are not count.
 * <br>03. TestingOccurrencesUpperCaseAboveLimit: Are all words that occurs more than 4 times count?
 * <br>04. TestingOccurrencesLowerCase: Words who start with lowercase letters don't count.
 * <br>05. TestingOccurrencesSpecialChars: Tests the filter to remove special chars.
 * <br>06. TestingOccurrencesInvalid: Tests with unwanted/invalid inputs.
 * <br>07. TestingIndicesAboveLimit: Are the indices right for words who occur more than 4 times.
 * <br>08. TestingIndicesOnLimit: Are the indices right for words who occur 4 times.
 * <br>09. TestingIndicesUnderLimit: Are the indices right for words who occur less than 4 times.
 * <br>10. TestingIndicesLowerCase: Words that start with a lower case letter are not considered in the directory.
 * <br>11. TestingIndicesNumbers: Numbers are not considered in the directory.
 * <br>12. TestingIndicesInvalid: Tests with unwanted/invalid input.
 * <br>13. TestWholeText: Tests with a full text.
 *
 * @author Louise Andermatt
 * @version 1.0
 */
class DirectoryTest {
    private static Directory directory;
    private final String dummy = "Die Äquivalenzrelation „hat die gleiche Farbe wie“ angewendet Äquivalenzklassen" +
            "auf das Skat-Spiel hat vier Äquivalenzklassen, nämlich die vier Farben der Spielkarten Äquivalenzklassen." +
            "Die Äquivalenzrelation „hat den gleichen Wert wie“ angewendet auf das Skat-Spiel hat acht Äquivalenzklassen" +
            " Äquivalenzklassen, nämlich die Quartette der Karten 7, 8, 9, 10, Bube, Dame, König, Ass. Äquivalenzklassen";

    @BeforeAll
    static void setUp() {
        directory = new Directory();
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks if countOccurrences works for a whole text.
     * The expected behaviour is that terms starting with letter in upper case are counted. This is a
     * positive test. The equivalence class is 13.
     */
    @Test
    void testOccurrencesWholeText() {

        HashMap<String, Integer> wordCounts = directory.countOccurrences(dummy);
        assertEquals(2, wordCounts.get("Äquivalenzrelation"));
    }
    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that
     * words that start with an upper case letter are counted correctly. The expected
     * behaviour is that terms starting with letter in upper case are counted. This is a
     * positive test. The equivalence class is 1.
     */
    @Test
    void testOccurrencesUpperCase4Times() {
        String input = "Das Wort kommt hier 4x vor. Wort Wort Wort";
        HashMap<String, Integer> wordCounts = directory.countOccurrences(input);
        assertNotNull(wordCounts.get("Wort"));
        assertEquals(4, wordCounts.get("Wort"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that
     * words that start with an upper case letter are counted correctly. The expected
     * behaviour is that terms starting with letter in upper case are counted. This is a
     * positive test. The equivalence class is 2.
     */
    @Test
    void testOccurrencesUpperCase1Time() {
        String input = "Das Wort kommt hier 1x vor.";
        HashMap<String, Integer> wordCounts = directory.countOccurrences(input);
        assertNotNull(wordCounts.get("Wort"));
        assertEquals(1, wordCounts.get("Wort"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that
     * words that start with an upper case letter are counted correctly. The expected
     * behaviour is that terms starting with letter in upper case are counted. This is a
     * positive test. The equivalence class is 3.
     */
    @Test
    void testOccurrencesUpperCase6Times() {
        String input = "Das Wort kommt hier 6x vor. Wort Wort Wort Wort Wort";
        HashMap<String, Integer> wordCounts = directory.countOccurrences(input);
        assertNotNull(wordCounts.get("Wort"));
        assertEquals(6, wordCounts.get("Wort"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that words
     * that start with a lower case letter are ignored. The expected behaviour is that
     * only terms starting with letter in upper case are counted. This is a positive test.
     * The equivalence class is 4.
     */
    @Test
    void testOccurrencesLowerCase() {
        String input = "Das Wort kommt hier 3x vor. wort wort Wort Wort";
        HashMap<String, Integer> wordCounts = directory.countOccurrences(input);
        assertNull(wordCounts.get("wort"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that
     * words that contain special characters are counted correctly. The expected
     * behavior is that the special characters are removed. This is a positive test.
     * The equivalence class is 5.
     */
    @Test
    void testOccurrencesWithSpecialChars() {
        String input = "Das !?Wort sollte als Wort erkennt werden.";
        HashMap<String, Integer> wordCounts = directory.countOccurrences(input);
        assertNotNull(wordCounts.get("Wort"));
        assertEquals(2, wordCounts.get("Wort"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that words
     * that start with numbers are ignored, since only terms starting with a letter in
     * upper case should be counted. This is a negative test.
     * The equivalence class is 6.
     */
    @Test
    void testOccurrencesOnlyNumbers() {
        String input = "Die 123Zahlen kommen nicht ins Verzeichnis.";
        HashMap<String, Integer> wordCounts = directory.countOccurrences(input);
        assertNull(wordCounts.get("123Zahlen"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that
     * null as input doesn't cause an exception. The method is expected to return
     * <code>null</code>. This is a negative test.
     * The equivalence class is 6.
     */
    @Test
    void testOccurrencesNull() {
        HashMap<String, Integer> wordCounts = directory.countOccurrences(null);
        assertNull(wordCounts);
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method and checks that
     * an empty String as input doesn't cause an exception and isn't counted. This
     * is a negative test. The equivalence class is 6.
     */
    @Test
    void testOccurrencesEmptyString() {
        String input = "";
        HashMap<String, Integer> wordCounts = directory.countOccurrences(input);
        assertFalse(wordCounts.containsKey(""));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method. The expected
     * behaviour is that the index of each paragraph where a term occurs is only
     * displayed once, no matter how many times that term occurs within the
     * paragraph. This is a positive test. The equivalence class is 7.
     */
    @Test
    void testIndicesOnlyOncePerParagraph8Times() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add("Lorem");
        paragraphs.add("Ipsum Ipsum Ipsum Ipsum");
        paragraphs.add("Ipsum Ipsum Ipsum Ipsum");

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertEquals(1, result.size());
        assertEquals(new ArrayList<>(List.of(2, 3)), result.get("Ipsum"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method. The expected
     * behaviour is that the index of each paragraph where a term occurs is only
     * displayed once, no matter how many times that term occurs within the
     * paragraph. This is a positive test. The equivalence class is 8.
     */
    @Test
    void testIndicesOnlyOncePerParagraph4Times() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add("Lorem");
        paragraphs.add("Ipsum Ipsum Ipsum");
        paragraphs.add("Ipsum");

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertEquals(1, result.size());
        assertEquals(new ArrayList<>(List.of(2, 3)), result.get("Ipsum"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method. The expected
     * behaviour is that the index of each paragraph where a term occurs is only
     * displayed once, no matter how many times that term occurs within the
     * paragraph. This is a positive test. The equivalence class is 9.
     */
    @Test
    void testIndicesOnlyOncePerParagraph2Times() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add("Lorem");
        paragraphs.add("Ipsum Ipsum");
        paragraphs.add("Lorem");

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertEquals(0, result.size());
        assertNull(result.get("Ipsum"));
        assertNull(result.get("Lorem"));
    }

    /**
     * Tests the {@link Directory#countOccurrences(String)} method. The expected
     * behaviour is that terms starting with a lower case letter are ignored, even
     * if they occur four or more times. This is a positive test.
     * The equivalence class is 10.
     */
    @Test
    void testIndicesOnlyLowercase() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add("lorem");
        paragraphs.add("ipsum ipsum ipsum ipsum");

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests the {@link Directory#getIndices(ArrayList)} method. The expected
     * behaviour is that numbers are ignored since only terms starting with an
     * upper case letter should be counted. This is a positive test.
     * The equivalence class is 11.
     */
    @Test
    void testIndicesOnlyNumbers() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add("0 1, 2, 2 1 2");
        paragraphs.add("2, 1, 0 0");
        paragraphs.add("3, 3 3 3! 1");

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests the {@link Directory#getIndices(ArrayList)} method and checks that
     * empty paragraphs are ignored since only terms starting with an upper case
     * letter should be counted. This is a positive test.
     * The equivalence class is 12.
     */
    @Test
    void testIndicesEmptyParagraphs() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add("");
        paragraphs.add("");
        paragraphs.add("");
        paragraphs.add("");

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertFalse(result.containsKey(""));
        assertTrue(result.isEmpty());
    }

    /**
     * Tests the {@link Directory#getIndices(ArrayList)} method and checks that
     * <code>null</code> as input does not throw an exception. The expected
     * behavior is that the method returns <code>null</code>. This is a negative test.
     * The equivalence class is 12.
     */
    @Test
    void testIndicesInputNull() {
        HashMap<String, ArrayList<Integer>> result = directory.getIndices(null);
        assertNull(result);
    }

    /**
     * Tests the {@link Directory#getIndices(ArrayList)} method and checks that a
     * paragraph being null doesn't cause an exception and instead is just ignored.
     * This is a negative test. The equivalence class is 12.
     */
    @Test
    void testIndicesNullParagraphs() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add("Lorem");
        paragraphs.add(null);
        paragraphs.add("Ipsum");

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests the {@link Directory#getIndices(ArrayList)} method and checks that the getIndices also works for a whole text.
     * This is a negative test. The equivalence class is 13.
     */
    @Test
    void testIndicesWholeText() {
        ArrayList<String> paragraphs = new ArrayList<>();
        paragraphs.add(dummy);
        paragraphs.add(dummy);

        HashMap<String, ArrayList<Integer>> result = directory.getIndices(paragraphs);
        assertEquals(4, result.size());
        assertEquals(new ArrayList<>(List.of(1, 2)), result.get("Äquivalenzklassen"));
        assertEquals(new ArrayList<>(List.of(1, 2)), result.get("Äquivalenzrelation"));
    }
}