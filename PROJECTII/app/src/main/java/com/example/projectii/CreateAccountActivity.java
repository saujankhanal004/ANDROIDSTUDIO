package com.example.projectii;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.security.SecureRandom;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button createAccountButton;
    private FirebaseAuth firebaseAuth;
    private TextView randomNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        // Initialize UI components
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        createAccountButton = findViewById(R.id.btn_create_account);
        randomNumberTextView = findViewById(R.id.randomNumberTextView);

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Set up Create Account button click listener
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate email and password
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate password complexity
                if (!isPasswordValid(password)) {
                    Toast.makeText(CreateAccountActivity.this, "Pass 8 char with num,UC and low C", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new user account
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccountActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Account creation success
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Toast.makeText(CreateAccountActivity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                                // You might want to add additional logic or navigate to another activity here
                                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                // Account creation failed
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(CreateAccountActivity.this, " failed:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Set up Generate Random String button click listener
        Button generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomString();
            }
        });
    }

    // Password complexity validation method
    private boolean isPasswordValid(String password) {
        // Password must be at least 8 characters long and include at least one number, one uppercase letter, and one lowercase letter.
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return password.matches(passwordPattern);
    }

    // Random string generation method
    private void generateRandomString() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder randomString = new StringBuilder();

        // Generate at least one uppercase letter
        randomString.append((char) (secureRandom.nextInt(26) + 'A'));

        // Generate at least one lowercase letter
        randomString.append((char) (secureRandom.nextInt(26) + 'a'));

        // Generate at least one number
        randomString.append(secureRandom.nextInt(10));

        // Generate additional characters to meet the length requirement (greater than 8)
        while (randomString.length() <= 8) {
            char randomChar = (char) (secureRandom.nextInt(26) + 'a');
            randomString.append(randomChar);
        }

        // Shuffle the characters to make the order random
        for (int i = randomString.length() - 1; i > 0; i--) {
            int index = secureRandom.nextInt(i + 1);
            char temp = randomString.charAt(index);
            randomString.setCharAt(index, randomString.charAt(i));
            randomString.setCharAt(i, temp);
        }

        // Display the random string
        randomNumberTextView.setText("Password: " + randomString.toString());
    }
}
