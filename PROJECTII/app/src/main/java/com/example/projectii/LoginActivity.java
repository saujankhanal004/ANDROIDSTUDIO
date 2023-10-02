package com.example.projectii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();

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
                            }
                        });
            }
        });
    }
}
