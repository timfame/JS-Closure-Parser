package main.exceptions;

public class ClosureException extends Exception {

    public ClosureException(String expected, String found, String pos) {
        super("expected: " + expected + ", but found: " + found + " on " + pos);
    }

    public ClosureException(char expected, char found, String pos) {
        super("expected: '" + expected + "', but found: '" + found + "' on " + pos);
    }
}
