package main.types;

import java.util.ArrayList;

public class Expression {
    private String expression;
    private ArrayList<String> tokens = new ArrayList<>();

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void addToken(String add) {
        if (!add.equals("")) {
            tokens.add(add);
        }
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }
}
