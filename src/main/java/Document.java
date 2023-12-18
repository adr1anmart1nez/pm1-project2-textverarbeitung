import java.util.ArrayList;

/**
 * Class that processes the input. It checks for invalid
 * characters and filters them.
 *
 * @author Team03 - Stackoverflow
 * @version 1.0
 */
public class Document {

    private final ArrayList<String> document;

    /**
     * Initialises the document with an empty ArrayList.
     */
    public Document() {
        document = new ArrayList<>();
    }

    public ArrayList<String> getDocument() {
        return document;
    }

    /**
     * Adds a paragraph to the existing document.
     *
     * @param text  input which is being added to the document.
     * @param index place at which the paragraph is being added.
     */
    public void addParagraph(String text, int index) {
        document.add(index - 1, text);
    }

    /**
     * Replaces a word with a new one in a selected paragraph.
     *
     * @param toReplace   Word, which is being replaced in the paragraph.
     * @param replacement Word, which is used to replace original word in paragraph.
     * @param index       Number of the paragraph in which the word is going to be replaced.
     */
    public void replaceParagraph(String toReplace, String replacement, int index) {
        String paragraph = document.get(index - 1);
        paragraph = paragraph.replace(toReplace, replacement);

        document.remove(index - 1);
        document.add(index - 1, paragraph);
    }

    /**
     * Deletes a paragraph from the document.
     *
     * @param index Position of the paragraph which is being deleted.
     */
    public void deleteParagraph(int index) {
        document.remove(index - 1);
    }

    /**
     * Adds a dummy text to the document.
     *
     * @param index Position in document where dummy is being added.
     */
    public void addDummy(int index) {
        String dummy = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
                "do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Scelerisque eleifend donec pretium vulputate sapien. Mi proin " +
                "sed libero enim sed faucibus turpis in. Et netus et malesuada " +
                "fames ac turpis egestas sed tempus. Sit amet nulla facilisi " +
                "morbi tempus iaculis urna. Tellus molestie nunc non blandit " +
                "massa enim nec. Quam lacus suspendisse faucibus interdum posuere " +
                "lorem ipsum. Pretium nibh ipsum consequat nisl vel pretium. Et " +
                "molestie ac feugiat sed lectus vestibulum. At varius vel " +
                "pharetra vel turpis nunc eget lorem dolor. Vulputate enim nulla " +
                "aliquet porttitor lacus luctus accumsan tortor.";
        document.add(index - 1, dummy);
    }

    /**
     * Returns the number of paragraphs in the document.
     *
     * @return An integer representing the number of paragraphs in the document.
     */
    public int documentSize() {
        return document.size();
    }
}