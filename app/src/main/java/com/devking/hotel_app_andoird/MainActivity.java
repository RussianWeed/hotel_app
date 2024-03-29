package com.devking.hotel_app_andoird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottom_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nav = findViewById(R.id.button_navigation_bar);
        bottom_nav.setSelectedItemId(R.id.home);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.home) {
                    loadfragment(new HomeFragment());
                }
                else if(id == R.id.saved){
                    loadfragment(new SavedFragment());
                }
                else if (id == R.id.explore){
                    loadfragment(new ExploreFragment());
                }
                else if (id == R.id.bookings) {
                    loadfragment( new BookingFragment());
                }
                else if (id == R.id.profile) {
                    loadfragment(new ProfileFragment());
                }

                return true;
            }
        });


    }
    public void loadfragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.framelayout,fragment);
        ft.commit();

    }
}