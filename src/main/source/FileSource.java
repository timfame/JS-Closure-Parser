package main.source;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileSource extends Source implements AutoCloseable{
    private final Reader reader;
    private final Writer writer;

    public FileSource(final String input, final String output) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
    }

    @Override
    protected char readChar() throws IOException {
        final int c = reader.read();
        return (c == -1 ? END : (char)c);
    }

    public void write(final String out) throws IOException {
        writer.write(out);
    }

    @Override
    public void close() {
        close(reader, writer);
    }

    private void close(AutoCloseable... args) {
        for (AutoCloseable arg : args)
            if (arg != null) {
                try {
                    arg.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
    }
}
