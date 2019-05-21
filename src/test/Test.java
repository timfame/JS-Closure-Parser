package test;

public class Test {
    private final String input;
    private final int countArgs;

    public Test(final String input, final int countArgs) {
        this.input = input;
        this.countArgs = countArgs;
    }

    public String getInput() {
        return input;
    }

    public int getCountArgs() {
        return countArgs;
    }
}
