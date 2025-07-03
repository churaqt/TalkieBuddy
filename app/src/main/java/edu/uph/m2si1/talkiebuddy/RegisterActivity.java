package edu.uph.m2si1.talkiebuddy;

import android.content.Intent;
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

import edu.uph.m2si1.talkiebuddy.model.Account;
import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPassword;
    private Button btnRegister;
    private TextView txvLogin;
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
        txvLogin = findViewById(R.id.txvLogin);

        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            Account existing = realm.where(Account.class)
                    .equalTo("username", username)
                    .findFirst();

            if (existing != null) {
                Toast.makeText(this, "Username sudah terdaftar", Toast.LENGTH_SHORT).show();
                return;
            }

            realm.executeTransactionAsync(r -> {
                Account newAcc = r.createObject(Account.class, username);
                newAcc.setEmail(email);
                newAcc.setPassword(password);
            }, () -> {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Akun berhasil dibuat. Mengalihkan ke halaman login...", Toast.LENGTH_SHORT).show();
                    goToLogin();
                });
            }, error -> {
                runOnUiThread(() -> Toast.makeText(this, "Gagal registrasi: " + error.getMessage(), Toast.LENGTH_SHORT).show());
            });
        });

        txvLogin.setOnClickListener(v -> goToLogin());
    }

    private void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
