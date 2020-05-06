# Currency converter

Library + command line to convert currencies using online XML (downloaded and locally cached bypassing network failures).

Built to show an example usage of Java 8 core, OOP design (SOLID principles), JUnit 5 and Mockito, Maven.

Work in progress (missing args parsing, XML, converting logic, few junit cases)

## Build

    mvn package
    java -jar target/<file>.jar 100 --from=EUR --to=GBP
    
## Test only
    mvn test
