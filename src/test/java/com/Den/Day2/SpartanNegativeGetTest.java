package com.Den.Day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method
        baseURI = "http://52.207.61.129:8000";
    }

    /*
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
     */

    @DisplayName("GET request to /api/sprtans/10")
    @Test
    public void test1(){
        Response response = given()
                                     .accept(ContentType.XML)
                            .when()
                                     .get("/api/spartans/search");
        //verify status code is 406
        assertEquals(406,response.statusCode());
        //verify content type
        assertEquals("application/xml;charset=UTF-8",response.contentType());
    }

}
