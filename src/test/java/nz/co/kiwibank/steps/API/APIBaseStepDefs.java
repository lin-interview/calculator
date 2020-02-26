package nz.co.kiwibank.steps.API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * API Base step definitions
 * initiate request and store common methods
 *
 * @author Lin
 * @since 26/02/2020
 */

public class APIBaseStepDefs {
    public static final String BASE_URI = "https://calculator-api.azurewebsites.net/api/";
    protected static RequestSpecification Request;

    protected void initCalculatorAPI() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(BASE_URI);
        builder.setContentType(ContentType.JSON);
        RequestSpecification requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }

    protected ResponseOptions<Response> PostOpsWithBodyAndPathParams(String url, Map<String, String> pathParams, Map<String, String> body)  {
        Request.pathParams(pathParams);
        Request.body(body);
        return Request.post(url);
    }

    protected ResponseOptions<Response> PostOpsWithBody(String url,Map<String, Object> body)  {
        Request.body(body);
        return Request.post(url);
    }
}
