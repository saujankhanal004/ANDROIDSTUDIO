package com.example.projectii;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Login_home extends AppCompatActivity {
    Button lhome, lmessage;
    LinearLayout linlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);

        linlayout = findViewById(R.id.farglayout);
        lhome = findViewById(R.id.fragmm1);
        lmessage = findViewById(R.id.fragmm2);

        lmessage.setOnClickListener(view -> loadFragment(new S_fragmentmessage()));
        lhome.setOnClickListener(view -> loadFragment(new S_fragmenthome()));
    }

    void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Add the fragment to the container
        fragmentTransaction.add(R.id.farglayout, fragment);

        // Hide the previously added fragment, if any
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.farglayout);
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        // Commit the fragment transaction
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_logout) {
            // Perform logout operation
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        // Code for logging out the user
        // Add your implementation here

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        // Finish the current activity
        finish();
    }
}
