package matgm50.lang29;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A class holding many utilities used in the interpretation of Lang29 scripts.
 *
 * @author MasterAbdoTGM50
 */
public class L29Util {

    /** The titles of every L29Keyword*/
    public static final String SET_TEXT = "set";
    public static final String JOIN_TEXT = "join";
    public static final String INCREASE_TEXT = "increase";
    public static final String DECREASE_TEXT = "decrease";
    public static final String IF_TEXT = "if";
    public static final String ELSE_TEXT = "else";
    public static final String END_TEXT = "end";
    public static final String AND_TEXT = "and";
    public static final String OR_TEXT = "or";

    /** The instances of every L29Keyword*/
    private static final L29Keyword SET = new L29Keyword.SET();
    private static final L29Keyword JOIN = new L29Keyword.JOIN();
    private static final L29Keyword INCREASE = new L29Keyword.INCREASE();
    private static final L29Keyword DECREASE = new L29Keyword.DECREASE();
    private static final L29Keyword IF = new L29Keyword.IF();

    /** The map holding all L29Keywords and the static initializer*/
    private static final Map<String, L29Keyword> keywords = new HashMap<String, L29Keyword>();
    static {

        keywords.put(SET.title, SET);
        keywords.put(JOIN.title, JOIN);
        keywords.put(INCREASE.title, INCREASE);
        keywords.put(DECREASE.title, DECREASE);
        keywords.put(IF.title, IF);

    }

    /**
     * Checks whether a word is an L29Keyword.
     *
     * @param arg the word to be checked
     *
     * @return if this word is a keyword or not
     */
    public static boolean isKeyword(String arg) { return keywords.containsKey(arg); }

    /**
     * Gets the L29Keyword object with the associated title.
     *
     * @param arg the title/text to find the L29Keyword associated with
     *
     * @return the L29Keyword object that was found
     */
    public static L29Keyword getKeyword(String arg) { return keywords.get(arg); }

    /**
     * Checks whether the argument is a variable or not.
     *
     * @param interpreter the parent L29Interpreter object
     * @param arg the argument to be tested
     *
     * @return if the word is a registered variable or not
     */
    public static boolean isVariable(L29Interpreter interpreter, String arg) { return interpreter.hasValue(arg); }


    /**
     * Gets the value of the argument whether it's the argument itself or a variable stored with it's name
     *
     * @param interpreter the parent L29Interpreter object
     * @param arg the argument to get the value of
     * @return the value of the argument
     */
    public static String valueOfArg(L29Interpreter interpreter, String arg) {

        return isVariable(interpreter, arg) ? interpreter.getValue(arg) : arg;

    }

    /**
     * Executes the lines of code until a certain keyword
     *
     * @param interpreter the parent L29Interpreter object
     * @param execTill the keyword to stop at
     */
    public static void executeTill(L29Interpreter interpreter, String execTill) {

        String args[];

        try {

            while ((interpreter.currentLine = interpreter.getReader().readLine()) != null) {

                if (interpreter.currentLine.equals(execTill)) { break; }
                if (atEnd(interpreter)) { break; }

                args = interpreter.currentLine.split(" ", 4);
                if(L29Util.isKeyword(args[0])) {

                    L29Util.getKeyword(args[0]).execute(interpreter, Arrays.copyOfRange(args, 1, args.length));

                }

            }

        } catch (IOException e) { e.printStackTrace(); }

    }

    /**
     * Skips the lines of code until a certain keyword
     *
     * @param interpreter the parent L29Interpreter object
     * @param skipTill the keyword to stop at
     */
    public static void skipTill(L29Interpreter interpreter, String skipTill) {

        String args[];

        try {

            while ((interpreter.currentLine = interpreter.getReader().readLine()) != null) {

                if (interpreter.currentLine.equals(skipTill)) { break; }
                if (atEnd(interpreter)) { break; }

            }

        } catch (IOException e) { e.printStackTrace(); }

    }

    /**
     * Checks whether the interpreter is at an END argument
     *
     * @param interpreter the parent L29Interpreter object
     * @return whether the current line is an END line or not
     */
    public static boolean atEnd(L29Interpreter interpreter) { return interpreter.currentLine.equals(L29Util.END_TEXT); }

}
