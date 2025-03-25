package com.gorest.users.tests;

import core.StatusCode;
import helper.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.UserPayload;

import java.util.HashMap;


import static io.restassured.RestAssured.given;
import static utils.TestUtil.testData;

public class CreateUser extends BaseTest {
    int id = 0;

    @Test(priority = 1)
    public void validateCreateUserAPI() {
        UserPayload payload = new UserPayload();
        payload.setName("walter Silver");
        payload.setEmail("walter@mail.com");
        payload.setGender("female");
        payload.setStatus("active");
        Response createUserResp = given().contentType(ContentType.JSON).header("Authorization", "Bearer 8dca99b56a15365345426cb53a7ef085de75cab3924802db5b1ae9a9dfa193ac").body(payload).when().post("/public/v2/users");
        Assert.assertEquals(createUserResp.statusCode(), StatusCode.CREATED.code);
        Assert.assertNotNull(createUserResp.body());
        Assert.assertNotNull(createUserResp.jsonPath().get("id"), "'id' is not present");
        id = createUserResp.jsonPath().get("id");


    }

    @Test(description = "Validates user creation without Auth Token")
    public void validateCreateUserWithoutAuthToken() {
        UserPayload payload = new UserPayload();
        payload.setName("Alder Silver");
        payload.setEmail("alder@mail.com");
        payload.setGender("male");
        payload.setStatus("active");
        Response createUserResp = given().contentType(ContentType.JSON).body(payload).when().post("/public/v2/users");
        Assert.assertEquals(createUserResp.statusCode(), StatusCode.UNAUTHORIZED.code);
        Assert.assertNotNull(createUserResp.body());
        Assert.assertEquals(createUserResp.jsonPath().get("message"), "Authentication failed");

    }

    @Test
    public void validateDuplicateUserCreation() {
        UserPayload payload = new UserPayload();
        payload.setName("Alder Silver");
        payload.setEmail("alder@mail.com");
        payload.setGender("male");
        payload.setStatus("active");
        Response createUserResp = given().contentType(ContentType.JSON).header("Authorization", "Bearer 8dca99b56a15365345426cb53a7ef085de75cab3924802db5b1ae9a9dfa193ac").body(payload).when().post("/public/v2/users");
        Assert.assertEquals(createUserResp.statusCode(), 422);
        Assert.assertNotNull(createUserResp.body());


    }

    @Test(dependsOnMethods = "validateCreateUserAPI", priority = 2)
    public void validateUpdatingUserDetailsUsingPutMethod() {
        HashMap<String, Object> putPayload = new HashMap<>();
        putPayload.put("name", "Morris");
        putPayload.put("gender", "female");
        putPayload.put("email", "morris12@mail.com");
        putPayload.put("status", "active");

        Response putRespbdy = given().header("Authorization", "Bearer 8dca99b56a15365345426cb53a7ef085de75cab3924802db5b1ae9a9dfa193ac")
                .contentType(ContentType.JSON).body(putPayload).when().put("/public/v2/users/{id}", id);

        System.out.println(putRespbdy.body().asString());
        Assert.assertEquals(putRespbdy.jsonPath().get("name"), putPayload.get("name"));


    }


    @Test(dataProvider = "create user data")
    public void validateCreateUserAPIWithMultipleInput(String name, String email, String gender, String status) {
        UserPayload payload = new UserPayload();
        payload.setName(name);
        payload.setEmail(email);
        payload.setGender(gender);
        payload.setStatus(status);
        Response createUserResp = given().contentType(ContentType.JSON).header("Authorization", "Bearer 8dca99b56a15365345426cb53a7ef085de75cab3924802db5b1ae9a9dfa193ac").body(payload).when().post("/public/v2/users");
        Assert.assertEquals(createUserResp.statusCode(), StatusCode.CREATED.code);
        Assert.assertNotNull(createUserResp.body());
        Assert.assertNotNull(createUserResp.jsonPath().get("id"), "'id' is not present");


    }
    @DataProvider(name = "create user data")
    public Object[][] getData(){
        Object[][] data=testData("/Users/rajdeeproy/IdeaProjects/Go_Rest_API_Automation/resources/testData/APIData.xlsx","createUser");

        return data;
    }

}
