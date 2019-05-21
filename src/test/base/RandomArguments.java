package test.base;

import java.util.Random;

public class RandomArguments {
    private static RandomArguments instance;
    private final Random random = new Random();

    private RandomArguments() {}

    public static RandomArguments getInstance() {
        if (instance == null) {
            instance = new RandomArguments();
        }
        return instance;
    }

    public String getArgs(int n) {
        StringBuilder res = new StringBuilder();
        String separator = "";
        for (int i = 0; i < n; i++) {
            res.append(separator).append(random.nextInt(1000));
            separator = ", ";
        }
        return res.toString();
    }
}
