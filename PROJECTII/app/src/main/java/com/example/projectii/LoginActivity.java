package com.example.projectii;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton, btnForget;
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;

    // Keep track of login attempts and lockout times
    private Map<String, Integer> loginAttempts = new HashMap<>();
    private Map<String, Long> lockoutTimes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);
        btnForget = findViewById(R.id.btn_forget);
        editTextEmail = findViewById(R.id.editTextEmail);

        firebaseAuth = FirebaseAuth.getInstance();

        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                if (!TextUtils.isEmpty(email)) {
                    sendPasswordResetEmail(email);
                } else {
                    Toast.makeText(LoginActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if username and password are not empty
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if email is temporarily locked out
                if (lockoutTimes.containsKey(username) && System.currentTimeMillis() - lockoutTimes.get(username) < 10 * 60 * 1000) {
                    Toast.makeText(LoginActivity.this, "Account temporarily locked. Try again later after 10 min.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if login attempts exceed a threshold (e.g., 3 attempts)
                if (loginAttempts.containsKey(username) && loginAttempts.get(username) >= 3) {
                    // Lock the account and record the lockout time
                    lockoutTimes.put(username, System.currentTimeMillis());
                    Toast.makeText(LoginActivity.this, "Account temporarily locked. Try again later.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Authenticate user
                firebaseAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Login success
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String email = (user != null) ? user.getEmail() : "";

                                // Check if the user is an administrator
                                if (!TextUtils.isEmpty(email) && email.equals("saujankhanal004@gmail.com")) {
                                    Toast.makeText(LoginActivity.this, "Admin login successful.", Toast.LENGTH_SHORT).show();
                                    // Proceed to the admin activity
                                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                                    // Proceed to the regular user activity
                                    startActivity(new Intent(LoginActivity.this, Login_home.class));
                                }
                                finish();
                            } else {
                                // Login failed
                                Exception exception = task.getException();
                                if (exception instanceof FirebaseAuthException) {
                                    FirebaseAuthException e = (FirebaseAuthException) exception;
                                    Toast.makeText(LoginActivity.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                // Update login attempts
                                if (loginAttempts.containsKey(username)) {
                                    loginAttempts.put(username, loginAttempts.get(username) + 1);
                                } else {
                                    loginAttempts.put(username, 1);
                                }
                            }
                        });
            }
        });
    }

    private void sendPasswordResetEmail(String email) {
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Password reset email sent to " + email, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error sending password reset email.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
