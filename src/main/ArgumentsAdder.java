package main;

import main.exceptions.ClosureException;
import main.source.StringSource;
import main.types.Expression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

class ArgumentsAdder {
    private static ArgumentsAdder instance;

    private ArgumentsAdder() {}

    static ArgumentsAdder getInstance() {
        if (instance == null) {
            instance = new ArgumentsAdder();
        }
        return instance;
    }

    StringBuilder addArguments(Expression oldExpression, HashSet<String> outerFunctions, ArrayList<String> nextArgs) throws IOException, ClosureException {
        ArrayList<String> tokens = oldExpression.getTokens();
        StringBuilder newReturn = new StringBuilder();
        String whitespace = "";
        for (String token : tokens) {
            StringBuilder newToken = new StringBuilder(token);
            if (token.indexOf('(') != -1) {
                StringSource stringSource = new StringSource(token);
                String name = stringSource.nextWord();
                if (outerFunctions.contains(name)) {
                    newReturn.append(whitespace).append(newToken);
                    whitespace = " ";
                    continue;
                }
                stringSource.nextSymbol();
                ArgumentsParser.getInstance().init(stringSource);
                ArrayList<String> args = ArgumentsParser.getInstance().parse();
                args.addAll(nextArgs);
                String separator = "";
                newToken = new StringBuilder(name);
                newToken.append('(');
                for (String arg : args) {
                    newToken.append(separator).append(arg);
                    separator = ", ";
                }
                newToken.append(')');
            }
            newReturn.append(whitespace).append(newToken);
            whitespace = " ";
        }
        return newReturn;
    }
}
