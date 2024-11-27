package roman.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Users")  // The table name in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto increment for the user_id field
    @Column(name = "user_id")
    private int userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "rights", nullable = false)
    private String rights;

    @Column(name = "password", nullable = false)
    private String password;

//    @Column(name = "created_at", nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Default constructor (needed by Hibernate)
    public User() {
    }

    // Constructor for convenience
    public User(String email, String rights, String password) {
        this.email = email;
        this.rights = rights;
        this.password = password;
//        this.createdAt = new Date();  // Set current timestamp for created_at
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

