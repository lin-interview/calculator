package nz.co.kiwibank;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;
import nz.co.kiwibank.steps.UI.UIBaseStepDefs;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:report/cucumber-pretty", "json:report/cucumber.json"}, tags = {"@API or @UI"}, features = "classpath:features")
public class RunAllTest {
    protected static final Logger LOGGER = Logger.getLogger(RunAllTest.class.getName());
    @AfterClass
    public static void generateReport() {
        LOGGER.log(Level.INFO, "Generating Pretty Cucumber Report...");
        File reportOutputDirectory = new File("report");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("report/cucumber.json");

        String buildNumber = "1";
        String projectName = "Technical Test For Lin";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration - check javadoc for details
        // configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
        // do not make scenario failed when step has status SKIPPED
        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
        configuration.setBuildNumber(buildNumber);

        try {
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
            LOGGER.log(Level.INFO, "Finished");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occured generating pretty report" + e.toString());
        }
    }
}
