package com.example.projectii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AdminActivity extends AppCompatActivity {
    Button ADDGOODS, EDITGOODS, MESSAGE;
    LinearLayout linlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        linlayout = findViewById(R.id.farglayout);
        ADDGOODS = findViewById(R.id.admadd);
        EDITGOODS = findViewById(R.id.admedi);
        MESSAGE = findViewById(R.id.admmes);

        ADDGOODS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminADDGOODS addtGoodsFragment = new AdminADDGOODS();
                loadFragment(addtGoodsFragment);
            }
        });

        EDITGOODS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminEDITGOODS editGoodsFragment = new AdminEDITGOODS();
                loadFragment(editGoodsFragment);
            }
        });

        MESSAGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminMessageView messageViewFragment = new AdminMessageView();
                loadFragment(messageViewFragment);
            }
        });
    }

    void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.farglayout, fragment);
        transaction.commit();
    }
}
