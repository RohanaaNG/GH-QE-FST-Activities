package RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Activity2 {
	@Test
	@Order(1)
	public void addNewUserFromFile() throws IOException {
		// Import JSON from test classpath (src/test/Resources or src/test/resources output)
		try (InputStream inputJSON = getClass().getClassLoader().getResourceAsStream("userInfo.json")) {
			if (inputJSON == null) {
				throw new IOException("userInfo.json not found on classpath");
			}

			Response response = given()
				.baseUri("https://petstore.swagger.io/v2/user") // Set base URI
				.header("Content-Type", "application/json") // Set headers
				.body(inputJSON) // Pass request body from stream
				.when().post(); // Send POST request

			// Assertion
			response.then().body("code", equalTo(200));
			response.then().body("message", equalTo("9901"));
		}
	}
	
	@Test
	@Order(2)
	public void getUserInfo() {
		// Use a consistent, writable path inside target so it exists during test runtime.
		String outputPath = System.getProperty("user.dir") + "/target/test-classes/activities/userGETResponse.json";
		File outputJSON = new File(outputPath);
		outputJSON.getParentFile().mkdirs();

		Response response = given()
			.baseUri("https://petstore.swagger.io/v2/user") // Set base URI
			.header("Content-Type", "application/json") // Set headers
			.pathParam("username", "justinc") // Pass request body from file
			.when().get("/{username}"); // Send POST request
		
		// Get response body
		String resBody = response.getBody().asPrettyString();
		System.out.println("Activity2 GET response body:\n" + resBody);
		System.out.println("Saving response to: " + outputJSON.getAbsolutePath());

		try {
			// Create JSON file
			outputJSON.createNewFile();
			// Write response body to external file
			FileWriter writer = new FileWriter(outputJSON.getPath());
			writer.write(resBody);
			writer.close();
		} catch (IOException excp) {
			excp.printStackTrace();
		}
		
		// Assertion
		response.then().body("id", equalTo(9901));
		response.then().body("username", equalTo("justinc"));
		response.then().body("firstName", equalTo("Justin"));
		response.then().body("lastName", equalTo("Case"));
		response.then().body("email", equalTo("justincase@mail.com"));
		response.then().body("password", equalTo("password123"));
		response.then().body("phone", equalTo("9812763450"));
	}
	
	@Test
	@Order(3)
	public void deleteUser() throws IOException {
		Response response = given()
			.baseUri("https://petstore.swagger.io/v2/user") // Set base URI
			.header("Content-Type", "application/json") // Set headers
			.pathParam("username", "justinc") // Add path parameter
			.when().delete("/{username}"); // Send POST request

		// Assertion
		response.then().body("code", equalTo(200));
		response.then().body("message", equalTo("justinc"));
	}
}