package nz.co.kiwibank.steps.API;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Calculator API step definitions
 * step definitions for each step in the API test scenarios
 *
 * @author Lin
 * @since 26/02/2020
 */

public class TestCalculatorAPIStepDefs extends APIBaseStepDefs {
    protected static ResponseOptions<Response> response;

    public HashMap<String, Object> setBody(Object leftNumber, Object rightNumber, Object operator){
        HashMap<String, Object> body = new HashMap<>();
        body.put("LeftNumber", leftNumber);
        body.put("RightNumber", rightNumber);
        body.put("Operator", operator);
        return body;
    }

    @Given("I have one calculator API")
    public void iHaveOneCalculatorAPI() {
        initCalculatorAPI();
    }

    @When("I perform POST operation for {string} with {int} {string} {int}")
    public void iPerformPOSTOperationForWithUnacceptableTypeAnd(String url, int leftNumber, String operator, int rightNumber) {
        response = PostOpsWithBody(url, setBody(leftNumber, rightNumber, operator));
    }

    @And("I should see the body has {string} as {string}")
    public void iShouldSeeTheBodyHasAs(String nodeName, String result) {
        assertThat(response.getBody().jsonPath().get(nodeName).toString(), equalTo(result));
    }


    @Then("the response code should be {string}")
    public void theResponseCodeShouldBe(String statusCode) {
        assertThat(response.getStatusCode(), equalTo(Integer.parseInt(statusCode)));
    }

    @When("I perform POST operation for {string} with unacceptable type {string} {string} {string}")
    public void iPerformPOSTOperationForWithUnacceptableTypeAnd(String url, String leftNumber, String operator, String rightNumber) {
        response = PostOpsWithBody(url, setBody(leftNumber, rightNumber, operator));
    }
}