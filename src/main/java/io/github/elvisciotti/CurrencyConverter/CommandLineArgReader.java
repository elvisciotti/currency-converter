package io.github.elvisciotti.CurrencyConverter;

import org.apache.commons.cli.*;

public class CommandLineArgReader {
    private Float currencyValue;
    private String currencyFrom;
    private String currencyTo;

    private CommandLineArgReader(Float value, String from, String to) {
        this.currencyValue = value;
        this.currencyFrom = from;
        this.currencyTo = to;
    }

    public static Options createOptions() {
        Options options = new Options();
        options
                .addOption("v", "value", true, "Value e.g. 1.5")
                .addOption("f", "from", true, "Input currency e.g. EUR, GBP, USD")
                .addOption("t", "to", true, "Output currency e.g. EUR, GBP, USD");

        return options;
    }

    public static CommandLineArgReader createFromCmdArgs(Options options, String[] commandLineArgs) throws ParseException {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, commandLineArgs);
        if (!cmd.hasOption('v') || !cmd.hasOption('f') || !cmd.hasOption('t')) {
            throw new MissingOptionException("Missing one of the options");
        }

        return new CommandLineArgReader(
                Float.valueOf(cmd.getOptionValue('v')),
                cmd.getOptionValue('f'),
                cmd.getOptionValue('t')
        );
    }

    public Float getCurrencyValue() {
        return currencyValue;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }
}
