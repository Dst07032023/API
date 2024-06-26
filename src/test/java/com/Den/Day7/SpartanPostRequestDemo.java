package com.Den.Day7;

import com.Den.pojo.Spartan;
import com.Den.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {

    /*
    Given accept type and Content type is JSON
    And request json body is:
    {
        "gender":"Male",
        "name":"Severus",
        "phone":8877445596
     }
     When user POST request to'/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     "A Spartan is Born!" message
     and same data what is posted
     */
    @Test
    public void postMethod1() {

        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Severus\",\n" +
                "\"phone\":8877445596";

        Response response = given().accept(ContentType.JSON).and()//what we are asking from api which is JSON response
                                    .contentType(ContentType.JSON)//what we are sending to api, which is JSON also
                                    .body(requestJsonBody)
                            .when()
                                    .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponceMessage = "A Spartan is Born!";
        assertThat(response.path("succes"),is(expectedResponceMessage));
        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();
    }
    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod2() {

        //create one object from your pojo,send it as a JSON

        Spartan spartan = new Spartan();
        spartan.setName("SeverusSpartan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and()//what we are asking from api which is JSON response
                .contentType(ContentType.JSON)//what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");


        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("succes"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("SeverusSpartan"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();
    }



    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3() {
        //this example we implement serialization with creating spartan object sending as a request body
        //also implemented deserialization getting the id, sending get request and saving that body as a response

        //create one object from your pojo,send it as a JSON

        Spartan spartan = new Spartan();
        spartan.setName("SeverusSpartan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and()//what we are asking from api which is JSON response
                .contentType(ContentType.JSON)//what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success",is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);
        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                        .and().pathParam("id",idFromPost)
                        .when().get("/api/spartans/{id}")
                        .then().statusCode(200).log().all().extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));
    }
    //    HomeWork

    //Create one SpartanUtil class
    //create o static method that returns Map<String,Object>
    //use faker library(add as dependency) to assign each time different information
    //for name, gender, phone number
    //then use your method for creating spartan as a map, dynamically.
}
