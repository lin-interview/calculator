package nz.co.kiwibank.steps.UI;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * UI Base step definitions
 * initiate WebDriver and store common methods
 *
 * @author Lin
 * @since 26/02/2020
 */

public class UIBaseStepDefs {

    protected static final Logger LOGGER = Logger.getLogger(UIBaseStepDefs.class.getName());
    private static WebDriver driver = null;
    private static String baseUrl = null;

    public static WebDriver getWebDriver(){
        if(null == driver){
            driver = initWebDriver();
        }
        return driver;
    }

    public static void Destroy(){
        driver = null;
        baseUrl = null;
    }

    private static WebDriver initWebDriver() {
        if(SystemUtils.IS_OS_MAC_OSX){
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bin\\chromedriverformac");
        } if(SystemUtils.IS_OS_LINUX){
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bin\\chromedriverforlinux");
        }else {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\bin\\chromedriver.exe");
        }

        driver = new ChromeDriver();
        //set timeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        //To maximize browser
        driver.manage().window().maximize();
        return driver;
    }

    public String getCurrentTimeStr(String timePattern){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(timePattern);
        return dateFormat.format(date);
    }
}
