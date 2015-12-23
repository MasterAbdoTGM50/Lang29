package matgm50.lang29.tst;

import matgm50.lang29.L29Interpreter;

import static java.lang.System.*;

public class L29Test {

    public static void main(String[] args) {

        L29Interpreter interpreter = new L29Interpreter();
        interpreter.openFile("L29_1.txt");
        out.println("Executing script 1");
        interpreter.execute();
        out.println(interpreter.getValue("x"));
        out.println(interpreter.getValue("y"));
        out.println(interpreter.getValue("z"));
        out.println(interpreter.getValue("result_1"));
        out.println(interpreter.getValue("result_2"));
        interpreter.openFile("L29_2.txt");
        out.println("Executing script 2");
        interpreter.execute();
        out.println(interpreter.getValue("x"));
        out.println(interpreter.getValue("y"));
        out.println(interpreter.getValue("z"));
        out.println(interpreter.getValue("result_1"));

    }

}
