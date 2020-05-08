package io.github.elvisciotti.CurrencyConverter;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommandLineRunnerTest {
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void noArgsShowsGuide() {
        new CommandLineRunner().main(new String[0]);
        assertTrue(outContent.toString().startsWith("usage:"));
    }

    @Test
    void missingArgsShowsGuide() {
        new CommandLineRunner().main(new String[]{"--value=1.0", "--from=GBP"});
        assertTrue(outContent.toString().startsWith("usage:"));
    }

    @Test
    void mainLongOptions() throws ParseException {
        new CommandLineRunner().main(new String[]{"--value=1.0", "--from=GBP", "--to=EUR"});
        assertEquals("1.14\n", outContent.toString());
    }

    @Test
    void mainShortOptions() throws ParseException {
        new CommandLineRunner().main(new String[]{"-v", "1.0", "-f", "GBP", "-t", "EUR"});
        assertEquals("1.14\n", outContent.toString());
    }


    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}