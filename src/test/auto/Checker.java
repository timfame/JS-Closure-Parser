package test.auto;

import main.Parser;
import main.exceptions.ClosureException;
import main.exceptions.UndefinedException;
import main.source.Source;
import main.source.StringSource;
import test.base.RandomArguments;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Checker {
    private final boolean show;

    public Checker(boolean show) { this.show = show; }

    public void check(String input, int cntArgs, int index) throws ScriptException {
        Source source = new StringSource(input);
        Parser parser = new Parser(source);
        String output;
        try {
            output = parser.parse();
        } catch (ClosureException| UndefinedException| IOException e) {
            System.out.println("Invalid input" + e.getMessage() + " on TEST " + index);
            return;
        } catch (Exception e) {
            System.out.println("Program has crashed");
            return;
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String args = RandomArguments.getInstance().getArgs(cntArgs);
        input += "\nmain(" + args + ")";
        output += "\nmain(" + args + ")";
        System.out.println("---- TEST " + index + " ----");
        if (show) {
            System.out.println("Input:\n\n" + input + "\n");
            System.out.println("----------------");
            System.out.println("Output:\n\n" + output + "\n");
            System.out.println("----------------");
        }
        try {
            String ansInput = engine.eval(input).toString();
            String ansOutput = engine.eval(output).toString();
            if (ansInput.equals(ansOutput)) {
                System.out.println("Correct! - " + ansInput + " = " + ansOutput);
            } else {
                System.out.println("Incorrect! - " + ansInput + " = " + ansOutput);
            }
            System.out.println("----------------\n");
        } catch (ScriptException e) {
            System.out.println("Code was incorrect");
        }
    }
}
