Spring boot - cucumber - demo
-------
This demo is about putting in place a cucumber senario in a Spring boot environement with differents technologies.


Objective
--
Create a reference implementation which provides the best practices of running integration & component tests in a SpringBoot application.

Technologies
--
Spring Boot Test - Provides the spring boot ecosystem required for the tests.<br>
Cucumber 6.8.0 <br>
TestNg 7.1.0<br>
Java 1.8 <br>
Maven 4.0

Paths
--
**addresse.feature** : ``src/test/resources/features/adresse.feature`` <br>
**java class representing the models** : ``src/test/java/com/example/demo/model`` <br>
**java step definitions** : ``src/test/java/com/example/demo/stepDefinition`` <br>
**cucumber runner** : ``src/test/``

Running tests
---
1. Open terminal in the project repo, then execute ``mvn clean install``
2. After running the application, a result are generated in : ``target/site/serenity/index.html``

Struggles
---
1. as far as the researches took me, Serenity is no compatible with testNg.<br>to activate Serenity report for the test we shall use<br>
the annotation ``@RunWith(SerenityRunner.class)``, meanwhile this annotation work only with JUnit.<br>
To check the test report please go into ``target/cucumber/report-html``

Wire mock implementation
---
wire mock server implementation is being in comment.
check stepDefinition.java file and CucumberRunner.java