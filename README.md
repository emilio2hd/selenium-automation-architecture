# selenium-automation-architecture
[![Java CI](https://github.com/emilio2hd/selenium-automation-architecture/actions/workflows/ci.yml/badge.svg)](https://github.com/emilio2hd/selenium-automation-architecture/actions/workflows/ci.yml)

# Architecture
It's using spring, so it might leverage to understand some basic concepts about like
Dependency Injection and Inversion of Control.

# Continues Integration
The project has been made thinking on CI there are a couple of options that can be provided as maven params.
There is a "ci" profile in the `pom.xml` file, which will set to run the test headless on single thread with
log level = info.  In order to run the tests using the "ci" profile, use the following code:
```bash
mvn clean test -P ci
```

These default options can be changed passing maven parameters (using -D).

## Maven params
* test.parallelThreadCount - Number of threads to run the test suite in parallel. Default: `2`. CI Profile: `1`.
* test.browser - Which browser the tests should be use. Default: `chrome`. Available Options: `chrome` and `firefox`.
* test.headless - Should run the test suite headless or not. Default: `no`. CI Profile: `yes`.
* test.log_level - The level of information on logs. Default: `info`. CI Profile: `debug`.

Example:
```bash
mvn clean test -Dtest.parallelThreadCount=2 -Dtest.headless=true
```

# Screenshots

The screenshot can be found in folder `target/surefire-reports/screenshots`.
