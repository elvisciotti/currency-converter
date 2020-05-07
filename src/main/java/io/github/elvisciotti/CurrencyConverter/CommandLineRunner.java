package io.github.elvisciotti.CurrencyConverter;

import io.github.elvisciotti.CurrencyConverter.Ecb.EcbConverterFactory;

public class CommandLineRunner {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage java -jar file.jar <VALUE> <FROM> <TO>");
            System.out.println(" e.g. 100.5 EUR GBP ");
            System.exit(1);
        }

        try {
            float convert = EcbConverterFactory.factory()
                    .convert(Float.valueOf(args[0]), args[1], args[2]);
            System.out.println(Math.round(convert * 100f) / 100f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
