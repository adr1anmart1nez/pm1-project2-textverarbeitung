import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides methods to format the paragraphs.
 *
 * @author Team03 - Stackoverflow
 * @version 1.0
 */

public class FormatText {

    private int amountOfChars = 0;
    private int remainingParagraphLength = 0;
    private String formattedParagraph = "";

    public void setAmountOfChars(int amountOfChars) {
        this.amountOfChars = amountOfChars;
    }

    /**
     * FormatRaw sets the current Arraylist-Text into a new format it numbers all
     * the existing paragraphs.
     *
     * @param document Document provides the text which needs to be formatted.
     * @return Returns the document as an ArrayList in formatRaw.
     */
    public ArrayList<String> formatRaw(ArrayList<String> document) {
        ArrayList<String> documentTextInParagraphs = new ArrayList<>();
        for (int i = 0; i < document.size(); i++) {
            int paragraphNumber = i + 1;
            documentTextInParagraphs.add(i, "<" + paragraphNumber + ">: <" + document.get(i) + ">");
        }
        return documentTextInParagraphs;
    }

    /**
     * FormatFix sets the current Arraylist-Text into a new format after x amount
     * of characters it adds a newLine based on multiple conditions.
     *
     * @param document Document provides the text which needs to be formatted.
     * @return Returns the document as an ArrayList in formatFix.
     */
    public ArrayList<String> formatFix(List<String> document) {
        int paragraphIndex = 0;
        ArrayList<String> formattedText = new ArrayList<>();
        for (String paragraph : document) {
            remainingParagraphLength = amountOfChars;
            String[] words = paragraph.split(" ");
            for (String word : words) {
                if (amountOfChars - word.length() < 0) {
                    addWhitespace(amountOfChars);
                    wordIsLongerThanParagraphLength(word, amountOfChars);
                } else if (remainingParagraphLength - word.length() > 0) {
                    addWhitespace(amountOfChars);
                    wordFitsInParagraph(word);
                } else if (remainingParagraphLength - word.length() == 0) {
                    addWhitespace(amountOfChars);
                    wordBarelyFitsInParagraph(word);
                } else if (remainingParagraphLength - word.length() < 0) {
                    addWhitespace(amountOfChars);
                    wordDoesNotFitInRemainingParagraph(word, amountOfChars);
                }
            }
            formattedText.add(paragraphIndex, formattedParagraph);
            formattedParagraph = "";
            paragraphIndex++;
        }
        return formattedText;
    }

    private void wordIsLongerThanParagraphLength(String word, int paragraphLength) {
        boolean wordIsLongerThanParagraph = true;
        while (wordIsLongerThanParagraph) {
            if (remainingParagraphLength != paragraphLength) {
                formattedParagraph += "\n";
                remainingParagraphLength = paragraphLength;
            }
            formattedParagraph += word.substring(0, remainingParagraphLength) + " \n";
            word = word.substring(remainingParagraphLength);
            if (remainingParagraphLength - word.length() == 0) {
                wordIsLongerThanParagraph = false;
                wordBarelyFitsInParagraph(word);
            } else if (remainingParagraphLength - word.length() > 0) {
                wordIsLongerThanParagraph = false;
                wordFitsInParagraph(word);
            }
        }
    }

    private void wordFitsInParagraph(String word) {
        formattedParagraph += word;
        remainingParagraphLength = remainingParagraphLength - word.length() - 1;
    }

    private void wordBarelyFitsInParagraph(String word) {
        formattedParagraph += word;
        remainingParagraphLength = 0;
    }

    private void wordDoesNotFitInRemainingParagraph(String word, int paragraphLength) {
        formattedParagraph += "\n" + word;
        remainingParagraphLength = paragraphLength - word.length() - 1;
    }

    private void addWhitespace(int paragraphLength) {
        if (remainingParagraphLength != paragraphLength) {
            formattedParagraph += " ";
        }
    }
}