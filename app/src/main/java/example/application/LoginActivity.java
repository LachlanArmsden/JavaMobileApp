package example.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import example.data.StaticDatabaseConnection;
import example.data.StaticUserDAO;
import example.data.User;
import example.miscellaneous.Util;

/**
 * Represents the login view of the application.
 */
public class LoginActivity extends AppCompatActivity {
    // Declare class variables for the views
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    public static String adminUsername;
    public static String adminEmail;
    public static String adminPassword;
    public static String adminType;
    // Declare class variables for the DAO and current user
    private final StaticUserDAO staticUserDAO = new StaticUserDAO();
    private User currentUser;

    /**
     * Creates the login view.
     * @param savedInstanceState the saved instance state
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Sustainable Tourism");

        // Initialize the views
        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        // Set up a click listener for the login button
        loginButton.setOnClickListener(v -> onLoginButtonClick());
        // Set up a click listener for the register button
        registerButton.setOnClickListener(v -> onRegisterButtonClick());

        // do not allow blank spaces in email and password
        Util.removeSpace(emailEditText);
        Util.removeSpace(passwordEditText);

        // On start add the admin account
        adminAccount("admin", "admin@gmail.com", "Admin123!", "Admin");
    }

    private void adminAccount(String username, String email, String password, String type) {
        adminUsername = username;
        adminEmail = email;
        adminPassword = password;
        adminType = type;

        if (staticUserDAO.listUsers().size() == 0) {
            staticUserDAO.addUser(new User(username, email, password, type));
        }
    }
    /**
     * Event handler for the register button click event.
     */
    private void onRegisterButtonClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Event handler for the login button click event.
     */
    private void onLoginButtonClick(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean success = logIn(email, password);
        if (success) {
            // check if it is the admin account
            String typeComparer = currentUser.getType();
            if(typeComparer.equals(adminType)) {
                //launchAdminView();
                launchMainView();
            }
            // Otherwise transition to the main view
            launchMainView();
        } else {
            // Show an error message
            popupMessage("Invalid email and password combination");
        }
    }

    private void popupMessage(String text) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.popup_message, (ViewGroup) findViewById(R.id.toast_root));

        TextView message = layout.findViewById(R.id.message_title_textview);
        message.setText(text);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    /**
     * Logs the user in.
     * @param email the email
     * @param password the password
     * @return true if the user was logged in, false otherwise
     */
    private boolean logIn(String email, String password) {
        for (User user : staticUserDAO.listUsers()) {
            // Compare the email and password of each user to the specific input
            String emailComparer = user.getEmail();
            String passwordComparer = user.getPassword();
            if (emailComparer.equals(email) && passwordComparer.equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    /**
     * Launches the main view.
     */
    private void launchMainView() {
        Intent intent = new Intent(this, MainViewActivity.class);
        intent.putExtra("CURRENT_USER", currentUser.getUserName());

        startActivity(intent);
    }

    /*private void launchAdminView() {
        Intent intent = new Intent(this, AdminViewActivity.class);
        startActivity(intent);
    }*/


}