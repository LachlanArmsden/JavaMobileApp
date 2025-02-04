package example.application;

import example.data.StaticDatabaseConnection;
import example.data.StaticUserDAO;
import example.data.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void userConstructorTest() {
        User user = new User("Lookedshiv3", "Dinosaur!11", "shivaansundar@gmail.com", "tourist");
        assertEquals("Lookedshiv3", user.getUserName());
        assertEquals("shivaansundar@gmail.com", user.getEmail());
        assertEquals("Dinosaur!11", user.getPassword());
    }

    @Test
    public void setUserNameTest() {
        User user = new User("Lookedshiv3", "Dinosaur!11", "shivaansundar@gmail.com", "tourist");
        assertEquals("Lookedshiv3", user.getUserName());
    }

    @Test
    public void setEmailTest() {
        User user = new User("Lookedshiv3", "Dinosaur!11", "shivaansundar@gmail.com", "tourist");
        assertEquals("shivaansundar@gmail.com", user.getEmail());
    }

    @Test
    public void setPasswordTest() {
        User user = new User("Lookedshiv3", "Dinosaur!11", "shivaansundar@gmail.com", "tourist");
        assertEquals("Dinosaur!11", user.getPassword());
    }

}