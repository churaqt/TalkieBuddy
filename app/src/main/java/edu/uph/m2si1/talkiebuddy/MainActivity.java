package edu.uph.m2si1.talkiebuddy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import edu.uph.m2si1.talkiebuddy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Fragment default saat pertama dibuka
        //replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navHome) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.navProfil) {
                replaceFragment(new ProfilFragment());
            } else if (itemId == R.id.navTips) {
                replaceFragment(new TipsFragment());
            }
            else if (itemId == R.id.navTeddy) {
                replaceFragment(new TeddyFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commit();
    }
}

