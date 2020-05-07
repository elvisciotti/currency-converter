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
        String fixturePath = getClass().getClassLoader().getResource("resources/ecb.xml").getFile();
        try {
            xmlContent1 = Files
                    .newBufferedReader(Paths.get(fixturePath))
                    .lines()
                    .collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void factory() throws Exception {
        EcbRates sut = EcbRatesXmlFactory.factory(xmlContent1);
        assertEquals(true, sut.has("USD"));
        assertEquals(1.0807, sut.get("USD"), 0.0001);
        assertEquals(20.0603, sut.get("ZAR"), 0.0001);
        System.out.println(xmlContent1);
    }
}