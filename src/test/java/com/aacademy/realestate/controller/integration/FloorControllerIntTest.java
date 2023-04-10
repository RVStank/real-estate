package com.aacademy.realestate.controller.integration;

import com.aacademy.realestate.dto.FloorDTO;
import com.aacademy.realestate.model.Floor;
import com.aacademy.realestate.repository.FloorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FloorControllerIntTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FloorRepository floorRepository;

    @Test
    public void saveFloor() throws Exception {

        FloorDTO floorDTO = FloorDTO.builder().number(5).build();
        String jsonRequest = objectMapper.writeValueAsString(floorDTO);

        given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .post("http://localhost:8080/floors")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("number", equalTo(5));
    }

    @Test
    public void updateFloor() throws Exception {

        floorRepository.save(Floor.builder().number(1).build());
        FloorDTO floorDTO = FloorDTO.builder().id(1L).number(5).build();
        String jsonRequest = objectMapper.writeValueAsString(floorDTO);

        given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .put("http://localhost:8080/floors/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("number", equalTo(5));
    }

    @Test
    public void findByNumber() throws Exception {
        floorRepository.save(Floor.builder().number(1).build());

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/floors/number/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("number", equalTo(5));
    }
}
