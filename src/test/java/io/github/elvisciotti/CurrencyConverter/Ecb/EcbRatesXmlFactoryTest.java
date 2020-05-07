package io.github.elvisciotti.CurrencyConverter.Ecb;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EcbRatesXmlFactoryTest {
    private String xmlContent1;

    {
        try {
            String filePath = getClass()
                    .getClassLoader()
                    .getResource("junit-fixtures/ecb.xml")
                    .getFile();

            xmlContent1 = Files
                    .newBufferedReader(Paths.get(filePath))
                    .lines()
                    .collect(Collectors.joining());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void factory() throws Exception {
        EcbRates sut = EcbRatesXmlFactory.factory(xmlContent1);
        assertEquals(true, sut.has("USD"));
        assertEquals(true, sut.has("GBP"));
        assertEquals(1.0807, sut.get("USD"), 0.0001);
        System.out.println(xmlContent1);
    }
}