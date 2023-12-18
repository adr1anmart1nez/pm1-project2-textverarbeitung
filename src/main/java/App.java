/**
 * Class App which starts a new textEditor.
 *
 * @author Team03 - Stackoverflow
 * @version 1.0
 */
public class App {

    /**
     * Creates a new editor and runs it.
     *
     * @param args an array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        textEditor.run();
    }
}