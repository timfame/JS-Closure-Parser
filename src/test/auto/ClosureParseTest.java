package test.auto;

import test.Test;
import test.manual.ManualTests;

import javax.script.ScriptException;
import java.util.ArrayList;

public class ClosureParseTest {

    public static void main(String[] args) throws ScriptException {
        boolean show = false;
        if (args.length > 0) {
            if (args[0].equals("show")) {
                show = true;
            }
        }
        Checker checker = new Checker(show);
        ManualTests manualTests = new ManualTests();
        ArrayList<Test> tests = manualTests.getTests();
        int index = 0;
        for (Test test : tests) {
            index++;
            checker.check(test.getInput(), test.getCountArgs(), index);
        }
    }
}
