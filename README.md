# location2csv

This is an implementation to the coding challenge proposed on https://github.com/goeuro/dev-test. As per the challenge description, this 
project should make a HTTP call to GoEuro API and output results to a CSV file.

## How do I build this project?

The project uses Gradle as build tool, backed by `shadow` plugin for the creation of a "fat jar" containing all dependencies. 
To build the JAR file as requested by the challenge's description, use `./gradlew clean shadowJar`

## How do I run the solution?

Once the project has been build, a "fat jar" named `GoEuroTest.jar` will be created under `/build/libs`. To run the solution, 
use `java -jar build/libs/GoEuroTest.jar "<city name here>"`. For instance, `java -jar build/libs/GoEuroTest.jar "Berlin"`.

## Approach to the solution

I've tried to establish a clear separation between the contexts of GoEuro API and the solution. The approach for such a separation was
based on the concepts discussed by Martin Fowler on http://martinfowler.com/articles/refactoring-external-service.html

I've also tried to maintain a clear separation of concerns and dependencies, having based the architecture of the solution on the "Clean Archictecture", 
described by Robert C. Martin on https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html, and on the discussion
presented on http://blog.cleancoder.com/uncle-bob/2016/01/04/ALittleArchitecture.html, by the same author.

I've worked on the code to the best of my TDD knowledge. There certainly are failures in my produced code, but I've tried to stick to 
small incremental steps for every method in every class. Please notice there are two special packages under `src/test/java`:

* `contract`: this package contains an integration contract test, which performs an actual HTTP call to GoEuro API. The definition of 
such test type are much better described by Martin Fowler at http://martinfowler.com/bliki/IntegrationContractTest.html .
* `acceptance`: this package contains an acceptance test, in other words, a test that will execute the entire solution and make sure
it complies with the requirements. It will simulate user input and go through all layers and classes, make an HTTP call to GoEuro API
and output results to a CSV file. My initial intention was to use (Cucumber)[https://cucumber.io/] for such a purpose, but after struggling 
with is setup for Java 8 for a couple of hours, I decided to move on and stick to good old JUnit.

Hope I've been able to convey basic information about my solution well enough, and hope you like it! :)