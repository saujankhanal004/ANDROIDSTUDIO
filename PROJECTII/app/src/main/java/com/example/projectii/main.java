package com.example.projectii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.Menu;
import android.content.Intent;




public class main extends AppCompatActivity {
    Button frag1, frag2, frag3;
    LinearLayout linlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        linlayout = (LinearLayout) findViewById(R.id.farglayout);
        frag1 = (Button) findViewById(R.id.fragm1);
        frag2 = (Button) findViewById(R.id.fragm2);


        frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home a = new Home();
                FragmentManager fmanager = getFragmentManager();
                FragmentTransaction t = fmanager.beginTransaction();
                t.replace(R.id.farglayout, a);
                t.commit();
            }
        });

        frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new contact());
            }
        });


    }

    void loadFragment(contact fargm) {
        FragmentManager fmanager = getFragmentManager();
        FragmentTransaction t = fmanager.beginTransaction();
        t.replace(R.id.farglayout, fargm);
        t.commit();
    }
//kjfwke for menu


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.options_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_create_account:
                    Toast.makeText(this, "Create Account selected", Toast.LENGTH_SHORT).show();
                    openCREATEACCOUNTPage();
                    return true;
                case R.id.menu_login:
                    Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT).show();
                    openLoginPage();

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }






    //knkfbhwd
    //for login button
    private void openLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private void openCREATEACCOUNTPage() {
        Intent intent = new Intent(this, CREATEACCOUNT.class);
        startActivity(intent);
    }












    //kdjfjkd

}
