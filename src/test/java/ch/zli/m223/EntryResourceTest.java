package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Specify the order of test methods
public class EntryResourceTest {

    @Test
    @Order(1)
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }
    // testCreateEndpoint with BODY
    @Test
    @Order(2)
    public void testCreateEndpoint() {
        given().contentType(ContentType.JSON)
        .body("{\"checkIn\": \"2022-03-10T12:15:50\",\"checkOut\": \"2022-03-10T12:15:50\"}")
          .when().post("/entries")
          .then()
             .statusCode(200)
             .body(is("{\"id\":1,\"checkIn\":\"2022-03-10T12:15:50\",\"checkOut\":\"2022-03-10T12:15:50\",\"category\":null,\"tags\":null}"));
    }
    // @Test
    // public void testCreateEndpointFail() {
    //     given().contentType(ContentType.JSON)
    //       .when().post("/entries")
    //       .then()
    //          .statusCode(500);
    // }

    // testDeleteEndpoint with ID
    // @Test
    // public void testDeleteEndpoint() {

    //     given().contentType(ContentType.JSON)
    //       .when().delete("/entries/1")
    //       .then()
    //          .statusCode(204);
    // }

    // testDeleteEndpoint but insert before test a new entry
    @Test
    @Order(3)
    public void testDeleteEndpoint() {
        given().contentType(ContentType.JSON)
        .body("{\"checkIn\": \"2022-03-10T12:15:50\",\"checkOut\": \"2022-03-10T12:15:50\"}")
          .when().post("/entries")
          .then()
             .statusCode(200)
             .body(is("{\"id\":2,\"checkIn\":\"2022-03-10T12:15:50\",\"checkOut\":\"2022-03-10T12:15:50\",\"category\":null,\"tags\":null}"));

        given()
          .when().delete("/entries/2")
          .then()
             .statusCode(204);
    }

    @Test
    @Order(4)
    public void testEditEndpoint() {
        given().contentType(ContentType.JSON)
                .body("{\"id\":3,\"checkIn\":\"2022-03-11T12:15:50\",\"checkOut\": \"2022-03-10T12:15:50\"}")
                .when().put("/entries")
                .then()
                .statusCode(200)
                .body(is("{\"id\":3,\"checkIn\":\"2022-03-11T12:15:50\",\"checkOut\":\"2022-03-10T12:15:50\",\"category\":null,\"tags\":null}"));
    }
}