package main.types;

public class Variable {
    private String name;
    private Expression expression;

    public Variable(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "    var " + name + " = " + expression.getExpression() + ";\n";
    }
}
