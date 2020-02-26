package nz.co.kiwibank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PAGE_Calculator extends PAGE_Base{

    public PAGE_Calculator(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "leftNumber")
    WebElement leftNumberTextBox;

    @FindBy(how = How.ID, using = "rightNumber")
    WebElement rightNumberTextBox;

    @FindBy(how = How.ID, using = "operator")
    WebElement operatorDropList;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Calculate')]")
    WebElement calculatorButton;

    @FindBy(how = How.CLASS_NAME, using = "result")
    WebElement resultTextBox;

    public void setLeftNumberTextBox(String leftNumber){
        clearBeforeSendKeys(leftNumberTextBox, leftNumber);
    }

    public void setRightNumberTextBox(String rightNumber){
        clearBeforeSendKeys(rightNumberTextBox,rightNumber);
    }

    public void setOperatorDropList(String operator){
        selectByVisibleText(operatorDropList, operator);
    }

    public void clickCalculateButton(){
        click(calculatorButton);
    }

    public String getResult(){
        waitForTheResult(driver, timeout);
        return getResultTextBoxAttribute("value");
    }

    public String getLeftNumberTextBoxAttribute(String attributeName){
        return leftNumberTextBox.getAttribute(attributeName);
    }
    public String getRightNumberTextBoxAttribute(String attributeName){
        return rightNumberTextBox.getAttribute(attributeName);
    }

    public String getResultTextBoxAttribute(String attributeName){
        return resultTextBox.getAttribute(attributeName);
    }
}
