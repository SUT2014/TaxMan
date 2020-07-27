/*
 *
 *  * Copyright (c) 2020.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.TaxMan.taxman;

import com.github.SUT2014.TaxMan.properties.PropertiesLoad;
import com.github.SUT2014.TaxMan.selenium.CustomSelenium;
import com.github.SUT2014.TaxMan.utils.FileUtils;
import com.github.SUT2014.TaxMan.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class taxman {
    //setup logger - final
    private static final Logger LOGGER = LogManager.getLogger(taxman.class);



    public static void main(String[] args) {

        String KEYS = "Tax";
        String BROWSER_KIND = "firefox";


        //LOGGER.debug("There is no spoon");
        try {
            if (PropertiesLoad.loadProp(LOGGER)) {
                LOGGER.debug(PropertiesLoad.getWebdriverDir());
            }
            CustomSelenium cs = new CustomSelenium(LOGGER, PropertiesLoad.getWebdriverDelay());
            List<String> REGs = StringUtils.getAllREGs(FileUtils.readAllFiles(PropertiesLoad.getInputDir()),
                    PropertiesLoad.getPATTERN());
            LOGGER.debug("RegNos retrieved from Input File:" + REGs.toString());
            cs.setDriver(BROWSER_KIND);
            for (String RegNo : REGs) {
                LOGGER.debug("***************************************************************************");
                //retrieve page information for each RegNo, keys are passed to retrieve elements
                cs.setURL(PropertiesLoad.getCheckarUrl());
                cs.findSendAndClick("vrm-input", RegNo, "Free Car Check");
                String retrievedCSV = cs.retrieveDetailsForKeys(KEYS);
                if (retrievedCSV.isEmpty()) {
                    LOGGER.error("Failed - RegNo:" + RegNo + " Car details not found");
                } else {
                    LOGGER.debug("Checking Car RegNo: " + RegNo);
                    LOGGER.debug("Taxed Until - " + retrievedCSV);
                }
                LOGGER.debug("***************************************************************************");
            }

            cs.close();
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
        finally {
            LOGGER.error("Closing Down..Goodbye !!");
        }
    }

}
