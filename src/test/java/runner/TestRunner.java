package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/java/features",
        glue = ("steps"),
        tags =("@smokeTest"),
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:extendReport/report.html"}
)

public class TestRunner {

}
