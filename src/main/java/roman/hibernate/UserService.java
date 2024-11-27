package roman.hibernate;

import org.hibernate.Session;
import roman.models.User;
import roman.utils.HibernateUtil;

public class UserService {

    public void createUser(String email, String rights, String password) {
        // Create a new User object
        User user = new User(email, rights, password);

        // Get session
        Session session = HibernateUtil.getSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Save the user object
            session.save(user);

            // Commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
