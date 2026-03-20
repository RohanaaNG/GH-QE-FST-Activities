package RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.*;

import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActivityPet {

    static long petId;

    // pOST - Create Pet (Explicit stable ID)
    @Test
    @Order(1)
    public void createPet() {

        long requestedId = 12345L;
        System.out.println("Creating Pet with explicit ID: " + requestedId);

        Response response = given()
            .baseUri("https://petstore.swagger.io/v2/pet")
            .header("Content-Type", "application/json")
            .body("{ \"id\": " + requestedId + ", \"name\": \"RohanPet\", \"status\": \"available\" }")
        .when()
            .post();

        // Capture ID
        petId = response.jsonPath().getLong("id");

        System.out.println(" Pet Created:");
        System.out.println(response.asPrettyString());
        System.out.println(" Auto Generated Pet ID: " + petId);

        response.then().statusCode(200);
        response.then().body("name", equalTo("RohanPet"));
        response.then().body("status", equalTo("available"));
    }

    // GET - Fetch Pet Details
    @Test
    @Order(2)
    public void getPet() {

        System.out.println("Fetching Pet Details");

        Response response = given()
            .baseUri("https://petstore.swagger.io/v2/pet")
            .pathParam("id", petId)
        .when()
            .get("/{id}");

        System.out.println(" Pet Details:");
        System.out.println(response.asPrettyString());

        response.then().statusCode(200);
        long responseId = response.jsonPath().getLong("id");
        Assertions.assertEquals(petId, responseId, "Pet ID should match requested ID");
    }

    //  PUT - Update Pet Status
    @Test
    @Order(3)
    public void updatePet() {

        System.out.println(" Updating Pet Status to SOLD");

        Response response = given()
            .baseUri("https://petstore.swagger.io/v2/pet")
            .header("Content-Type", "application/json")
            .body("{ \"id\": " + petId + ", \"name\": \"RohanPet\", \"status\": \"sold\" }")
        .when()
            .put();

        System.out.println(" Updated Response:");
        System.out.println(response.asPrettyString());

        response.then().statusCode(200);
        response.then().body("status", equalTo("sold"));
    }

    // GET - Verify Update
    @Test
    @Order(4)
    public void verifyUpdate() {

        System.out.println(" Verifying Updated Status");

        Response response = given()
            .baseUri("https://petstore.swagger.io/v2/pet")
            .pathParam("id", petId)
        .when()
            .get("/{id}");

        System.out.println("Verified Response:");
        System.out.println(response.asPrettyString());

        response.then().statusCode(200);
        response.then().body("status", equalTo("sold"));
    }

    //  DELETE - Delete Pet
    @Test
    @Order(5)
    public void deletePet() {

        System.out.println("🔹 Deleting Pet");

        Response response = given()
            .baseUri("https://petstore.swagger.io/v2/pet")
            .pathParam("id", petId)
        .when()
            .delete("/{id}");

        System.out.println(" Delete Response:");
        System.out.println(response.asPrettyString());

        response.then().statusCode(200);
    }

    //  GET - Verify Deletion
    @Test
    @Order(6)
    public void verifyDeletion() {

        System.out.println("🔹 Verifying Pet Deletion");

        Response response = given()
            .baseUri("https://petstore.swagger.io/v2/pet")
            .pathParam("id", petId)
        .when()
            .get("/{id}");

        System.out.println(" Final Response:");
        System.out.println(response.asPrettyString());

        // Pet should not exist now
        response.then().statusCode(404);
    }
}


