# NewYorkTimesPopular
It is a simple app which shows popular news

To build and run the app
  ./gradlew build
  ./gradlew installDebug

To run all tests
  ./gradlew test connectedAndroidTest


To generate coverage report
  ./gradlew createDebugCoverageReport

Task will analyze code of our project in /src/main/java/ directory and unit tests placed in /src/androidTest/java/ directory.
After executing this task, we can find test coverage report in the following directory of the module:

  /build/outputs/reports/coverage/debug/





