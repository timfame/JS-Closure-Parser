package main;

import main.exceptions.ClosureException;
import main.exceptions.UndefinedException;
import main.source.Source;
import main.types.Function;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private final Source files;

    public Parser(final Source files) {
        this.files = files;
    }

    public String parse() throws IOException, UndefinedException, ClosureException {
        ArgumentsParser.getInstance().init(files);
        ExpressionParser.getInstance().init(files);
        TextParser textParser = new TextParser(files);
        Function closure = textParser.getTokens();
        ClosureParser closureParser = new ClosureParser(closure);
        ArrayList<Function> functions = closureParser.parse();
        StringBuilder res = new StringBuilder();
        for (Function function : functions) {
            res.append(function.toString());
        }
        return res.toString();
    }
}
