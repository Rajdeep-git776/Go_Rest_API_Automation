package com.gorest.users.tests;

import core.StatusCode;
import helper.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllUsers  extends BaseTest {
    @Test
    public void validateGetAllUsers(){

        Response response  =given().contentType(ContentType.JSON).when().get("/public/v2/users");
        int actualStatusCode=response.statusCode();
        Assert.assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
        Assert.assertNotNull(response.body());
        List<String> names =response.jsonPath().getList("name");
        Assert.assertTrue(names.contains("Rohit Khan"),"The name is not present");




    }
}
