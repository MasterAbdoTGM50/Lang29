package matgm50.lang29;

import java.util.Arrays;

import static matgm50.lang29.L29Util.*;

/**
 * The class that holds data essential to the execution of keywords in the Lang29 scripts
 *
 * @author MasterAbdoTGM50
 */
public abstract class L29Keyword {

    /** The title of the keyword AKA it's text*/
    public String title;

    /**
     * Constructs an L29Keyword with the specified title
     *
     * @param title the title of the L29Keyword
     */
    public L29Keyword(String title) {

        this.title = title;

    }

    /**
     * Executes the L29Keyword with the following params
     *
     * @param interpreter the parent L29Interpreter object
     * @param args the arguments that were read by the interpreter
     */
    public abstract void execute(L29Interpreter interpreter, String[] args);

    //<editor-fold desc="Keywords">

    public static class SET extends L29Keyword {

        public SET() { super(SET_TEXT); }

        @Override
        public void execute(L29Interpreter interpreter, String[] args) {

            interpreter.setValue(args[0], valueOfArg(interpreter, args[2]));

        }

    }

    public static class INCREASE extends L29Keyword {

        public INCREASE() { super(INCREASE_TEXT); }

        @Override
        public void execute(L29Interpreter interpreter, String[] args) {

            int base = Integer.parseInt(interpreter.getValue(args[0]));
            int increase = Integer.parseInt(args[2]);

            interpreter.setValue(args[0], String.valueOf(base + increase));

        }

    }

    public static class DECREASE extends L29Keyword {

        public DECREASE() { super(DECREASE_TEXT); }

        @Override
        public void execute(L29Interpreter interpreter, String[] args) {

            int base = Integer.parseInt(interpreter.getValue(args[0]));
            int decrease = Integer.parseInt(args[2]);

            interpreter.setValue(args[0], String.valueOf(base - decrease));

        }

    }

    public static class IF extends L29Keyword {

        public IF() { super(IF_TEXT); }

        @Override
        public void execute(L29Interpreter interpreter, String[] args) {

            if (isConditionTrue(interpreter, args)) {

                executeTill(interpreter, ELSE_TEXT);
                if (!atEnd(interpreter)) { skipTill(interpreter, END_TEXT); }

            } else {

                skipTill(interpreter, ELSE_TEXT);
                if (!atEnd(interpreter)) { executeTill(interpreter, END_TEXT); }

            }

        }

        private boolean isConditionTrue(L29Interpreter interpreter, String[] args) {

            String[] tmpArgs = args[2].split(" ", 5);

            if(tmpArgs.length > 1) {

                if(tmpArgs[1].equals(AND_TEXT)) {

                    return isConditionTrue(interpreter, new String[] {args[0], args[1], tmpArgs[0] })
                            && isConditionTrue(interpreter, Arrays.copyOfRange(tmpArgs, 2, tmpArgs.length));

                }

                if(tmpArgs[1].equals(OR_TEXT)) {

                    return isConditionTrue(interpreter, new String[] {args[0], args[1], tmpArgs[0]})
                            || isConditionTrue(interpreter, Arrays.copyOfRange(tmpArgs, 2, tmpArgs.length));

                }

            }

            if (args[1].equals("=")) {

                return valueOfArg(interpreter, args[0]).equals(valueOfArg(interpreter, args[2]));

            } else if (args[1].equals(">")) {

                return (Integer.parseInt(valueOfArg(interpreter, args[0]))
                        > Integer.parseInt(valueOfArg(interpreter, args[2])));

            } else if (args[1].equals(">=") || args[1].equals("=>")) {

                return (Integer.parseInt(valueOfArg(interpreter, args[0]))
                        >= Integer.parseInt(valueOfArg(interpreter, args[2])));

            } else if (args[1].equals("<")) {

                return (Integer.parseInt(valueOfArg(interpreter, args[0]))
                        < Integer.parseInt(valueOfArg(interpreter, args[2])));

            } else if (args[1].equals("<=") || args[1].equals("=<")) {

                return (Integer.parseInt(valueOfArg(interpreter, args[0]))
                        <= Integer.parseInt(valueOfArg(interpreter, args[2])));

            }

            return false;

        }

    }

    //</editor-fold>

}
