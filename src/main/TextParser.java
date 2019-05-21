package main;

import main.exceptions.ClosureException;
import main.exceptions.UndefinedException;
import main.source.Source;
import main.types.Expression;
import main.types.Function;
import main.types.Variable;

import java.io.IOException;
import java.util.ArrayList;

public class TextParser {
    private final Source files;

    TextParser(final Source files) {
        this.files = files;
    }

    Function getTokens() throws IOException, UndefinedException, ClosureException {
        if (!files.nextWord().equals("function")) {
            throw new UndefinedException(files.getWord(), files.getPos(files.getWord().length()));
        }
        return getFunction();
    }

    private Function getFunction() throws IOException, ClosureException, UndefinedException {
        String name = files.nextWord();
        if (name.equals("")) {
            throwNothingException("some name of function");
        }
        if (files.nextSymbol() != '(') {
            throwException('(', files.getSymbol());
        }
        ArrayList<String> arguments = new ArrayList<>(ArgumentsParser.getInstance().parse());
        if (files.nextSymbol() != '{') {
            throwException('{', files.getSymbol());
        }
        Function currentFunction = new Function(name, arguments);
        boolean wasReturn = false;
        while (true) {
            String word = files.nextWord();
            if (word.equals("var") && !wasReturn) {
                currentFunction.addVariable(getVariable());
                continue;
            }
            if (word.equals("function")) {
                currentFunction.addFunction(getFunction());
                continue;
            }
            if (word.equals("return")) {
                currentFunction.setReturnValue(getReturn());
                wasReturn = true;
                continue;
            }
            if (word.equals("")) {
                if (files.nextSymbol() != '}') {
                    throwException('}', files.getSymbol());
                } else {
                    break;
                }
            }
            throw new UndefinedException(word, files.getPos(word.length()));
        }
        return currentFunction;
    }

    private Variable getVariable() throws IOException, ClosureException {
        String name = files.nextWord();
        if (files.nextSymbol() != '=') {
            throwException('=', files.getSymbol());
        }
        return new Variable(name, ExpressionParser.getInstance().parse());
    }

    private Expression getReturn() throws IOException, ClosureException {
        return ExpressionParser.getInstance().parse();
    }

    private void throwException(String e, String f) throws ClosureException {
        throw new ClosureException(e, f, files.getPos(f.length()));
    }

    private void throwException(char e, char f) throws ClosureException {
        throw new ClosureException(e, f, files.getPos(1));
    }

    private  void throwNothingException(String e) throws ClosureException {
        throw new ClosureException(e, "nothing", files.getPos(0));
    }
}
