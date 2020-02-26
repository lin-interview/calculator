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

public class TestCalculatorAPISetpDefs extends APIBaseStepDefs {
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

    @When("I perform POST operation for {string} with {int} {int} and {string}")
    public void iPerformPOSTOperationForWithUnacceptableTypeAnd(String url, int leftNumber, int rightNumber, String operator) {
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

    @When("I perform POST operation for {string} with unacceptable type {string} {string} and {string}")
    public void iPerformPOSTOperationForWithUnacceptableTypeAnd(String url, String leftNumber, String rightNumber, String operator) {
        response = PostOpsWithBody(url, setBody(leftNumber, rightNumber, operator));
    }
}