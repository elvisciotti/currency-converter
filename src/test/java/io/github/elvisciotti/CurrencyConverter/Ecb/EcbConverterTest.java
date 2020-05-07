package io.github.elvisciotti.CurrencyConverter.Ecb;

import io.github.elvisciotti.CurrencyConverter.Ecb.Exception.MissingCurrencyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EcbConverterTest {

    @Test
    void convert() throws MissingCurrencyException {
        EcbRates rates = new EcbRates();
        rates.add("GBP", 0.87f);
        rates.add("USD", 1.08f);
        EcbConverter ecbConverter = new EcbConverter(rates);

        assertEquals(1f, ecbConverter.convert(1, "EUR", "EUR"));
        assertEquals(1f, ecbConverter.convert(1, "GBP", "GBP"));
        assertEquals(1.15f, ecbConverter.convert(1, "GBP", "EUR"), 0.01);
        assertEquals(1.24f, ecbConverter.convert(1, "GBP", "USD"), 0.01);
        assertEquals(0.8f, ecbConverter.convert(1, "USD", "GBP"), 0.01);
    }
}