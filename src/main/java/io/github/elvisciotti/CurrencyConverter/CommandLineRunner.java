package io.github.elvisciotti.CurrencyConverter;

import io.github.elvisciotti.CurrencyConverter.Ecb.EcbConverterFactory;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineRunner {

    public static void main(String[] args) {
        Options options = CommandLineArgReader.createOptions();

        try {
            CommandLineArgReader cmdArgsReader = CommandLineArgReader.createFromCmdArgs(options, args);
            float convert = EcbConverterFactory.factory().convert(
                    cmdArgsReader.getCurrencyValue(),
                    cmdArgsReader.getCurrencyFrom(),
                    cmdArgsReader.getCurrencyTo()
            );
            System.out.println(Math.round(convert * 100f) / 100f);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Currency Converter", options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
