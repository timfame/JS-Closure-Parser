package main;

import main.exceptions.ClosureException;
import main.source.Source;

import java.io.IOException;
import java.util.ArrayList;

class ArgumentsParser {
    private Source input;
    private static ArgumentsParser instance;

    private ArgumentsParser() {}

    static ArgumentsParser getInstance() {
        if (instance == null) {
            instance = new ArgumentsParser();
        }
        return instance;
    }

    void init(Source input) {
        this.input = input;
    }

    ArrayList<String> parse() throws IOException, ClosureException {
        ArrayList<String> arguments = new ArrayList<>();
        String first = input.nextWord();
        if (!first.equals("")) {
            arguments.add(first);
            while (input.nextSymbol() != ')') {
                if (input.getSymbol() != ',') {
                    throw new ClosureException("',' or ')'", "'" + input.getSymbol() + "'", input.getPos(1));
                }
                if (input.nextWord().equals("")) {
                    throw new ClosureException("argument", "nothing", input.getPos(0));
                }
                arguments.add(input.getWord());
            }
        } else {
            if (input.nextSymbol() != ')') {
                throw new ClosureException(')', input.getSymbol(), input.getPos(1));
            }
        }
        return arguments;
    }

}
