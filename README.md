# connect-four
A command line version of the game Connect Four.

## To build the app:
Required to build:
- Maven 3.5 - can be downloaded [here](https://maven.apache.org/index.html)
- Java 8.x - can be downloaded [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
```
mvn package
```

## To run the app:
- In order to play, you will need to use a terminal that [supports ANSI escape codes](https://en.wikipedia.org/wiki/ANSI_escape_code#Platform_support).
Mostly this means don't use Windows command prompt to run this! If you are on windows please use a unix-like terminal, such as [git bash](https://git-scm.com/downloads).
- using spring boot:
```
mvn spring-boot:run
```