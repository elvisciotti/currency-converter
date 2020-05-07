package io.github.elvisciotti.CurrencyConverter.Ecb;

import io.github.elvisciotti.Cache.CacheInterface;
import io.github.elvisciotti.Cache.DiskCache;
import io.github.elvisciotti.CurrencyConverter.Ecb.Exception.WrongOnlineEcbXmlFormat;
import io.github.elvisciotti.Downloader.DownloaderCached;
import io.github.elvisciotti.Downloader.DownloaderInterface;
import io.github.elvisciotti.Downloader.DownloaderTryAgain;
import io.github.elvisciotti.Downloader.NativeDownloader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathException;
import java.io.IOException;
import java.net.URL;

public class EcbConverterFactory {
    final private static String ratesUrl = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    final private static String filesPrefix = "io.github.elvisciotti.cacheconverter";
    final private static String cacheRootDir = System.getProperty("java.io.tmpdir");
    final private static int expireSeconds = 3600;

    public static EcbConverter factory() throws IOException, XPathException, ParserConfigurationException, SAXException, WrongOnlineEcbXmlFormat {
        CacheInterface cacheFile = new DiskCache(cacheRootDir, filesPrefix, expireSeconds);

        DownloaderInterface downloader = new NativeDownloader();
        downloader = new DownloaderTryAgain(downloader);
        downloader = new DownloaderCached(downloader, cacheFile);

        String xmlContent = downloader.getContent(new URL(ratesUrl));
        EcbRates rates = EcbRatesXmlFactory.factory(xmlContent);

        return new EcbConverter(rates);
    }
}
