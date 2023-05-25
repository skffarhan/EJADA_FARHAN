package TestLauncher;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	        features = {"@rerun/failed_scenarios.txt"}, 
	        monochrome = true, 
	        plugin = {
	        		"html:target/custom-reports/cucumber-htmlreports",
					},
	        glue = {"com.stepDefination"}
	        )
	public class TestTryFailures extends AbstractTestNGCucumberTests {
	 @Override
	    @DataProvider(parallel = false)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	}