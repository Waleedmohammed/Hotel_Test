# Hotel CRUD Test
A framework designed to test the admin panel of the Hotel booking app

* Needed Setup to run the tests:
    * JAVA 17
    * MAVEN

## Table of Contents

- [Installation](#installation)
- [Features](#Features)
- [Framework Structure](#Framework_Structure)
- [How To Run Tests](#How_To_Run_Tests)

### Installation

1. Download and install Maven
2. Download and install Java JDK 17
3. Clone this Repo
4. Build Project by running command ``` mvn clean install -Dmaven.test.skip=true  ```

## Features

* This Project built with Java Spring boot Framework which support dependency injection
* Used Playwright with Page Object Model Design has been used 
* Visualized Reporting - Using allureReport
* Tests built with the help of Fluent Interface Design Pattern with Method chaining to build tests with Syntactic Sugar Language to achieve Domain Specific Language(DSL)
* Tests can run with maven profiles option
* Support Testing for multi-language test cases . Just by adding new property file under src/main/resources directory and call it with your chosen maven spring active profile
* Smart wait for element with the help of Awaitility Library (giving the chance to implement Fluent wait action) 

## Framework_Structure
1. src/main/java/hotel/qa/test/core/conf
   * here located all Properties classes
  
2. src/main/java/hotel/qa/test/core/factory
   * here located Page wrapper class and browser Factory methods

3. src/main/java/hotel/qa/test/helper
   * here located all Page Objects with main three classes for each page
     *  PageClass : contain each page elements locators
     *  PageAct : Contain all action methods needed for each page
     *  PageVerify : Contain all Verification methods needed to apply verification in each page
       
4. src/main/java/hotel/qa/test/helper
   * here small package has Test helper class
  
5. src/main/java/hotel/qa/test/utils
   * here located all utils Enums and classes to be used for tests

6. src/main/resources
   * here located application/Driver properties 
     *  application.properties : Property file contains application related configs 
     *  application-chrome-test.properties : Property file to apply all needed configs for chrome driver
     *  application-firefox-test.properties : Property file to apply all needed configs for firefox driver
     *  application-en-test.properties : property file contains expected assertions values in english (to be used or switched to different property file in case language change)
       
7. src/test/java/hotel/qa/test/CRUD
     * here located all Test Classes

## How_To_Run_Tests
1. Build current project locally ``` mvn clean install -Dmaven.test.skip=true  ```

2. Configure your preferred test browser
   * application-chrome-test.properties
   * application-firefox-test.properties

3. Choose your spring profile from application.properties 
   * spring.profiles.active = (chrome,firefox)
     When it is chrome , then you are choosing to run tests in chromium browser with english assertions (spring.profiles.group.chrome=chrome-test,en-test)
     When it is chrome , then you are choosing to run tests in firefox browser with english assertions (spring.profiles.group.firefox=firefox-test,en-test)

4. Open IntelliJ terminal
   
5. CD to main directory of current repository

6. Tests can run with different profile
   ``` mvn clean test -Pall-tests ``` (Test will run with maven profile all-tests , which will call tests in AllTests.xml)
   ``` mvn clean test -Pview-tests ``` (Test will run with maven profile view-tests , which will call tests in ViewTests.xml)
   ``` mvn clean test -Pdelete-tests ``` (Test will run with maven profile delete-tests , which will call tests in DeleteTests.xml)
   ``` mvn clean test -Pupdate-tests ``` (Test will run with maven profile update-tests , which will call tests in UpdateTests.xml)
   ``` mvn clean test -Pcreate-tests ``` (Test will run with maven profile create-tests , which will call tests in CreateTests.xml)
   If you need to override spring active profile , run  ``` mvn clean test --activate-profiles=your prefered active profile (chrome/firefox) ```

7. Run  ``` mvn allure:serve ``` to generate and browse Allure Report . It will be opened automatically
   







