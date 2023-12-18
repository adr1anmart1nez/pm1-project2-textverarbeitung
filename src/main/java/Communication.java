import java.util.*;

/**
 * Class that communicates with the user. In other words, it reads the input and prints the output.
 *
 * @author Team03 - Stackoverflow
 * @version 1.0
 */
public class Communication {
    private final Scanner scanner = new Scanner(System.in);
    private final LinkedHashMap<String, String> guide = new LinkedHashMap<>();
    private final HashMap<String, String> dialogue = new HashMap<>();

    /**
     * Initialises and fills the guide and dialogue HashMaps.
     */
    public Communication() {
        fillGuide();
        fillDialogue();
    }

    /**
     * Reads the commands and inputs from the console and returns it in a String.
     *
     * @return The line from the console in a String.
     */
    public String readCommand() {
        System.out.print("Geben Sie einen Befehl ein: ");
        return scanner.nextLine();
    }

    /**
     * Reads the text from the console and returns it in a String.
     *
     * @return The line from the console in a String.
     */
    public String readText() {
        System.out.println("Geben Sie denn Text ein.");
        return scanner.nextLine();
    }

    /**
     * Reads the String to be replaced by the <code>REPLACE</code> command.
     *
     * @return The line from the console in a String.
     */
    public String readCommandTextToReplace() {
        System.out.print("Dieser Text soll ersetzt werden: ");
        return scanner.nextLine();
    }

    /**
     * Reads the String that is replacing another String by the <code>REPLACE</code> command.
     *
     * @return The line from the console in a String.
     */
    public String readCommandTextNew() {
        System.out.print("Mit folgendem Text ersetzen: ");
        return scanner.nextLine();
    }

    /**
     * Fills the guide HashMap with the commands and their respective description.
     */
    private void fillGuide() {
        guide.put("ADD [n]", "Fügen Sie einen Absatz ein mit dem Index n.");
        guide.put("ADD", "Fügen Sie einen Absatz am Schluss hinzu.");
        guide.put("DEL [n]", "Löschen Sie den Absatz mit dem Index n.");
        guide.put("DEL", "Löschen Sie den Absatz am Schluss.");
        guide.put("DUMMY [n]", "Fügen Sie einen Platzhaltertext beim Absatz n hinzu.");
        guide.put("DUMMY", "Fügen Sie einen Platzhaltertext am Schluss hinzu.");
        guide.put("REPLACE [n]", "Eine gewünschte Sequenz wird im Paragraph mit Index n ersetzt.");
        guide.put("REPLACE", "Eine gewünschte Sequenz wird im letzten Paragraph ersetzt.");
        guide.put("FORMAT RAW", "Stelle das Format auf RAW. Somit wird der ganze Text ohne Formatierung und mit Nummerierung der Paragraphen gestaltet.");
        guide.put("FORMAT FIX <b>", "Der Paragraph wird jeweils nach b-Zeichen umgebrochen (b > 0). Zudem wird der Text ohne Nummerierung ausgegeben.");
        guide.put("INDEX", "Geben Sie aus, welches Wort wie viel mal im Text vorkommt.");
        guide.put("PRINT", "Geben Sie den gesamten Text aus. Ist das Format bereits gesetzt?");
        guide.put("EXIT", "Der Texteditor wird verlassen.");
    }

    /**
     * Prints the guide in the console.
     */
    public void printGuide() {
        System.out.println("******************************");
        for (String command : guide.keySet()) {
            String description = guide.get(command);
            System.out.println(command + ": " + description);
        }
        System.out.println("******************************");
    }

    /**
     * Fills the dialogue HashMap that tells the user what is currently happening.
     */
    private void fillDialogue() {
        dialogue.put("ADD", "Der Text wurde erfolgreich hinzugefügt.");
        dialogue.put("DEL", "Der Text wurde erfolgreich gelöscht.");
        dialogue.put("DUMMY", "Der Platzhalter wurde erfolgreich eingefügt.");
        dialogue.put("REPLACE", "Das Austauschen hat funktioniert");
        dialogue.put("EXIT", "Der Texteditor wurde verlassen.");
        dialogue.put("FORMAT_RAW", "Der Text hat jetzt das Format RAW");
        dialogue.put("FORMAT_FIX", "Der Text hat jetzt das Format FIX");
    }

    /**
     * Prints a feedback to the user after an input.
     *
     * @param command         String given with the key of the guide.
     * @param paragraphNumber Int given with the paragraphNumber of the paragraph changes were made to.
     */
    public void printSuccess(String command, int paragraphNumber) {
        if (command.equals("ADD") || command.equals("DEL") || command.equals("DUMMY")) {
            System.out.println(dialogue.get(command) + " (Position: " + paragraphNumber + ")");
        }
        if (command.equals("FORMAT_FIX")) {
            System.out.println(dialogue.get(command) + " (Umbruch: " + paragraphNumber + " Zeichen)");
        }
    }

    /**
     * Prints a feedback to the user after an input.
     *
     * @param command      String given with the key of the guide.
     * @param replacedText String with the text that has been replaced.
     * @param newText      String with the new text that replaced the old one.
     */
    public void printSuccess(String command, String replacedText, String newText) {
        if (command.equals("REPLACE")) {
            System.out.println(dialogue.get(command));
            System.out.println("Ursprünglicher Text: " + replacedText);
            System.out.println("Neuer Text: " + newText);
        }
    }

    /**
     * Prints a feedback to the user after an input.
     *
     * @param command String given with the key of the guide.
     */
    public void printSuccess(String command) {
        if (command.equals("ADD") || command.equals("DEL") || command.equals("DUMMY")) {
            System.out.println(dialogue.get(command) + " (Position: Am Schluss)");
        }
        if (command.equals("EXIT") || command.equals("FORMAT_RAW")) {
            System.out.println(dialogue.get(command));
        }
    }

    /**
     * Prints the whole text.
     *
     * @param text The output in the already applied format.
     */
    public void printText(ArrayList<String> text) {
        for (String s : text) {
            System.out.println(s);
        }
    }

    /**
     * Prints an error, saying the given command was incorrect.
     */
    public void printErrorCommandInvalid() {
        System.err.println("Der Befehl ist nicht korrekt.");
        System.out.println("Hinweis: Die Befehle finden Sie in der Übersicht zuoberst.");
    }

    /**
     * Prints an error, saying there are no words in the document occurring four or more times.
     */
    public void printErrorNoWordsDirectory() {
        System.err.println("Keine Wörter im Verzeichnis vorhanden.");
        System.out.println("Hinweis: Damit ein Wort im Verzeichnis aufgelistet wird, muss es mindestens vier Male vorkommen.");
    }

    /**
     * Prints an error, saying there is no paragraph with this index.
     *
     * @param paragraphNumber Number the user gave in the command.
     */
    public void printErrorIndexInvalid(int paragraphNumber) {
        System.err.println("Der Absatz " + paragraphNumber + " existiert nicht.");
        System.out.println("Hinweis: Der Index beginnt bei 1 und geht bis zur gesamten Anzahl der Absätze.");
    }

    /**
     * Prints the Index-directory.
     *
     * @param indicesMap The key is the word and the value of how many times it appears in the text.
     */
    public void printDirectory(HashMap<String, ArrayList<Integer>> indicesMap) {
        for (String word : indicesMap.keySet()) {
            ArrayList<Integer> indices = indicesMap.get(word);
            String result = word + " " + indices.get(0);
            for (int i = 1; i < indices.size(); i++) {
                result += "," + indices.get(i);
            }
            System.out.println(result);
        }
    }

    /**
     * This method prints an error, saying the document is empty.
     */
    public void printErrorNoDocument() {
        System.err.println("Das Dokument ist leer!");
    }
}