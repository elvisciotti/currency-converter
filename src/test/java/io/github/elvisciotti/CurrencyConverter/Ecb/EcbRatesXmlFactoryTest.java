package io.github.elvisciotti.CurrencyConverter.Ecb;

import io.github.elvisciotti.ResourceReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EcbRatesXmlFactoryTest {
    ResourceReader resourceReader = new ResourceReader();

    @Test
    void factory() throws Exception {
        EcbRates sut = EcbRatesXmlFactory.factory(resourceReader.getXmlContent());
        assertEquals(true, sut.has("USD"));
        assertEquals(true, sut.has("GBP"));
        assertEquals(1.0807, sut.get("USD"), 0.0001);
    }
}