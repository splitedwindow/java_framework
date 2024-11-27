import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import roman.models.User;
import roman.utils.HibernateConnector;

import static io.restassured.RestAssured.given;

public class ApiTest {
    @Test
    public void apiTest() {
        given()
                .when()
                .get("http://localhost:3000/")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void createUserAndVerifyInDatabaseTest() {
        Faker faker = new Faker();
        String randomUsername = faker.lorem().word();
        String email = randomUsername + "@example.com";
        String password = "securePassword";

        given()
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"" + email + "\", \"password\": \"" + password + "\" }")
                .when()
                .post("http://localhost:3000/api/register")
                .then()
                .assertThat()
                .statusCode(200);

        // Step 2: Verify user in the database
        Session session = HibernateConnector.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();

        Assert.assertNotNull(user, "User was not found in the database.");
        Assert.assertEquals(user.getEmail(), email, "Email does not match.");

        session.getTransaction().commit();
        session.close();
    }
}
