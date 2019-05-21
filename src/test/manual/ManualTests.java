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
        tests.add(new Test("function main() {\n" +
                "    var b = 2;\n" +
                "    function foo(a1) {\n" +
                "        return a1 + a;\n" +
                "    }\n" +
                "    function bar(b1) {\n" +
                "        return b1 + b;\n" +
                "    }\n" +
                "    var a = 1;\n" +
                "    return foo(a) + bar(b);\n" +
                "};", 0));
        tests.add(new Test("function main() {\n" +
                "    function foo() {\n" +
                "        var a = 1;\n" +
                "        var b = 2;\n" +
                "        function bar() {\n" +
                "            return a + b;\n" +
                "        }\n" +
                "        return bar();\n" +
                "    }\n" +
                "    function baz() {\n" +
                "        var x = 1;\n" +
                "        var y = 2;\n" +
                "        function baq() {\n" +
                "            return x + y;\n" +
                "        }\n" +
                "        return baq();\n" +
                "    }\n" +
                "    return foo() + baz();\n" +
                "};", 1));
        tests.add(new Test("function main() {\n" +
                "    return bar();\n" +
                "};", 0));
        tests.add(new Test("functionmain(a) {\n" +
                "    var b = bar(2);\n" +
                "    function bar(x) {\n" +
                "        return 1 + x + 1;\n" +
                "    }\n" +
                "    return bar(b) + a;\n" +
                "};", 1));
        tests.add(new Test("functon main(a) {\n" +
                "  var b = 42;\n" +
                "  function bar(c) {\n" +
                "    return a + b + c;\n" +
                "  }\n" +
                "  return bar(24);\n" +
                "};", 1));
        tests.add(new Test("function    main( a   )   {\n" +
                "     var b      = 42;\n\n\n\n\n\n" +
                "  function    bar   (c   ) {\n" +
                "       return a + b +    c;\n" +
                "  }\n" +
                "  return    bar(24);\n" +
                "};", 1));
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
