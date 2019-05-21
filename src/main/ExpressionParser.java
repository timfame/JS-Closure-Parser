package main;

import main.exceptions.ClosureException;
import main.source.Source;
import main.types.Expression;

import java.io.IOException;
import java.util.ArrayList;

class ExpressionParser {
    private Source files;
    private static ExpressionParser instance;

    private ExpressionParser() {}

    void init(Source files) {
        this.files = files;
    }

    static ExpressionParser getInstance() {
        if (instance == null) {
            instance = new ExpressionParser();
        }
        return instance;
    }

    Expression parse() throws IOException, ClosureException {
        StringBuilder res = new StringBuilder();
        Expression expression = new Expression();
        while (true) {
            String word = files.nextWord();
            char next = files.nextSymbol();
            if (word.equals("")) {
                if (next == ';') {
                    break;
                }
                if (next != '+') {
                    throw new ClosureException("operand", "'" + next + "'", files.getPos(1));
                }
            }
            if (next == '(') {
                StringBuilder func = new StringBuilder();
                StringBuilder call = new StringBuilder(callingFunction());
                func.append(word).append(next).append(call).append(')');
                expression.addToken(func.toString());
                res.append(func);
            } else if (next == ';') {
                expression.addToken(word);
                res.append(word);
                break;
            } else {
                expression.addToken(word);
                expression.addToken("" + next);
                res.append(word).append(' ').append(next).append(' ');
            }
        }
        expression.setExpression(res.toString());
        return expression;
    }

    private StringBuilder callingFunction() throws IOException, ClosureException {
        ArrayList<String> arguments = new ArrayList<>(ArgumentsParser.getInstance().parse());
        StringBuilder res = new StringBuilder();
        String separator = "";
        for (String arg : arguments) {
            res.append(separator).append(arg);
            separator = ", ";
        }
        return res;
    }
}
