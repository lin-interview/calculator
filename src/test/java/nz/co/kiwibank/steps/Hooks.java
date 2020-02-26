package nz.co.kiwibank.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import nz.co.kiwibank.steps.UI.UIBaseStepDefs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.logging.Level;

/**
 * Hooks class
 * operations before or after each test
 *
 * @author Lin
 * @since 26/02/2020
 */

public class Hooks extends UIBaseStepDefs {
    @After("not @API")
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {

                File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File(".\\screenshots\\" + getCurrentTimeStr("yyyy-dd-MM--HH-mm-ss") + "_" + scenario.getName() + ".png"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (getWebDriver() != null) {
                getWebDriver().quit();
                Destroy();
            }
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING, "Failed to close browser session");
        }
    }
}
