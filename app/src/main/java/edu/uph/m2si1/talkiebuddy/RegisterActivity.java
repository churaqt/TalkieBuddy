package edu.uph.m2si1.talkiebuddy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.uph.m2si1.talkiebuddy.model.User;
import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPassword;
    private Button btnRegister;
    private TextView txtLogin;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        realm = Realm.getDefaultInstance();


        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);

        // Tombol Register
        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            // Validasi input
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cek apakah username sudah ada
            User existingUser = realm.where(User.class)
                    .equalTo("username", username)
                    .findFirst();

            if (existingUser != null) {
                Toast.makeText(this, "Username sudah terdaftar", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simpan ke Realm
            realm.executeTransaction(r -> {
                User user = r.createObject(User.class, username);
                user.setEmail(email);
                user.setPassword(password);
            });

            Toast.makeText(this, "Registrasi berhasil. Silakan login.", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Klik "Login" untuk kembali
        txtLogin.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
