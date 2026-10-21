package com.example.quanlysach;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlysach.data.BookRepository;
import com.example.quanlysach.model.Book;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        EditText edtTitle = findViewById(R.id.edtTitle);
        EditText edtAuthor = findViewById(R.id.edtAuthor);
        Spinner spCategory = findViewById(R.id.spCategory);
        RadioGroup rgStatus = findViewById(R.id.rgStatus);
        RatingBar rbRating = findViewById(R.id.rbRating);
        TextView tvRating = findViewById(R.id.tvRating);
        EditText edtNote = findViewById(R.id.edtNote);
        Button btnSave = findViewById(R.id.btnSave);
        TextView tvBack = findViewById(R.id.tvBack);
        tvBack.setOnClickListener(v -> finish());

        String[] categories = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryAdapter);

        rbRating.setOnRatingBarChangeListener((bar, rating, fromUser) ->
                tvRating.setText(((int) rating) + " / 5 sao"));

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String author = edtAuthor.getText().toString().trim();

            if (title.isEmpty()) {
                edtTitle.setError("Vui lòng nhập tên sách");
                edtTitle.requestFocus();
                return;
            }
            if (author.isEmpty()) {
                edtAuthor.setError("Vui lòng nhập tác giả");
                edtAuthor.requestFocus();
                return;
            }

            String category = spCategory.getSelectedItem().toString();

            String status = BookRepository.UNREAD;
            int checkedId = rgStatus.getCheckedRadioButtonId();
            if (checkedId == R.id.rbReading) {
                status = BookRepository.READING;
            } else if (checkedId == R.id.rbRead) {
                status = BookRepository.READ;
            }

            float rating = rbRating.getRating();
            String note = edtNote.getText().toString().trim();

            BookRepository.add(new Book(title, author, category, status, rating, note));
            Toast.makeText(this, "Đã lưu sách", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}