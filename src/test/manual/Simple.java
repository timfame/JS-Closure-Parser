package test.manual;

import main.Parser;
import main.TextParser;
import main.exceptions.ClosureException;
import main.exceptions.UndefinedException;
import main.source.FileSource;

import javax.script.ScriptException;
import java.io.IOException;

public class Simple {

    public static void main(String[] args) throws IOException, ClosureException, UndefinedException, ScriptException {
        FileSource files = new FileSource("src/test/manual/input.txt",
                "src/test/manual/output.txt");
        Parser parser = new Parser(files);
        System.out.println(parser.parse());
    }
}
