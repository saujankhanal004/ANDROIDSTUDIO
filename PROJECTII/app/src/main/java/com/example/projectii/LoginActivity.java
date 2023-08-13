package com.example.projectii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                // Authenticate user
                firebaseAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Login success
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String uid = user.getUid();
                                String email = user.getEmail();


                                // Check if the user is an administrator
                                if (email != null && email.equals("saujankhanal004@gmail.com")) {
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
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(LoginActivity.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
