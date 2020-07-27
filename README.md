# TaxMan

TaxMan is a BDD Cucumber style Test application written in Java which retrieves Vehicle Tax information for a given list of Vehicle Registration Numbers.  It uses Selenium to retrieve Tax details by comparing the list of Vehicle Registration Numbers against a well known database.
Typical Requirements
  - Read Input files and retrieve Registration Numbers. Multiple test input files may be generated.
  - Run Selenium based tests to verify test data.  
  - Retrieve Tax details.

# Features!

  - Retrieves Car Registration Number from input file.  Multiple input files are supported.  There is no specific format for this input file as long as the Registration Numbers are mentioned.
  - Uses REGEX to retrieve the Registration Numbers, REGEX string is configurable as well in the config.properties project file.
  - Using Car Regs from input file, retrieves Tax details from well known website, in this case it is https://cartaxcheck.co.uk/ .  This can be configured in the config.properties file.
  - The Vehicle tax details are logged, asserts are currently disabled.

TaxMan has been developed using the following tools/apps:

* [Java]     - Core Java 
* [Log4j2]   - Logging utility by Apache.
* [IntelliJ] - IDE
* [Selenium] - Firefox Webdriver
* [Cucumber] - Cucumber Packages(io.cucumber)
* [JUnit]    - JUnit Framework


And of course TaxMan itself is open source.

### Installation

Use Maven to build TaxMan.  the pom file can be found along with the source.
Typical Maven commands:
-- mvn clean test


input files:

# car_input.txt
--sample content--

There are multiple websites avaiable to check current car value in United Kingdom.The best of all is webuyanycar.com for intant valuation.The other examples are autotrader.com and confused.com. Checking example BMW with registration DN09HRM the value of the car is roughly around £3000. However car with registration BW57 BOW is not worth much in current market. There are multiple cars available higher than £10k with registraions KT17DLX andSG18 HTN.


# Generated Logs...
020-07-27 01:03:48,445 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] RegNos retrieved from Input File:[DN09HRM, BW57BOW, KT17DLX, SG18HTN]
2020-07-27 01:03:53,208 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________
2020-07-27 01:04:05,862 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] Checking Car RegNo: DN09HRM
2020-07-27 01:04:05,863 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] Taxed Until - 01 May 2021,
2020-07-27 01:04:05,863 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________
2020-07-27 01:04:05,863 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________
2020-07-27 01:04:16,395 ERROR com.github.SUT2014.TaxMan.taxman.taxman [main] Failed - RegNo:BW57BOW Car details not found
2020-07-27 01:04:16,396 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________
2020-07-27 01:04:16,396 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________
2020-07-27 01:04:26,827 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] Checking Car RegNo: KT17DLX
2020-07-27 01:04:26,827 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] Taxed Until - 01 Jun 2021,
2020-07-27 01:04:26,827 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________
2020-07-27 01:04:26,827 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________
2020-07-27 01:04:38,219 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] Checking Car RegNo: SG18HTN
2020-07-27 01:04:38,219 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] Taxed Until - 01 Oct 2020,
2020-07-27 01:04:38,220 DEBUG com.github.SUT2014.TaxMan.taxman.taxman [main] ______________________________________________


**Free Software, Hell Yeah!**
