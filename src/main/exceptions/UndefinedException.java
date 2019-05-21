package main.exceptions;

public class UndefinedException extends Exception {

    public UndefinedException(String name, String pos) {
        super(name + " on " + pos);
    }
}
