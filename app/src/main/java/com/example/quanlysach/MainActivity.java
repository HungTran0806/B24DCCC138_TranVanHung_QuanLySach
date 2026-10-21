package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlysach.adapter.BookAdapter;
import com.example.quanlysach.data.BookRepository;

public class MainActivity extends AppCompatActivity {
    private BookAdapter adapter;
    private TextView tvTotal;
    private TextView tvRead;
    private TextView tvUnfinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotal = findViewById(R.id.tvTotal);
        tvRead = findViewById(R.id.tvRead);
        tvUnfinished = findViewById(R.id.tvUnfinished);
        ListView lvBooks = findViewById(R.id.lvBooks);

        adapter = new BookAdapter(this, BookRepository.getBooks());
        lvBooks.setAdapter(adapter);

        lvBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });

        Button btnAddBook = findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(v -> startActivity(new Intent(this, AddBookActivity.class)));

        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        adapter.notifyDataSetChanged();
        tvTotal.setText(String.valueOf(BookRepository.getBooks().size()));
        tvRead.setText(String.valueOf(BookRepository.countRead()));
        tvUnfinished.setText(String.valueOf(BookRepository.countUnfinished()));
    }
}