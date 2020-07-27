/*
 *
 *  * Copyright (c) 2020.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.TaxMan.Test.Cucumber.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features",
        glue={"com.github.SUT2014.TaxMan.Test.StepDefinition"},
        plugin = {"pretty","html:target/reports/cucumber.html"}
)
public class RunCukesTest {

}

