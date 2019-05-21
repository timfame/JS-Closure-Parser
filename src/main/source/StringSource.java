package main.source;

public class StringSource extends Source {
    private final String input;

    public StringSource(final String input) {
        this.input = input;
    }

    @Override
    protected char readChar() {
        return (generalPos >= input.length() ? END : input.charAt(generalPos));
    }
}
