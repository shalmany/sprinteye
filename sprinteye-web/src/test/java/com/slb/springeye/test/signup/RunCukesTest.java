package com.slb.springeye.test.signup;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.slb.springeye.test",	 }
,features="src/test/resources/features/")

public class RunCukesTest {
}

