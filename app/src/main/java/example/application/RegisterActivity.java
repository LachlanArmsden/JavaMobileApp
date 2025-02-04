package example.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import example.data.StaticUserDAO;
import example.data.User;
import example.miscellaneous.Util;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerUserNameEditText;
    private EditText registerEmailEditText;
    private EditText registerPasswordEditText;
    private EditText registerConfirmPasswordEditText;
    private Button signupButton;
    private Button touristButton;
    private Button businessownerButton;
    private String type = null;
    private final StaticUserDAO staticUserDAO = new StaticUserDAO();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Sustainable Tourism");

        // Initialize the views
        registerUserNameEditText = findViewById(R.id.register_username_edittext);
        registerEmailEditText = findViewById(R.id.register_email_edittext);
        registerPasswordEditText = findViewById(R.id.register_password_edittext);
        registerConfirmPasswordEditText = findViewById(R.id.register_confirmpassword_edittext);
        touristButton = findViewById(R.id.tourist_button);
        businessownerButton = findViewById(R.id.business_owner_button);
        signupButton = findViewById(R.id.signup_button);

        // Set up a click listener for the signup button
        touristButton.setOnClickListener(v -> onTouristClick());
        businessownerButton.setOnClickListener(v -> onBusinessOwnerClick());
        signupButton.setOnClickListener(v -> onSignupClick());


        // Show the name EditText
        registerUserNameEditText.setVisibility(View.VISIBLE);

        // Set the focus to the name EditText
        registerUserNameEditText.requestFocus();

        // enable the email and password EditTexts
        registerEmailEditText.setEnabled(true);
        registerPasswordEditText.setEnabled(true);
        registerConfirmPasswordEditText.setEnabled(true);

        // do not allow blank spaces and special chars username
        // and do not allow blank spaces in email and password
        Util.removeSpaceAndSpecialChars(registerUserNameEditText);
        Util.removeSpace(registerEmailEditText);
        Util.removeSpace(registerPasswordEditText);
        Util.removeSpace(registerConfirmPasswordEditText);
    }

    private boolean validateUsername(String username) {
        if (username.equals(null) || username.length() < 5) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateEmail(String email) {
        int nonLetterCounter = 0;
        Integer suffixStart = email.lastIndexOf(".");
        String[] parts = email.split("@");

        for (int i = suffixStart + 1; email.length() > i; i++) {
            if (!Character.isLetter(email.charAt(i))) {
                nonLetterCounter++;
            }
        }

        if (email.equals(null) || email.length() < 10 || parts.length != 2 ||
                !email.contains(".") || nonLetterCounter != 0) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validatePassword(String password) {
        int capsCounter = 0;
        int digitCounter = 0;
        int specialCharCounter = 0;

        for (int i = 0; password.length() > i; i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                capsCounter++;
            }
            if (Character.isDigit(password.charAt(i))) {
                digitCounter++;
            }
            if (!Character.isLetter(password.charAt(i)) && !Character.isDigit(password.charAt(i))) {
                specialCharCounter++;
            }
        }

        if(capsCounter == 0 || digitCounter == 0 || specialCharCounter == 0 || password.length() < 8) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateType() {
        if (type == null) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkPasswordMatches(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkInternalMatches(String username, String email, String password) {
        if (username.equals(email) || email.equals(password) || password.equals(username)) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkExternalMatches(String username, String email, String password) {
        int matches = 0;
        for (User user : staticUserDAO.listUsers()) {
            if (user.getUserName().equals(username) || user.getEmail().equals(email) ||
                    user.getPassword().equals(password)) {
                matches++;
            }
        }

        if (matches != 0) {
            return false;
        }
        else {
            return true;
        }
    }

    // View if all user information is valid
    private boolean signin(String username, String email, String password, String confirmPassword) {
        boolean reviewUsername = validateUsername(username);
        boolean reviewEmail = validateEmail(email);
        boolean reviewPassword = validatePassword(password);
        boolean reviewType = validateType();
        boolean reviewInternalMatches = checkInternalMatches(username, email, password);
        boolean reviewExternalMatches = checkExternalMatches(username, email, password);
        boolean reviewPasswordMatches = checkPasswordMatches(password, confirmPassword);
        if (reviewUsername && reviewEmail && reviewPassword && reviewType && reviewInternalMatches
                && reviewExternalMatches && reviewPasswordMatches) {
            return true;
        }
        else {
            return false;
        }
    }

    private void onSignupClick() {
        // Get the username, email, password and type from the EditTexts
        String username = registerUserNameEditText.getText().toString();
        String email = registerEmailEditText.getText().toString();
        String password = registerPasswordEditText.getText().toString();
        String confirmPassword = registerConfirmPasswordEditText.getText().toString();

        boolean valid = signin(username, email, password, confirmPassword);
        if (valid) {
            // Add the user to the database
            staticUserDAO.addUser(new User(username, password, email, type));

            // Go back to the main log in page
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);;
        }
        else {
            // Show an error message
            Toast.makeText(RegisterActivity.this, "Please input all data and select the account type", Toast.LENGTH_SHORT).show();
        }
    }

    private void onTouristClick() {
        type = "Tourist";
    }

    private void onBusinessOwnerClick() {
        type = "Business Owner";
    }
}