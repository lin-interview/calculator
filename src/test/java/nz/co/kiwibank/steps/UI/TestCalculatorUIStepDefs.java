package nz.co.kiwibank.steps.UI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.kiwibank.pages.PAGE_Calculator;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

/**
 * Calculator UI step definitions
 * step definitions for each step in the UI test scenarios
 *
 * @author Lin
 * @since 26/02/2020
 */

public class TestCalculatorUIStepDefs extends UIBaseStepDefs{

    PAGE_Calculator page = new PAGE_Calculator(getWebDriver());

    @Given("I already opened website {string}")
    public void iAlreadyOpenedWebsite(String url) {
        getWebDriver().navigate().to(url);
    }

    @When("^I (type in|choose) \"([^\"]*)\" as the \"([^\"]*)\"$")
    public void iTypeInAsThe(String operation, String inputTerm, String elementFlag) {
        if("type in".equals(operation)){
            if("left number".equals(elementFlag)){
                page.setLeftNumberTextBox(inputTerm);
            }
            if("right number".equals(elementFlag)){
                page.setRightNumberTextBox(inputTerm);
            }
        }
        if("choose".equals(operation)){
            if("operator".equals(elementFlag)){
                page.setOperatorDropList(inputTerm);
            }
        }
    }

    @And("I click the calculator button")
    public void iClickTheCalculatorButton() {
        page.clickCalculateButton();
    }

    @Then("I should see {string} displayed as result")
    public void iShouldSeeDisplayedAsResult(String expectedResult) throws InterruptedException {
        Assert.assertEquals(expectedResult, page.getResult());
    }

    @And("I have switched to the iFrame {string}")
    public void iHaveSwitchedToTheIFrame(String frameName) {
        getWebDriver().switchTo().frame(frameName);
    }

    @Then("I should see {string} as the {string}")
    public void iShouldSeeAsThe(String expectedValue, String elementFlag) {
        String actualValue = "";
        if("left number".equals(elementFlag)){
            actualValue = page.getLeftNumberTextBoxAttribute("value");
        }
        if("right number".equals(elementFlag)){
            actualValue = page.getRightNumberTextBoxAttribute("value");
        }
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Then("I should see {string} as error message")
    public void iShouldSeeAsErrorMessage(String expectedValue) {
        Assert.assertTrue(getWebDriver().getPageSource().contains(expectedValue));
    }

    @Then("I should see the attribute {string} of {string} is {string}")
    public void iShouldSeeTheAttributeOfIs(String attributeName, String elementFlag, String expectedValue) {
        if("resultTextBox".equals(elementFlag)){
            Assert.assertEquals(page.getResultTextBoxAttribute(attributeName), expectedValue);
        }
    }
}
