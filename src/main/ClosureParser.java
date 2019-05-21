package main;

import main.exceptions.ClosureException;
import main.types.Expression;
import main.types.Function;
import main.types.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

class ClosureParser {
    private final Function function;
    private ArrayList<Function> functions = new ArrayList<>();
    private HashSet<String> outerFunctions = new HashSet<>();

    ClosureParser(Function function) {
        this.function = function;
    }

    ArrayList<Function> parse() throws IOException, ClosureException {
        parseFunction(function, new ArrayList<>());
        return functions;
    }

    private void parseFunction(Function function, ArrayList<String> outerArgs) throws IOException, ClosureException {
        ArrayList<String> arguments = function.getArguments();
        arguments.addAll(outerArgs);
        function.setArguments(arguments);
        ArrayList<String> nextArgs = new ArrayList<>(arguments);
        for (Variable var : function.getBodyVariables()) {
            nextArgs.add(var.getName());
        }
        for (Variable var : function.getBodyVariables()) {
            Expression oldExpression = var.getExpression();
            oldExpression.setExpression(ArgumentsAdder.getInstance().addArguments(oldExpression, outerFunctions, nextArgs).toString());
        }
        outerFunctions.add(function.getName());
        for (Function fun : function.getBodyFunctions()) {
            parseFunction(fun, nextArgs);
        }
        outerFunctions.remove(function.getName());
        Expression oldExpression = function.getReturnValue();
        oldExpression.setExpression(ArgumentsAdder.getInstance().addArguments(oldExpression, outerFunctions, nextArgs).toString());
        function.setReturnValue(oldExpression);
        functions.add(function);
    }
}
