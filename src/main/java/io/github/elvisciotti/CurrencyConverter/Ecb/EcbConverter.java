package io.github.elvisciotti.CurrencyConverter.Ecb;

import io.github.elvisciotti.CurrencyConverter.ConverterInterface;
import io.github.elvisciotti.CurrencyConverter.Ecb.Exception.MissingCurrencyException;

public class EcbConverter implements ConverterInterface {
    private EcbRates rates;

    public EcbConverter(EcbRates rates) {
        this.rates = rates;
    }

    @Override
    public float convert(float value, String from, String to) throws MissingCurrencyException {
        if (from.equals(to)) {
            return 1f;
        }

        return value * rates.get(to) / rates.get(from);
    }
}
