package com.example.projectii;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Login_home extends AppCompatActivity {
    Button lhome, lmessage;
    LinearLayout linlayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);

        linlayout = findViewById(R.id.farglayout);
        lhome = findViewById(R.id.fragmm1);
        lmessage = findViewById(R.id.fragmm2);

        lhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new S_fragmenthome());
            }
        });

        lmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new S_fragmentmessage());
            }
        });
    }

    void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.farglayout, fragment);
        fragmentTransaction.commit();
    }
}
