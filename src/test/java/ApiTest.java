import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import roman.models.Folder;
import roman.models.User;
import roman.models.File;
import roman.models.Content;
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

    @Test
    public void createFolderTest() {
        Faker faker = new Faker();
        String name = faker.lorem().word();

        // Step 1: Create folder through the API
        given()
                .contentType(ContentType.JSON)
                .body("{ \"folder_name\": \"" + name + "\" }")
                .when()
                .post("http://localhost:3000/api/folder")
                .then()
                .assertThat()
                .statusCode(200);

        // Step 2: Verify folder in the database
        Session session = HibernateConnector.getSessionFactory().openSession();
        session.beginTransaction();

        // Fix: Use 'name' for querying, not 'folderId'
        Folder folder = session.createQuery("FROM Folder WHERE name = :name", Folder.class)
                .setParameter("name", name)  // Fix: Parameter should match the query
                .uniqueResult();

        Assert.assertNotNull(folder, "Folder was not found in the database.");
        Assert.assertEquals(folder.getName(), name, "Folder name does not match.");

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void createFileWithContent() {
        // Step 1: Prepare data for creating the file and its associated content
        Faker faker = new Faker();
        String QAWord = faker.lorem().word();
        String folderName = "Sub Folder"; // this one is static
        String filename = "QA test file " + QAWord;
        String contentText = "I need 51/100 " + QAWord;

        // Step 2: Create file with content using the API
        String payload = "{ \"array_of_objects\": [ { \"content_type\": \"text\", \"content_text\": \"" +
                contentText + "\", \"content_url\": null, \"sequence\": 1 } ], \"filename\": \""
                + filename + "\", \"folder_name\": \"" +
                folderName + "\" }";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("http://localhost:3000/api/file")
                .then()
                .assertThat()
                .statusCode(200);  // Ensure the file creation is successful

        // Step 3: Verify file in the database
        Session session = HibernateConnector.getSessionFactory().openSession();
        session.beginTransaction();

        // Verify that the file was created in the database
        File file = session.createQuery("FROM File WHERE title = :title", File.class)
                .setParameter("title", filename)
                .uniqueResult();

        Assert.assertNotNull(file, "File was not found in the database.");
        Assert.assertEquals(file.getTitle(), filename, "File title does not match.");

        // Verify that the content for this file was created
        Content content = session.createQuery("FROM Content WHERE file.id = :fileId AND contentText = :contentText", Content.class)
                .setParameter("fileId", file.getFileId())
                .setParameter("contentText", contentText)
                .uniqueResult();

        Assert.assertNotNull(content, "Content was not found in the database.");
        Assert.assertEquals(content.getContentText(), contentText, "Content text does not match.");
        Assert.assertEquals(content.getContentType(), Content.ContentType.text, "Content type does not match.");

        // Commit transaction and close session
        session.getTransaction().commit();
        session.close();
    }

}
