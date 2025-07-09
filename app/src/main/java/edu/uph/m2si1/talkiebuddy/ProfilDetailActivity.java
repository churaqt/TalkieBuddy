import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.DatePicker;
import android.view.View;
import android.widget.Toast;

import edu.uph.m2si1.talkiebuddy.R;

public class ProfilDetailActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName;
    private RadioGroup genderGroup;
    private RadioButton maleRadio, femaleRadio;
    private DatePicker birthdayPicker;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_detail);

        initializeViews();
        setupClickListeners();
        loadProfileData();
    }

    private void initializeViews() {
        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        genderGroup = findViewById(R.id.gender_group);
        maleRadio = findViewById(R.id.male_radio);
        femaleRadio = findViewById(R.id.female_radio);
        birthdayPicker = findViewById(R.id.birthday_picker);
        updateButton = findViewById(R.id.update_button);
    }

    private void setupClickListeners() {
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
            }
        });
    }

    private void loadProfileData() {
        // Load existing profile data
        profileName.setText("John");
        maleRadio.setChecked(true);
        // Set default date or load from preferences
    }

    private void saveProfileData() {
        String selectedGender = maleRadio.isChecked() ? "Male" : "Female";
        int year = birthdayPicker.getYear();
        int month = birthdayPicker.getMonth();
        int day = birthdayPicker.getDayOfMonth();

        // Save to SharedPreferences or database
        // For now, show a toast
        Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
    }
}