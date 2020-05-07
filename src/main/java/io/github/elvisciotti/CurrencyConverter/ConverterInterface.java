package io.github.elvisciotti.CurrencyConverter;

import io.github.elvisciotti.CurrencyConverter.Ecb.Exception.MissingCurrencyException;

public interface ConverterInterface {
    float convert(float value, String from, String to) throws MissingCurrencyException;
}
