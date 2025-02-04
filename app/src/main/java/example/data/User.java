package example.data;

import android.text.InputFilter;
import android.widget.EditText;

/**
 * Represents a user of the application.
 */
public class User {
    private String userName;
    private String password;
    private String email;
    private String type;

    /**
     * Creates a new User.
     *
     * @param userName     The name of the user.
     * @param password The password of the user.
     * @param email    The email of the user.
     * @param type    The email of the user.
     */
    public User(String userName, String email, String password, String type) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the type of the user.
     *
     * @return The type of the user.
     */
    public String getType() {
        return type;
    }
}
