/*
 *
 *  * Copyright (c) 2020.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.TaxMan.Test.StepDefinition;

import com.github.SUT2014.TaxMan.properties.PropertiesLoad;
import com.github.SUT2014.TaxMan.selenium.CustomSelenium;
import com.github.SUT2014.TaxMan.taxman.taxman;
import com.github.SUT2014.TaxMan.utils.FileUtils;
import com.github.SUT2014.TaxMan.utils.StringUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;

import java.util.List;

public class CarTaxCheckFeatureSteps {


    private static final Logger LOGGER = LogManager.getLogger(taxman.class);
    CustomSelenium cs;
    List<String> REGs;


    @Before
    // setup tests - custom selenium, logging, properties etc.
    public void setup_tests(){
        try {
            PropertiesLoad.loadProp(LOGGER);
            cs = new CustomSelenium(LOGGER, PropertiesLoad.getWebdriverDelay());
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
            LOGGER.fatal("Cannot load Properties file, exiting...");
            System.exit(-1);
        }

    }


    @Given("^a set of files in (.*) directory containing registration numbers$")
    public void a_set_of_files_in_directory_containing_registration_numbers(String dir) throws Exception{
        try{
            REGs = StringUtils.getAllREGs(
                    FileUtils.readAllFiles(dir.equalsIgnoreCase("configured")?PropertiesLoad.getInputDir():dir),
                    PropertiesLoad.getPATTERN());
            LOGGER.debug("RegNos retrieved from Input File:" + REGs.toString());
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }


    @When("^(.*) browser is launched to open the (.*) website$")
    public void browser_is_opened_and_website_is_launched(String browser_kind, String URL) throws Exception{
        try{
            cs.setDriver(browser_kind.toLowerCase());
            cs.setURL(URL.equalsIgnoreCase("configured")?PropertiesLoad.getCheckarUrl():URL);
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }



    @Then("^for each vehicle registration number vehicle (.*) details are retrieved$")
    public void vehicle_tax_details_are_retrieved(String KEYS) {
        try{
            for (String RegNo : REGs) {
                LOGGER.debug("______________________________________________");
                //retrieve page information for each RegNo, keys are passed to retrieve elements
                cs.findSendAndClick("vrm-input", RegNo, "Free Car Check");
                String retrievedCSV = cs.retrieveDetailsForKeys(KEYS);
                //assertEquals(retrievedCSV.isEmpty(), false);  // not in scope currently
                if (retrievedCSV.isEmpty()) {
                    LOGGER.error("Failed - RegNo:" + RegNo + " Car details not found");
                } else {
                    LOGGER.debug("Checking Car RegNo: " + RegNo);
                    LOGGER.debug("Taxed Until - " + retrievedCSV);
                }
                LOGGER.debug("______________________________________________");
            }
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }


    @After
    //cleanup before exit
    public void cleanup(){
        cs.close();
    }

}
