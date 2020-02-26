package nz.co.kiwibank.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;
import nz.co.kiwibank.RunAllTest;
import nz.co.kiwibank.steps.UI.UIBaseStepDefs;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class Hooks extends UIBaseStepDefs {

    @After("not @API")
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                File screenshot = ((TakesScreenshot) Current()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File(".\\screenshots\\" + scenario.getName() + ".png"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (Current() != null) {
                Current().quit();
                Destroy();
            }
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING, "Failed to close browser session");
        }
    }
}
