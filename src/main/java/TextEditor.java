import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that represents the text editor itself. It handles the connection and
 * communication between its components.
 *
 * @author Team03 - Stackoverflow
 * @version 1.0
 */
public class TextEditor {
    private final Communication editorCommunication;
    private final InputProcessing editorInputProcessing;
    private final Document document;
    private final FormatText formatText;
    private final Directory directory;
    private boolean isRunning;
    private boolean formatRaw;

    /**
     * Initialises all the Instance fields. Creates new objects for all the used classes.
     */
    public TextEditor() {
        document = new Document();
        editorCommunication = new Communication();
        editorInputProcessing = new InputProcessing(document);
        formatText = new FormatText();
        directory = new Directory();
        isRunning = true;
        formatRaw = true;
    }

    /**
     * Starts the textEditor and keeps it running until the user exits it.
     */
    public void run() {
        editorCommunication.printGuide();
        while (isRunning) {
            String input = editorCommunication.readCommand();
            processInput(input);
        }
    }

    /**
     * Reads the input from user and calls the corresponding method.
     *
     * @param input A String entered by the user.
     */
    private void processInput(String input) {
        input = input.trim();
        int documentSize = document.documentSize();

        // Regex: String starts with 'add' and any number follows. Case is irrelevant.
        if (input.matches("(?i)ADD \\d+")) {
            int index = getNumberFromInput(input);
            addParagraph(index);
        }
        // Regex: String equals 'add'. Case is irrelevant.
        else if (input.matches("(?i)ADD")) {
            addParagraph(documentSize + 1);
        }
        // Regex: String starts with 'replace' and any number follows. Case is irrelevant.
        else if (input.matches("(?i)REPLACE \\d+")) {
            int index = getNumberFromInput(input);
            replaceParagraph(index);
        }
        // Regex: String equals 'replace'. Case is irrelevant.
        else if (input.matches("(?i)REPLACE")) {
            replaceParagraph(documentSize);
        }
        // Regex: String starts with 'del' and any number follows. Case is irrelevant.
        else if (input.matches("(?i)DEL \\d+")) {
            int index = getNumberFromInput(input);
            deleteParagraph(index);
        }
        // Regex: String equals 'del'. Case is irrelevant.
        else if (input.matches("(?i)DEL")) {
            deleteParagraph(documentSize);
        }
        // Regex: String starts with 'dummy' and any number follows. Case is irrelevant.
        else if (input.matches("(?i)DUMMY \\d+")) {
            int index = getNumberFromInput(input);
            addDummy(index);
        }
        // Regex: String equals 'dummy'. Case is irrelevant.
        else if (input.matches("(?i)DUMMY")) {
            addDummy(documentSize + 1);
        }
        // Regex: String equals 'format raw'. Case is irrelevant.
        else if (input.matches("(?i)FORMAT RAW")) {
            formatRaw = true;
            editorCommunication.printSuccess("FORMAT_RAW");
        }
        // Regex: String starts with 'format fix' and any number follows. Case is irrelevant.
        else if (input.matches("(?i)FORMAT FIX \\d+")) {
            setFormatFix(input);
        }
        // Regex: String equals 'index'. Case is irrelevant.
        else if (input.matches("(?i)INDEX")) {
            printIndex();
        }
        // Regex: String equals 'print'. Case is irrelevant.
        else if (input.matches("(?i)PRINT")) {
            print();
        }
        // Regex: String equals 'exit'. Case is irrelevant.
        else if (input.matches("(?i)EXIT")) {
            exit();
        } else {
            editorCommunication.printErrorCommandInvalid();
        }
    }

    /**
     * Adds a paragraph to the document.
     *
     * @param index The position where the user would like to add a paragraph.
     */
    private void addParagraph(int index) {
        if (!editorInputProcessing.isValidIndexAdd(index)) {
            editorCommunication.printErrorIndexInvalid(index);
            return;
        }

        String newParagraph = editorCommunication.readText();
        newParagraph = editorInputProcessing.filterInvalidChars(newParagraph);

        document.addParagraph(newParagraph, index);

        if (index == document.documentSize()) {
            editorCommunication.printSuccess("ADD");
        } else {
            editorCommunication.printSuccess("ADD", index);
        }
    }

    /**
     * Replaces a defined string with another string within a paragraph.
     *
     * @param index The index of the paragraph where the String should be replaced.
     */
    private void replaceParagraph(int index) {
        if(document.documentSize() == 0){
            editorCommunication.printErrorNoDocument();
            return;
        }
        if (!editorInputProcessing.isValidIndex(index)) {
            editorCommunication.printErrorIndexInvalid(index);
            return;
        }

        String toReplace = editorCommunication.readCommandTextToReplace();
        String replacement = editorCommunication.readCommandTextNew();
        document.replaceParagraph(toReplace, replacement, index);
        editorCommunication.printSuccess("REPLACE", toReplace, replacement);
    }

    /**
     * Deletes a paragraph.
     *
     * @param index The position of the paragraph the user would like to delete.
     */
    private void deleteParagraph(int index) {
        if(document.documentSize() == 0){
            editorCommunication.printErrorNoDocument();
            return;
        }
        if (!editorInputProcessing.isValidIndex(index)) {
            editorCommunication.printErrorIndexInvalid(index);
            return;
        }
        document.deleteParagraph(index);
        if (index == document.documentSize()) {
            editorCommunication.printSuccess("DEL");
        } else {
            editorCommunication.printSuccess("DEL", index);
        }
    }

    /**
     * Adds a dummy text.
     *
     * @param index The position where the user would like to add a dummy text.
     */
    private void addDummy(int index) {
        if (!editorInputProcessing.isValidIndexAdd(index)) {
            editorCommunication.printErrorIndexInvalid(index);
            return;
        }

        document.addDummy(index);
        if (index == document.documentSize()) {
            editorCommunication.printSuccess("DUMMY");
        } else {
            editorCommunication.printSuccess("DUMMY", index);
        }
    }

    /**
     * Sets the format to format fix.
     *
     * @param input The command which gives us the length of a line.
     */
    private void setFormatFix(String input) {
        int amountOfChars = getNumberFromInput(input);
        if (editorInputProcessing.isValidCharParam(amountOfChars)) {
            formatRaw = false;
            formatText.setAmountOfChars(amountOfChars);
            editorCommunication.printSuccess("FORMAT_FIX", amountOfChars);
        } else {
            editorCommunication.printErrorCommandInvalid();
        }
    }

    /**
     * Gets the index and then lets it be printed.
     */
    private void printIndex() {
        ArrayList<String> textToIndex = document.getDocument();
        HashMap<String, ArrayList<Integer>> indices = directory.getIndices(textToIndex);
        if (indices.isEmpty()) {
            editorCommunication.printErrorNoWordsDirectory();
        } else {
            editorCommunication.printDirectory(indices);
        }
    }

    /**
     * Lets the document be printed in the saved format.
     */
    private void print() {
        ArrayList<String> textToPrint = document.getDocument();
        if (document.documentSize() == 0){
            editorCommunication.printErrorNoDocument();
            return;
        }
        if (formatRaw) {
            textToPrint = formatText.formatRaw(textToPrint);
        } else {
            textToPrint = formatText.formatFix(textToPrint);
        }
        editorCommunication.printText(textToPrint);
    }

    /**
     * Exits the TextEditor and announces it to the user via Communication.
     */
    private void exit() {
        isRunning = false;
        editorCommunication.printSuccess("EXIT");
    }

    /**
     * Reads the index from the input given by the user.
     *
     * @param input The full input of the user.
     * @return The index given by the user.
     */
    private Integer getNumberFromInput(String input) {
        // Regex: All symbols that are no numbers.
        String number = input.replaceAll("[^0-9]", "");
        return Integer.parseInt(number);
    }
}