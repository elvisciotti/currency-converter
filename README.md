# Currency converter

Java command line library to convert currencies using real time conversion rates from [ECB](https://www.ecb.europa.eu/) central bank

    java -jar target/currency-converter-1.0-SNAPSHOT.jar --value=100 --from=GBP --to=EUR
    >  114.89

(short version)

    java -jar target/currency-converter-1.0-SNAPSHOT.jar -v 100 -f GBP -t EUR
    >  114.89


Built to show an example of usage of 
 * Java 8 core (IO, NIO.2, Streams, lambda, Collections, initialisers, checked exceptions, XML DOM & XPath parsing, Date libs)
 * TDD using JUnit 5 and Mockito
 * Maven
 * OOP design (SOLID principles)
 

## Commands

    mvn test
    mvn package
