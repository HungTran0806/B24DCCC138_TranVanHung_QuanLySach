package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String USER = "admin";
    private static final String PASS = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        TextView tvError = findViewById(R.id.tvError);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String u = edtUsername.getText().toString().trim();
            String p = edtPassword.getText().toString();

            if (u.isEmpty() || p.isEmpty()) {
                tvError.setText("Vui lòng nhập đủ thông tin");
                tvError.setVisibility(View.VISIBLE);
                return;
            }

            if (u.equals(USER) && p.equals(PASS)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                tvError.setText("Sai tên đăng nhập hoặc mật khẩu");
                tvError.setVisibility(View.VISIBLE);
            }
        });
    }
}