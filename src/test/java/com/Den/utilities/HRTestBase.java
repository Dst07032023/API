package com.Den.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method
        baseURI = "http://52.207.61.129:1000/ords/hr";

        //get ip address from configurations
        String dbUrl = "jdbc:oracle:thin:@52.207.61.129:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

//        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public void teardown(){

//        DBUtils.destroy();
    }
}
