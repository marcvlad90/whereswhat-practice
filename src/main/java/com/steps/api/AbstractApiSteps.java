package com.steps.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.MultiPartSpecBuilder;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.tools.constants.ApiUrlConstants;
import com.tools.constants.HeaderConstants;

import net.thucydides.core.steps.ScenarioSteps;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractApiSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    public static RequestSpecification tokenSpec = null;
    public static Map<String, String> extraHeaders = new HashMap<String, String>();

    public static RequestSpecification getSpecWithExtraHeaders() {
        tokenSpec = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(ApiUrlConstants.BASE_URI)
        .addHeader(HeaderConstants.API_HEADER_USER_AGENT, "web_agent")
        .addHeaders(extraHeaders)
        .build();
        return tokenSpec;
    }

    protected static <T> T createResource(String path, Object requestBody, Class<T> responseClass) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .body(requestBody)
                .when().post(path)
                .then()
                .assertThat().statusCode(anyOf(is(201), is(200), is(302)))
                .extract().as(responseClass);
    }

    protected static <T> T createResourceWithFailure(String path, Object requestBody, Class<T> responseClass) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .body(requestBody)
                .when().post(path)
                .then()
                .assertThat().statusCode(anyOf(is(400), is(401), is(403), is(404)))
                .extract().as(responseClass);
    }

    protected static <T> T updateResourceWithFailure(String path, Object requestBody, Class<T> responseClass, Object... params) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .body(requestBody)
                .when().put(path, params)
                .then()
                .assertThat().statusCode(anyOf(is(400), is(401), is(403), is(404)))
                .extract().as(responseClass);
    }

    protected static <T> T updateResource(String path, Object requestBody, Class<T> responseClass, Object... params) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .body(requestBody)
                .when().put(path, params)
                .then()
                .assertThat().statusCode(anyOf(is(201), is(200), is(302)))
                .extract().as(responseClass);
    }

    protected static void updateResource(String path, Object requestBody, Object... params) {
        given().relaxedHTTPSValidation()
        .spec(getSpecWithExtraHeaders())
        .body(requestBody)
        .when().put(path, params)
        .then()
        .assertThat().statusCode(anyOf(is(201), is(200), is(302)));
    }

    protected String uploadCSVResource(String path, String pathToFile, String fileName) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .multiPart(new MultiPartSpecBuilder(new File(pathToFile + fileName)).fileName(fileName).mimeType("application/vnd.ms-excel").build())
                .when().post(path)
                .then()
                .assertThat().statusCode(anyOf(is(201), is(204), is(200), is(302)))
                .extract().response().asString();
    }

    protected String createItemFromCSV(String path) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .when().post(path)
                .then()
                .assertThat().statusCode(anyOf(is(201), is(204), is(200), is(302)))
                .extract().response().asString();
    }

    public static RequestSpecification getUploadFileSpecWithExtraHeaders(String entityType, int entity_id) {

        tokenSpec = new RequestSpecBuilder()
        .setContentType("multipart/form-data")
        .setBaseUri(ApiUrlConstants.BASE_URI)
        .addHeader(HeaderConstants.API_HEADER_USER_AGENT, "web_agent")
        .addHeader(HeaderConstants.API_HEADER_ENTITY_TYPE, entityType)
        .addHeader(HeaderConstants.API_HEADER_ENTITY_ID, String.valueOf(entity_id))
        .addHeaders(extraHeaders)
        .build();

        return tokenSpec;
    }

    protected String uploadResourceToEntity(String path, String filePath, String entityType, int entityId) {
        return given().relaxedHTTPSValidation()
                .spec(getUploadFileSpecWithExtraHeaders(entityType, entityId))
                .multiPart(new File(filePath))
                .when()
                .post(path)
                .then()
                .assertThat().statusCode(anyOf(is(201), is(204), is(200), is(302)))
                .extract().response().asString();
    }

    protected <T> T getResource(String path, Class<T> responseClass) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .when().get(path)
                .then()
                .assertThat().statusCode(anyOf(is(201), is(200), is(302)))
                .extract().as(responseClass);
    }

    protected <T> List<T> getResources(String path, Class<T> responseClass) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        json = given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .when().get(path)
                .then()
                .assertThat().statusCode(anyOf(is(201), is(204), is(200), is(302)))
                .extract().asString();

        @SuppressWarnings("unchecked")
        Class<T[]> arrayClass = (Class<T[]>)Class.forName("[L" + responseClass.getName() + ";");
        T[] objects = mapper.readValue(json, arrayClass);
        return Arrays.asList(objects);
    }

    protected static void deleteResource(String path, int id) {
        given().relaxedHTTPSValidation()
        .spec(getSpecWithExtraHeaders())
        .when().delete(path + "/" + id)
        .then()
        .assertThat().statusCode(anyOf(is(201), is(200), is(302)))
        .extract().response().asString();
    }

    protected static void deleteResourceWithoutAssertion(String path, int id) {
        given().relaxedHTTPSValidation()
        .spec(getSpecWithExtraHeaders())
        .when().delete(path + "/" + id)
        .then()
        .extract().response().asString();
    }

    protected static void deleteResource(String path) {
        given().relaxedHTTPSValidation()
        .spec(getSpecWithExtraHeaders())
        .when().delete(path)
        .then()
        .assertThat().statusCode(anyOf(is(200), is(201), is(204), is(302)))
        .extract().response().asString();
    }
}
