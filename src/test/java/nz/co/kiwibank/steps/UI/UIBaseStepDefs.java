package nz.co.kiwibank.steps.UI;

import nz.co.kiwibank.pages.PAGE_Base;
import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class UIBaseStepDefs {

    protected static final Logger LOGGER = Logger.getLogger(UIBaseStepDefs.class.getName());
    private static WebDriver driver = null;
    private static String baseUrl = null;

    public static WebDriver Current(){
        if(null == driver){
            driver = CreatWebBrowser();
        }
        return driver;
    }

    public static void Destroy(){
        driver = null;
        baseUrl = null;
    }

    private static WebDriver CreatWebBrowser() {
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
}
