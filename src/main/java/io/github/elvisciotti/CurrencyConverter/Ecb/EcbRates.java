package io.github.elvisciotti.CurrencyConverter.Ecb;

import io.github.elvisciotti.CurrencyConverter.Ecb.Exception.MissingCurrencyException;

import java.util.HashMap;
import java.util.Map;

public class EcbRates {

    private Map<String, Float> euroToCurrencyRate = new HashMap<>();

    void add(String currencyCode, float value) {
        euroToCurrencyRate.put(currencyCode, value);
    }

    public boolean has(String currencyCode) {
        return euroToCurrencyRate.containsKey(currencyCode);
    }

    public float get(String currencyCode) throws MissingCurrencyException {
        if (currencyCode.equals("EUR")) {
            return 1f;
        }
        if (!euroToCurrencyRate.containsKey(currencyCode)) {
            throw new MissingCurrencyException(currencyCode + " currency rate not recognised");
        }

        return euroToCurrencyRate.get(currencyCode);
    }
}
