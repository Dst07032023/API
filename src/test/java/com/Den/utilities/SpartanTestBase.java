package com.Den.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method
        baseURI = "http://52.207.61.129:8000";

        //get ip address from configurations
    }
}