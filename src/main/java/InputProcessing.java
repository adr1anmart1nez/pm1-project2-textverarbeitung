/**
 * Class that processes the input. It checks for invalid characters and filters them.
 *
 * @author Team03 - Stackoverflow
 * @version 1.0
 */
public class InputProcessing {
    private final Document document;

    /**
     * Initialises the document.
     *
     * @param document Sets used document in inputProcessing.
     */
    public InputProcessing(Document document) {
        this.document = document;
    }

    /**
     * Method to filter all invalid characters out of a string.
     *
     * @param textInput Text given by the user.
     * @return Returns the filtered string.
     */
    public String filterInvalidChars(String textInput) {
        //regex: all that don't match big or small letters from a to z, all numbers, ä ö u and ; : ? ! ' () [] {} & # $ /
        String inputRegex = "[^a-zA-Z0-9ÄÖÜäöü., :;!?’()\"%@+*\\[{}&#$/\\]\\-\\\\]";
        return textInput.replaceAll(inputRegex, "");
    }

    /**
     * Checks if the given index is within bounds of the document. Note that
     * the index should be 1-based, not 0-based. In other words, the first paragraph
     * has an index of 1 and the last paragraph has an index of the size of the document.
     *
     * @param index Is the position in document.
     * @return Returns true if index given is in bounds, else false.
     */
    public boolean isValidIndex(int index) {
        if (document.documentSize() == 0) {
            return index == 1;
        }
        return index > 0 && index <= document.documentSize();
    }

    /**
     * Tests if input for the add method in TextEditor is valid, +1 since userInput starts at 1.
     *
     * @param index Is the position in document.
     * @return Returns true if given index is within bounds + 1 else returns false.
     */
    public boolean isValidIndexAdd(int index) {
        if (document.documentSize() == 0) {
            return index == 1;
        }
        return index > 0 && index <= document.documentSize() + 1;
    }

    /**
     * Checks if input parameter for the method formatFix makes sense.
     *
     * @param paramInput paragraph width given by the user.
     * @return Returns true if paramInput is valid else false.
     */
    public boolean isValidCharParam(int paramInput) {
        return paramInput > 0;
    }
}
