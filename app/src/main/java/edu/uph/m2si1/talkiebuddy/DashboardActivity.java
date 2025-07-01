package edu.uph.m2si1.talkiebuddy.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.uph.m2si1.talkiebuddy.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // layout XML yg ada BottomNavigationView-nya

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Set 3 fragment sebagai top-level destination
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_tips)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);

        NavigationUI.setupWithNavController(navView, navController);
    }
}
