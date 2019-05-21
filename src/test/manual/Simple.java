package test.manual;

import main.Parser;
import main.TextParser;
import main.exceptions.ClosureException;
import main.exceptions.UndefinedException;
import main.source.FileSource;
import test.auto.Checker;

import javax.script.ScriptException;
import java.io.IOException;

public class Simple {

    public static void main(String[] args) throws IOException, ClosureException, UndefinedException, ScriptException {
      //  try (FileSource files = new FileSource(args[0], args[1])) {
             FileSource files = new FileSource("src/test/manual/input.txt",
                     "src/test/manual/output.txt");
             Parser parser = new Parser(files);
            System.out.println(parser.parse());
        //    System.out.println(files.nextSymbol() + "" + news.get() + "" + files.nextSymbol() + "" + news.get());

      //  } catch (Exception e) {
      //      e.printStackTrace();
      //  }
    }
}
