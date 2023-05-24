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

public class CreateAccountActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button createAccountButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        createAccountButton = findViewById(R.id.btn_create_account);

        firebaseAuth = FirebaseAuth.getInstance();

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Create a new user account
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccountActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Account creation success
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Toast.makeText(CreateAccountActivity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                // Account creation failed
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(CreateAccountActivity.this, "Account creation failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
