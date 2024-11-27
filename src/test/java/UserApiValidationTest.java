public class UserApiValidationTest {

    @Test
    public void validateUserCreated() {
        // Base URI
        RestAssured.baseURI = "http://localhost:8080/api/users";

        // User to validate
        String username = "testUser";

        // Send GET Request
        int statusCode = RestAssured.given()
                .pathParam("username", username)
                .get("/{username}")
                .getStatusCode();

        // Assert User Exists
        Assert.assertEquals(statusCode, 200, "User does not exist!");
    }
}
