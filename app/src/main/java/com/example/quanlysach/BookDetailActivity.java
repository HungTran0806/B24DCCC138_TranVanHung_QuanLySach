package com.example.quanlysach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlysach.data.BookRepository;
import com.example.quanlysach.model.Book;
import com.example.quanlysach.util.UiUtils;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        int position = getIntent().getIntExtra("position", -1);
        if (position < 0 || position >= BookRepository.getBooks().size()) {
            finish();
            return;
        }

        Book book = BookRepository.getBooks().get(position);

        TextView tvBack = findViewById(R.id.tvBack);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthorCategory = findViewById(R.id.tvAuthorCategory);
        TextView tvStatus = findViewById(R.id.tvStatus);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvNote = findViewById(R.id.tvNote);
        Button btnMarkRead = findViewById(R.id.btnMarkRead);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnDelete = findViewById(R.id.btnDelete);

        tvTitle.setText(book.getTitle());
        tvAuthorCategory.setText(book.getAuthor() + "  ·  " + book.getCategory());
        UiUtils.applyStatusChip(tvStatus, book.getStatus());
        ratingBar.setRating(book.getRating());

        String note = book.getNote();
        tvNote.setText(note == null || note.isEmpty() ? "(không có)" : note);

        if (BookRepository.READ.equals(book.getStatus())) {
            btnMarkRead.setVisibility(View.GONE);
        }

        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            if (fromUser) {
                book.setRating(rating);
            }
        });

        btnMarkRead.setOnClickListener(v -> {
            book.setStatus(BookRepository.READ);
            UiUtils.applyStatusChip(tvStatus, book.getStatus());
            btnMarkRead.setVisibility(View.GONE);
        });

        tvBack.setOnClickListener(v -> finish());
        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            BookRepository.remove(position);
            finish();
        });
    }
}