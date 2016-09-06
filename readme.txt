Environment
-----------

Eclipse Neon 4.6.0
m2e - Maven Integration for Eclipse 1.7.0.20160603-1933
JRE 1.8.0
JUnit 4.12
Java 8 mandatory

Supporting Material
--------------------

StockMarketClassDiagram.JPG - UML class diagram

Assumptions
-----------
All prices are integer values in pence.
All trade time stamps are UTC
Trades in future time are illegal


Design Considerations
---------------------

I've chosen to do the simplest thing that could possibly work with any eye on how it might be scaled to a 
client/server application by using a repository to abstract persistence of domain objects.
The class diagram in StockMarketClassDiagram.JPG shows the main application structure.
Where appropriate I'm programming to interface and not implementation.
I've chosen to have the stock classes implement the required algorithms. If the object model had been more 
complex and/or there were more algorithms, I might have chosen to implement the Visitor Design Pattern instead.

Interfaces
----------

The Stock interface declares the methods that are exposed by all stocks. 
The StockRepository interface declares the methods required to access stock and trade domain objects.
The StockMarket interface declares the methods required to meet the assignment requirements.

Classes
-------

The AbstractStock class is the abstract base class containing common behaviour for all stock types and
encapsulates the trade records associate with the stock.
CommonStock is the concrete class implementing the common stock specific dividend yield calculation.
PreferredStock is the concrete class implementing the preferred stock specific dividend yield calculation.
The Trade class defines value class for holding immutable trade information.
InMemoryStockRepository is an in memory repository, initialised with the sample data from the assignment. 
that persists the stock and trade records. Stock and trade data is persisted in a hash map, keyed by stock symbol.
The SuperSimpleStockMarket class implements the methods which meet the requirements.
The Application class contains a main entry point to demonstrate the functionality.

Testing
-------

JUnit tests are available for key classes and end-to-end. I have tested all happy path scenarios. 
I could do some further testing on exception conditions.

