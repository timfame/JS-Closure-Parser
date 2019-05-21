package test.manual;

import test.Test;

import java.util.ArrayList;

public class ManualTests {
    private ArrayList<Test> tests = new ArrayList<>();

    public ManualTests() {
        generateTests();
    }

    private void generateTests() {
        tests.add(new Test("function main(a) {\n" +
                "  var b = 42;\n" +
                "  function bar(c) {\n" +
                "    return a + b + c;\n" +
                "  }\n" +
                "  return bar(24);\n" +
                "};", 1));
        tests.add(new Test("function main() {\n" +
                "var a = 2;\n" +
                "function foo(b) {\n" +
                "return a + 3 + b;\n" +
                "}\n" +
                "return foo(a);\n" +
                "};", 0));
        tests.add(new Test("function main(a, b) {\n" +
                "    var c = 2;\n" +
                "    function foo(x, y, z) {\n" +
                "        return x + y + c + z;\n" +
                "    }\n" +
                "    return foo(a, b, c);\n" +
                "};",2));
        tests.add(new Test("function main(a, b) {\n" +
                "    var c = 2;\n" +
                "    function foo(x, y, z) {\n" +
                "        return c + z;\n" +
                "    }\n" +
                "    return foo(a, b, c);\n" +
                "};", 2));
        tests.add(new Test("function main(a) {\n" +
                "    var b = 2;\n" +
                "    function foo(x) {\n" +
                "        function bar(u, v) {\n" +
                "            return x + u + v;\n" +
                "        }\n" +
                "        return bar(x, b);\n" +
                "    }\n" +
                "    return foo(a);\n" +
                "};", 1));
        tests.add(new Test("function main(a) {\n" +
                "    var b = bar(2);\n" +
                "    function bar(x) {\n" +
                "        return b + x + 1;\n" +
                "    }\n" +
                "    return bar(b) + 2;\n" +
                "};", 1));
        tests.add(new Test("function main(a) {\n" +
                "    var b = bar(2);\n" +
                "    function bar(x) {\n" +
                "        return 1 + x + 1;\n" +
                "    }\n" +
                "    return bar(b) + a;\n" +
                "};", 1));
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
