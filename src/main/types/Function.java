package main.types;

import java.util.ArrayList;

public class Function {
    private String name;
    private ArrayList<String> arguments;
    private ArrayList<Variable> bodyVariables = new ArrayList<>();
    private ArrayList<Function> bodyFunctions = new ArrayList<>();
    private Expression returnValue;

    public Function(String name, ArrayList<String> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public void addVariable(Variable add) {
        bodyVariables.add(add);
    }

    public void addFunction(Function add) { bodyFunctions.add(add); }

    public ArrayList<Variable> getBodyVariables() {
        return bodyVariables;
    }

    public ArrayList<Function> getBodyFunctions() {
        return bodyFunctions;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<String> arguments) { this.arguments = arguments; }

    public Expression getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Expression returnValue) {
        this.returnValue = returnValue;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("function ").append(name).append("(");
        String separator = "";
        for (String arg : arguments) {
            res.append(separator).append(arg);
            separator = ", ";
        }
        res.append(") {\n");
        for (Variable var : bodyVariables) {
            res.append(var.toString());
        }
        res.append("    return ").append(returnValue.getExpression()).append(";\n};\n");
        return res.toString();
    }
}
