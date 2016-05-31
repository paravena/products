package com.rakuten.examples.products.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber" },
        features = "classpath:cucumber/image_downloader.feature",
        glue = {"com.rakuten.examples.products.bdd"})
public class ProductsBDDRunnerTest {

}
