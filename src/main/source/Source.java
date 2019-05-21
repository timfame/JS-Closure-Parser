package main.source;

import java.io.IOException;

public abstract class Source {
    static final char END = '\0';

    private int pos = 0, line = 0;
    protected int generalPos = 0;
    private char c, s;
    private String word;
    private boolean lastSeparator = false, newLine = false;

    protected abstract char readChar() throws IOException;

    public String getPos(int length) {
        return "line " + (line + 1) + ", position " + (pos - length + 1);
    }

    private char getChar() {
        return c;
    }

    private void nextChar() throws IOException {
        if (newLine) {
            line++;
            pos = -1;
            newLine = false;
        }
        c = readChar();
        if (c == '\r')
            c = readChar();
        if (c == '\n') {
            newLine = true;
        }
        pos++;
        generalPos++;
    }

    public char getSymbol() {
        return s;
    }

    public char nextSymbol() throws IOException {
        if (!lastSeparator) {
            skipWhiteSpaces();
        }
        lastSeparator = false;
        return s = getChar();
    }

    public String getWord() {
        return word;
    }

    public String nextWord() throws IOException {
        StringBuilder res = new StringBuilder();
        if (!lastSeparator) {
            skipWhiteSpaces();
        }
        while (getChar() != END && !isSeparator(getChar())) {
            res.append(getChar());
            nextChar();
        }
        lastSeparator = isSeparator(getChar()) && !Character.isWhitespace(getChar());
        return word = res.toString();
    }

    private void skipWhiteSpaces() throws IOException {
        do {
            nextChar();
        } while (Character.isWhitespace(getChar()));
    }

    private boolean isSeparator(char c) {
        return Character.isWhitespace(c) ||
                c == '=' || c == '+' ||
                c == '{' || c == '}' ||
                c == '(' || c == ')' ||
                c == ';' || c == ',';
    }
}
